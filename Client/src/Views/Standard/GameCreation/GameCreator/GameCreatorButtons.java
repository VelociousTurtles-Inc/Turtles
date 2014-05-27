package Views.Standard.GameCreation.GameCreator;

import Common.Interfaces.Event;
import Controllers.Interfaces.GameCreatorController;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class GameCreatorButtons {

    public TextField name;
    Stage myStage;
    GameCreatorController myController;

    public void setStage(Stage stage) {
        myStage = stage;
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
        myController.create(String.valueOf(name.getCharacters()));
        cancel(actionEvent);
    }
}
