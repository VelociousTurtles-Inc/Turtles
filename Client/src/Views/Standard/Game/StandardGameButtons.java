package Views.Standard.Game;

import Adapters.Interfaces.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mz18 on 8/05/14.
 */
public class StandardGameButtons {

    private GameController myGameController;

    @FXML private ImageView firstTurtle;
    @FXML private ImageView secondTurtle;
    @FXML private ImageView thirdTurtle;
    @FXML private ImageView fourthTurtle;
    @FXML private ImageView fifthTurtle;

    List<ImageView> getTurtles() {
        List<ImageView> turtles = new ArrayList<ImageView>();

        turtles.add(null);
        turtles.add(firstTurtle);
        turtles.add(secondTurtle);
        turtles.add(thirdTurtle);
        turtles.add(fourthTurtle);
        turtles.add(fifthTurtle);

        return turtles;
    }

    private int chosenCard = 1;

    public void init(GameController myGameController) {
        this.myGameController = myGameController;
    }

    @FXML protected void surrIt(ActionEvent event) {
        myGameController.surrender();
    }

    @FXML protected void playIt(ActionEvent event) {
        myGameController.playCard(chosenCard);
    }

    @FXML protected void chooseFirst(ActionEvent event) {
        chosenCard = 1;
    }
    @FXML protected void chooseSecond(ActionEvent event) {
        chosenCard = 2;
    }
    @FXML protected void chooseThird(ActionEvent event) {
        chosenCard = 3;
    }
    @FXML protected void chooseFourth(ActionEvent event) {
        chosenCard = 4;
    }
    @FXML protected void chooseFifth(ActionEvent event) {
        chosenCard = 5;
    }

}
