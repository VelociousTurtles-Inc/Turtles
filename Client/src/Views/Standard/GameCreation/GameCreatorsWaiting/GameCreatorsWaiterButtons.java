package Views.Standard.GameCreation.GameCreatorsWaiting;

import Events.Event;
import Adapters.Interfaces.GameCreatorWaiterController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameCreatorsWaiterButtons {

    public Label numberOfPlayers;
    public Label gameName;
    private GameCreatorWaiterController controller;
    private Stage stage;

    private String name;
    private int number;

    public void setController(GameCreatorWaiterController myController) throws Exception {
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
        gameName.setText(name);
        numberOfPlayers.setText(String.valueOf(number));
    }

    public void setInitValues(String name, int i) {
        this.name = name;
        this.number = i;
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
