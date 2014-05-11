package Views.Standard.Menu;

import Adapters.Interfaces.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;


/**
 * Created by michaziobro on 05.05.2014.
 */
public class StandardMenuButtons {

    private MenuController myMenuController;

    public void setMyMenuController(MenuController myMenuController) {
        this.myMenuController = myMenuController;
    }

    @FXML private void startGame(ActionEvent event) throws IOException {
        myMenuController.startGame();
    }
    @FXML private void exit(ActionEvent event) {
        myMenuController.exitProgram();
    }
}
