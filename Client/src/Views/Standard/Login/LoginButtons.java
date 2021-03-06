package Views.Standard.Login;

import Common.Interfaces.Event;
import Controllers.Interfaces.LoginController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginButtons {
    public TextField name;
    private Stage stage;
    private LoginController myController;
    public Button submitButton;

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

        name.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if(keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                        submit(null);
                    }
                    keyEvent.consume();
                }
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                name.requestFocus();
            }
        });
    }

    public Stage getStage() {
        return stage;
    }

    public void submit(ActionEvent actionEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                submitButton.setDisable(true);
                submitButton.setText("Wait...");
                new Thread () {
                    @Override
                    public void run() {
                        if (!myController.submit(name.getText())) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    submitButton.setDisable(false);
                                    submitButton.setText("Try again");
                                }
                            });
                        }
                    }
                }.start();
            }
        });
    }

    public void cancel(ActionEvent actionEvent) {
        myController.close();
    }
}
