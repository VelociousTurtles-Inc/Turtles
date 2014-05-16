package Views.Standard.GameChoosing;

import Adapters.Interfaces.GameChoosingController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameChoosingView {

    GameChoosingController myController;
    GameChoosingButtons myButtons;

    public GameChoosingView(GameChoosingController myController) {
        this.myController = myController;
    }

    public void start() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage myStage = new Stage();
                FXMLLoader myLoader = new FXMLLoader();

                Parent myParent = null;
                try {
                    myParent = (Parent) myLoader.load(getClass().getResource("GameChoosingView.fxml").openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                myButtons = myLoader.getController();
                myButtons.setController(myController);
                myButtons.setStage(myStage);

                myStage.setScene(new Scene(myParent));
            }
        });
    }
}
