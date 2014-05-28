package Views.Standard.GameCreation.GameCreator;

import Common.Interfaces.Event;
import Controllers.Interfaces.GameCreatorController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class GameCreatorButtons {

    public TextField name;
    Stage myStage;
    GameCreatorController myController;
    public Button createButton;

    public void setStage(Stage stage) {
        myStage = stage;

        name.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if(keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                        try {
                            create(null);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    keyEvent.consume();
                }
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                name.requestFocus();
            }
        });
    }
    public void setController(GameCreatorController myController) {
        this.myController = myController;
        this.myController.registerClosingEvent(new Event() {
            @Override
            public void call() {
                myStage.close();
            }
        });
    }

    public void cancel(ActionEvent actionEvent) {
        myStage.close();
    }

    public void create(ActionEvent actionEvent) throws RemoteException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createButton.setDisable(true);
                    createButton.setText("Creating...");
                } catch (NullPointerException ignored) {}
            }
        });
        myController.create(String.valueOf(name.getCharacters()));
        cancel(actionEvent);
    }
}
