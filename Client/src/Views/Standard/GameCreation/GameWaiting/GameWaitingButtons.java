package Views.Standard.GameCreation.GameWaiting;

import Adapters.Interfaces.Event;
import Adapters.StandardGameWaiterController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameWaitingButtons {
    public Label numberOfPlayers;
    public Label gameName;
    private StandardGameWaiterController controller;
    private Stage stage;

    public void setController(StandardGameWaiterController myController) throws Exception {
        this.controller = myController;
        this.controller.registerUpdate(new Event() {
            @Override
            public void call() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        numberOfPlayers.setText(String.valueOf(controller.getNumberOfPlayers()));
                        try {
                            gameName.setText(controller.getGameName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        controller.registerCancelEvent(new Event() {
            @Override
            public void call() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
        });
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void leave(ActionEvent actionEvent) throws Exception {
        stage.close();
        controller.leave();
    }
}