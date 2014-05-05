package Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewController {

    private int chosenCard = 1;


    @FXML private ImageView firstTurtle;

    @FXML protected void showIt(ActionEvent event) {
        Stage wind1 = new Stage();
        StackPane innerPane = new StackPane();
        innerPane.getChildren().add(new Text(String.valueOf(chosenCard)));
        innerPane.setLayoutX(100);
        innerPane.setLayoutY(100);

        wind1.setScene(new Scene(innerPane));
        wind1.setResizable(false);
        wind1.show();
    }
    @FXML protected void surrIt(ActionEvent event) {
        firstTurtle.setX(120);
        firstTurtle.setY(-100);
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
