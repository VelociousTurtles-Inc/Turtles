package Views.Standard.Login;

import Adapters.Interfaces.LoginController;
import Common.Interfaces.Event;
import Adapters.StandardLoginController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class LoginButtons {
    public TextField name;
    private Stage stage;
    private LoginController myController;

    public void setController(LoginController myController) {
        this.myController = myController;
        myController.registerCloseEvent(new Event() {
            @Override
            public void call() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });

            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void submit(ActionEvent actionEvent) throws RemoteException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    myController.submit(name.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void cancel(ActionEvent actionEvent) {
        myController.close();
    }
}
