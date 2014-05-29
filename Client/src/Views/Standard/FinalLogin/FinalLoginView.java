package Views.Standard.FinalLogin;

import Controllers.Interfaces.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FinalLoginView {
    final LoginController myController;
    FinalLoginButtons myButtons;

    public FinalLoginView(LoginController myController) {
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

    public void start() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage myStage = new Stage();
                FXMLLoader myLoader = new FXMLLoader();

                Parent myParent = null;
                try {
                    myParent = (Parent) myLoader.load(getClass().getResource("FinalLoginView.fxml").openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                myButtons = myLoader.getController();
                myButtons.setController(myController);
                myButtons.setStage(myStage);

                myStage.setScene(new Scene(myParent));
                myStage.show();
            }
        });

    }
}
