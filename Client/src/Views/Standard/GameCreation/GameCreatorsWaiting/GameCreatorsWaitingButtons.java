package Views.Standard.GameCreation.GameCreatorsWaiting;

import Adapters.Interfaces.Event;
import Adapters.StandardGameCreatorWaiterController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameCreatorsWaitingButtons {

    public Label numberOfPlayers;
    public Label gameName;
    private StandardGameCreatorWaiterController controller;
    private Stage stage;

    public void setController(StandardGameCreatorWaiterController myController) {
        this.controller = myController;
        this.controller.registerUpdateEvent(new Event() {
            @Override
            public void call() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        numberOfPlayers.setText(String.valueOf(controller.getNumberOfPlayers()));
                        gameName.setText(controller.getGameName());
                    }
                });
            }
        });
        this.controller.registerCancelEvent(new Event() {
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

    public void setName(String name) {
        gameName.setText(name);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void cancel(ActionEvent actionEvent) throws Exception {
        controller.cancelAll();
    }

    public void start(ActionEvent actionEvent) throws Exception {
        controller.startAll();
    }
}