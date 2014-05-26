package Views.Standard.Login;

import Adapters.LoginController;
import Events.Event;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by michaziobro on 26.05.2014.
 */
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

    public void submit(ActionEvent actionEvent) throws Exception {
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
