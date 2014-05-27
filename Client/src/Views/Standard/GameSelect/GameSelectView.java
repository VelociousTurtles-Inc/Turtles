package Views.Standard.GameSelect;

import Controllers.Interfaces.GameSelectController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class GameSelectView {

    final GameSelectController myController;
    GameSelectButtons myButtons;

    public GameSelectView(GameSelectController myController) {
        this.myController = myController;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void start() throws RemoteException {

        Stage myStage = new Stage();
        FXMLLoader myLoader = new FXMLLoader();

        Parent myParent = null;
        try {
            myParent = (Parent) myLoader.load(getClass().getResource("GameSelectView.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        myButtons = myLoader.getController();
        myButtons.setController(myController);
        myButtons.setStage(myStage);

        myStage.setScene(new Scene(myParent));
        myStage.show();

    }
}
