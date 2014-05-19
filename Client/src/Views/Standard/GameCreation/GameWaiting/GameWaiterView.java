package Views.Standard.GameCreation.GameWaiting;

import Adapters.Interfaces.GameWaiterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameWaiterView {
    GameWaiterController myController;
    GameWaitingButtons myButtons;

    public GameWaiterView(GameWaiterController controller) {
        myController = controller;
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
