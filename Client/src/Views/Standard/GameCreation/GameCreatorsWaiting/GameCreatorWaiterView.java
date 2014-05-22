package Views.Standard.GameCreation.GameCreatorsWaiting;

import Adapters.Interfaces.GameCreatorWaiterController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameCreatorWaiterView {
    GameCreatorWaiterController myController;
    GameCreatorsWaiterButtons myButtons;

    public GameCreatorWaiterView(GameCreatorWaiterController standardGameCreatorController) {
        myController = standardGameCreatorController;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    start(myController.getGameName(), myController.getNumberOfPlayers());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void start(String name, int numberOP) throws Exception {
        Stage myStage = new Stage();
        FXMLLoader myLoader = new FXMLLoader();

        Parent myParent = null;
        try {
            myParent = (Parent) myLoader.load(getClass().getResource("GameCreatorsWaiting.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        myButtons = myLoader.getController();

        myButtons.setStage(myStage);

        myStage.setScene(new Scene(myParent));
        myStage.show();
        myButtons.setInitValues(name, numberOP);
        myButtons.setController(myController);
    }
}
