package Views.Standard.GameCreation.GameWaiting;

import Controllers.Interfaces.GameWaiterController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class GameWaiterView {
    final GameWaiterController myController;
    GameWaitingButtons myButtons;

    public GameWaiterView(GameWaiterController controller) {
        myController = controller;
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
    public void start(String name, int numberOP) throws RemoteException {

                Stage myStage = new Stage();
                FXMLLoader myLoader = new FXMLLoader();

                Parent myParent = null;
                try {
                    myParent = (Parent) myLoader.load(getClass().getResource("GameWaiting.fxml").openStream());
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
