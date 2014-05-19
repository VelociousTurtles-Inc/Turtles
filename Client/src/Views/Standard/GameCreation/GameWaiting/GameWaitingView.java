package Views.Standard.GameCreation.GameWaiting;

import Adapters.StandardGameWaiterController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameWaitingView {
    StandardGameWaiterController myController;
    GameWaitingButtons myButtons;

    public GameWaitingView(StandardGameWaiterController controller) {
        myController = controller;
    }
    public void start() throws Exception {

                Stage myStage = new Stage();
                FXMLLoader myLoader = new FXMLLoader();

                Parent myParent = null;
                try {
                    myParent = (Parent) myLoader.load(getClass().getResource("GameWaiting.fxml").openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Wowowowowwowow");

                myButtons = myLoader.getController();
                myButtons.setController(myController);
                myButtons.setStage(myStage);

                myStage.setScene(new Scene(myParent));
                myStage.show();

    }
}
