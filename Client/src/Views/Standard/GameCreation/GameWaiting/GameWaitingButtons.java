package Views.Standard.GameCreation.GameWaiting;

import Adapters.StandardGameWaiterController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameWaitingButtons {
    public Label numberOfPlayers;
    private StandardGameWaiterController controller;
    private Stage stage;

    public void setController(StandardGameWaiterController controller) {
        this.controller = controller;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void leave(ActionEvent actionEvent) {
        stage.close();
        controller.leave();
    }

    public void update(int newNumberOfPlayers) {
        numberOfPlayers.setText(String.valueOf(numberOfPlayers));
    }
}
