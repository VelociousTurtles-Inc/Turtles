package Views.Standard.GameCreation.GameCreatorsWaiting;

import Adapters.StandardGameCreatorController;
import Adapters.StandardGameCreatorWaiterController;
import Views.Standard.GameCreation.GameCreator.GameCreatorButtons;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameCreatorsWaitingButtons {

    private StandardGameCreatorWaiterController controller;
    private Stage stage;

    public void setController(StandardGameCreatorWaiterController controller) {
        this.controller = controller;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void cancel(ActionEvent actionEvent) {
        controller.cancel();
        stage.close();
    }

    public void start(ActionEvent actionEvent) {
        controller.start();
    }
}
