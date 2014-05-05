package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller {
    @FXML private Text actiontarget;

    @FXML protected void showIt(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }

}
