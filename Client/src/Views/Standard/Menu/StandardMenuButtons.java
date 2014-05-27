package Views.Standard.Menu;

import Adapters.Interfaces.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class StandardMenuButtons {

    private MenuController myMenuController;

    public void setMyMenuController(MenuController myMenuController) {
        this.myMenuController = myMenuController;
    }

    @FXML private void startGame(ActionEvent event) throws IOException {
        try {
            myMenuController.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML private void exit(ActionEvent event) {
        myMenuController.exitProgram();
    }
}
