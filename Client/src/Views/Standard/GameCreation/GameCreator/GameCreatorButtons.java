package Views.Standard.GameCreation.GameCreator;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameCreatorController;
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

    public void create(ActionEvent actionEvent) throws Exception {
        myController.create(String.valueOf(name.getCharacters()));
        cancel(actionEvent);
    }
}
