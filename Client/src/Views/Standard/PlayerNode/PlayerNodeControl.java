package Views.Standard.PlayerNode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * Created by mz18 on 2/06/14.
 */
public class PlayerNodeControl extends AnchorPane {

    @FXML
    ImageView lastPlayedCard;

    @FXML
    Rectangle myBackground;
    @FXML
    Label name;

    public PlayerNodeControl(String myName) {
        FXMLLoader myLoader = new FXMLLoader();

        myLoader.setLocation(getClass().getResource("PlayerNode.fxml"));
        try {


        myLoader.setRoot(this);
        myLoader.setController(this);
            myLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        name.setText(myName);
    }

    public void setLastPlayedCard(Image lastPlayedCard) {
        this.lastPlayedCard.setImage(lastPlayedCard);
    }
    public void setOnMove() {
        myBackground.setFill(Color.GREEN);
    }
    public void setNotOnMove() {
        myBackground.setFill(Color.web("#2e3740"));
    }
}