package Views.Standard.GameSelect;

import Adapters.Interfaces.GameSelectController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameSelectView {

    GameSelectController myController;
    GameSelectButtons myButtons;

    public GameSelectView(GameSelectController myController) {
        this.myController = myController;
        start();
    }

    public void start() {

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
