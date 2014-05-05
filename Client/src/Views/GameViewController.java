package Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameViewController implements GVCInterface {

    public class Point {
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        int x;
        int y;
    }

    List<ImageView> turtles = new ArrayList<ImageView>();

    @FXML private ImageView firstTurtle;
    @FXML private ImageView secondTurtle;
    @FXML private ImageView thirdTurtle;
    @FXML private ImageView fourthTurtle;
    @FXML private ImageView fifthTurtle;

    {
        turtles.add(firstTurtle);
        turtles.add(secondTurtle);
        turtles.add(thirdTurtle);
        turtles.add(fourthTurtle);
        turtles.add(fifthTurtle);
    }

    private int size;
    private List<Point> myBoard = new ArrayList<Point>();
    private int chosenCard = 1;
    private boolean initialized = false;

    ControllerInterface myController;

    public void init(int numberOfBoard, ControllerInterface myController) {
        if(!initialized) {

            this.myController = myController;

            //tu będzie następować wczytanie rysunku, współrzędnych poszczególnych pól itd.
        }
    }

    public void updateBoard(List<List<Integer>> updateForBoard) {
        for(int i = 0; i<=size; i++) {
            if(updateForBoard.get(i) != null && updateForBoard.get(i).size() != 0) {
                double diff = 5*
            }
        }
    }

    public void updateCard(int numberOfCard, int typeOfCard) {
        // tu następuje zamiana wybranej karty na inną
    }

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
