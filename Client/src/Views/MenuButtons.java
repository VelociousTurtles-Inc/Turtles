package Views;

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
        final GameViewController myOwnGameController = myLoader.getController();


            Platform.runLater(new Runnable() {
                final GameViewController myCont = myOwnGameController;
                @Override
                public void run() {
                   if(Platform.isFxApplicationThread()) myCont.setOnStartPositions();
                    else System.out.println("Nie ten wątek :(");
                }
            }
            );

        }
    @FXML private void exit(ActionEvent event) {
        System.exit(0);
    }
}
