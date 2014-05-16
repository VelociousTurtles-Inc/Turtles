package Views.Standard.Game;

import Adapters.Interfaces.GameController;
import ModelHelpers.DebugWriter;
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

    @FXML private ImageView firstCardImage;
    @FXML private ImageView secondCardImage;
    @FXML private ImageView thirdCardImage;
    @FXML private ImageView fourthCardImage;
    @FXML private ImageView fifthCardImage;

    List<ImageView> getCardSlots() {
        List<ImageView> cards = new ArrayList<>();

        cards.add(null);
        cards.add(firstCardImage);
        cards.add(secondCardImage);
        cards.add(thirdCardImage);
        cards.add(fourthCardImage);
        cards.add(fifthCardImage);

        return cards;
    }

    List<ImageView> getTurtles() {
        List<ImageView> turtles = new ArrayList<>();

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
        assert DebugWriter.write("Surrender");
        myGameController.surrender();
    }

    @FXML protected void playIt(ActionEvent event) {
        assert DebugWriter.write("Play Card " + chosenCard);
        try {
            myGameController.playCard(chosenCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML protected void chooseFirst(ActionEvent event) {
        assert DebugWriter.write("Chosed 1st Card");
        chosenCard = 1;
    }
    @FXML protected void chooseSecond(ActionEvent event) {
        assert DebugWriter.write("Chosed 2nd Card");
        chosenCard = 2;
    }
    @FXML protected void chooseThird(ActionEvent event) {
        assert DebugWriter.write("Chosed 3rd Card");
        chosenCard = 3;
    }
    @FXML protected void chooseFourth(ActionEvent event) {
        assert DebugWriter.write("Chosed 4th Card");
        chosenCard = 4;
    }
    @FXML protected void chooseFifth(ActionEvent event) {
        assert DebugWriter.write("Chosed 5th Card");
        chosenCard = 5;
    }

}
