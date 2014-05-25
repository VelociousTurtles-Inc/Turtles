package Views.Standard.GameCreation.GameWaiting;

import Events.Event;
import Adapters.Interfaces.GameWaiterController;
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
    private GameWaiterController controller;
    private Stage stage;

    private String name;
    private int number;

    public void setController(GameWaiterController myController) throws Exception {
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
        this.controller.registerClosingEvent(new Event() {
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
        numberOfPlayers.setText(String.valueOf(number));
        gameName.setText(name);
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void leave(ActionEvent actionEvent) throws Exception {
        stage.close();
        controller.leave();
    }

    public void setInitValues(String name, int numberOP) {
        this.name = name;
        this.number = numberOP;
    }
}
