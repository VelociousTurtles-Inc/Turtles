package Views;

import Adapters.GameAdapter;
import javafx.application.Platform;
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
        myLoader.setLocation(getClass().getResource("Game.fxml"));
        Parent game = (Parent) myLoader.load((getClass().getResource("Game.fxml")).openStream());

        GameStage.setScene(new Scene(game));
        GameStage.setResizable(false);

        GameStage.show();
        final GameView myOwnGameController = myLoader.getController();


            Platform.runLater(new Runnable() {
                final GameView myCont = myOwnGameController;
                @Override
                public void run() {
                    myCont.setOnStartPositions();
                    myCont.init(0, new GameAdapter());
                }
            }
            );

        }
    @FXML private void exit(ActionEvent event) {
        System.exit(0);
    }
}
