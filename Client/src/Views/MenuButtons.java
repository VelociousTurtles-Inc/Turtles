package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by michaziobro on 05.05.2014.
 */
public class MenuButtons {
    @FXML private void startGame(ActionEvent event) throws IOException {
        Stage GameStage = new Stage();
        FXMLLoader myLoader = new FXMLLoader();
        Parent game = myLoader.load(getClass().getResource("Game.fxml"));

        GameStage.setScene(new Scene(game));
        GameStage.setResizable(false);

        GameStage.show();
    }
    @FXML private void exit(ActionEvent event) {
        System.exit(0);
    }
}
