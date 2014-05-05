package Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
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

    @FXML private ImageView firstTurtle;
    @FXML private ImageView secondTurtle;
    @FXML private ImageView thirdTurtle;
    @FXML private ImageView fourthTurtle;
    @FXML private ImageView fifthTurtle;

    private List<Point> startPositions = new ArrayList<Point>();
    private List<Point> myBoard = new ArrayList<Point>();
    private int size;

    ControllerInterface myController;
    private int chosenCard = 1;
    private boolean initialized = false;

    List<ImageView> turtles = new ArrayList<ImageView>();
    {
        turtles.add(firstTurtle);
        turtles.add(secondTurtle);
        turtles.add(thirdTurtle);
        turtles.add(fourthTurtle);
        turtles.add(fifthTurtle);
    }

    @FXML public void setOnStartPositions() {
            firstTurtle.setX(startPositions.get(1).x);
            firstTurtle.setY(startPositions.get(1).y);
            secondTurtle.setX(startPositions.get(2).x);
            secondTurtle.setY(startPositions.get(2).y);
            thirdTurtle.setX(startPositions.get(3).x);
            thirdTurtle.setY(startPositions.get(3).y);
            fourthTurtle.setX(startPositions.get(4).x);
            fourthTurtle.setY(startPositions.get(4).y);
            fifthTurtle.setX(startPositions.get(5).x);
            fifthTurtle.setY(startPositions.get(5).y);
    }



    public void init(int numberOfBoard, ControllerInterface myController) {
        if(!initialized) {

            this.myController = myController;


        }
    }

    public void updateBoard(List<List<Integer>> updateForBoard) {
        for(int i = 0; i<size; i++) {
            if(updateForBoard.get(i) != null && updateForBoard.get(i).size() != 0) {
                int diff = (updateForBoard.get(i).size() - 1)*5;
                for(int j = 0; j<updateForBoard.get(i).size(); j++) {
                    turtles.get(updateForBoard.get(i).get(j)).setY(myBoard.get(i).y - diff + j*10);
                    turtles.get(updateForBoard.get(i).get(j)).setX(myBoard.get(i).x);
                    turtles.get(updateForBoard.get(i).get(j)).toBack();
                }
            }
        }
    }

    public void updateCard(int numberOfCard, int typeOfCard) {
        // tu następuje zamiana wybranej karty na inną
    }

    @FXML protected void showIt(ActionEvent event) {
        //myController.playCard(chosenCard);

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
        secondTurtle.setX(120);
        secondTurtle.setY(-100);
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
