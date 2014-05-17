package Views.Standard.GameCreation.GameCreatorsWaiting;

import Adapters.StandardGameCreatorWaiterController;
import Views.Standard.GameCreation.GameCreator.GameCreatorButtons;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameCreatorsWaitingView {
    StandardGameCreatorWaiterController myController;
    GameCreatorsWaitingButtons myButtons;

    public GameCreatorsWaitingView(StandardGameCreatorWaiterController standardGameCreatorController) {
        myController = standardGameCreatorController;
    }
    public void start() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage myStage = new Stage();
                FXMLLoader myLoader = new FXMLLoader();

                Parent myParent = null;
                try {
                    myParent = (Parent) myLoader.load(getClass().getResource("GameCreatorsWaiting.fxml").openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                myButtons = myLoader.getController();
                myButtons.setController(myController);
                myButtons.setStage(myStage);

                myStage.setScene(new Scene(myParent));
                myStage.show();
            }
        });
    }
}
