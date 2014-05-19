package Views.Standard.GameCreation.GameCreator;

import Adapters.Interfaces.GameCreatorController;
import Adapters.StandardGameCreatorController;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameCreatorButtons {


    public TextField name;
    Stage myStage;
    GameCreatorController myController;

    public void setStage(Stage stage) {
        myStage = stage;
    }
    public void setController(GameCreatorController myController) {
        this.myController = myController;
    }

    public void cancel(ActionEvent actionEvent) {
        myStage.close();
    }

    public void create(ActionEvent actionEvent) throws Exception {
        myController.create(String.valueOf(name.getCharacters()));
        cancel(actionEvent);
    }
}
