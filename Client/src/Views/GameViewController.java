package Views;
import Adapters.GameAdapter;
import javafx.application.Platform;
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

    @FXML private ImageView firstTurtle;
    @FXML private ImageView secondTurtle;
    @FXML private ImageView thirdTurtle;
    @FXML private ImageView fourthTurtle;
    @FXML private ImageView fifthTurtle;

    private List<Point> startPositions = new ArrayList<Point>();
    private List<Point> myBoard = new ArrayList<Point>();
    private int size;                                        //UNINITIALIZED VARIABLE

    {
        /*
        the first position is not used because the GUI designer counts from 1
         */
        startPositions.add(new Point(42, 42));

        startPositions.add(new Point(60, 120));
        startPositions.add(new Point(45, 170));
        startPositions.add(new Point(60, 220));
        startPositions.add(new Point(45, 270));
        startPositions.add(new Point(60, 320));

    }

    {
        /*
        the first position is not used because the GUI designer counts from 1
         */
        myBoard.add(new Point(42, 42));

        myBoard.add(new Point(170, 340));
        myBoard.add(new Point(280, 290));
        myBoard.add(new Point(380, 190));
        myBoard.add(new Point(500, 130));
        myBoard.add(new Point(610, 150));
        myBoard.add(new Point(720, 200));
        myBoard.add(new Point(840, 220));
        size = myBoard.size();

    }

    GameAdapter myController;
    private int chosenCard = 1;
    private boolean initialized = false;

@FXML    List<ImageView> turtles = new ArrayList<ImageView>();

    @FXML public void setOnStartPositions() {
        turtles.add(null);

        turtles.add(firstTurtle);
        turtles.add(secondTurtle);
        turtles.add(thirdTurtle);
        turtles.add(fourthTurtle);
        turtles.add(fifthTurtle);

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



    public void init(int numberOfBoard, GameAdapter myController) {
        if(!initialized) {

            this.myController = myController;


        }
    }

    public void updateBoard(List<List<Integer>> updateForBoard) {
        for(int i = 0; i<size; i++) {
            if(updateForBoard.get(i) != null && updateForBoard.get(i).size() != 0) {
                if(i == 0) {
                    for(int j = 0; j<updateForBoard.get(i).size(); j++) {
                        turtles.get(updateForBoard.get(i).get(j)).setY(startPositions.get(updateForBoard.get(i).get(j)).y);
                        turtles.get(updateForBoard.get(i).get(j)).setX(startPositions.get(updateForBoard.get(i).get(j)).x);
                        turtles.get(updateForBoard.get(i).get(j)).toBack();
                    }
                }
                int diff = (updateForBoard.get(i).size() - 1)*5;
                for(int j = updateForBoard.get(i).size()-1; j>=0; j--) {
                    turtles.get(updateForBoard.get(i).get(j)).setY(myBoard.get(i).y - diff + j*10);
                    turtles.get(updateForBoard.get(i).get(j)).setX(myBoard.get(i).x);
                    turtles.get(updateForBoard.get(i).get(j)).toFront();
                }
            }
        }
    }

    public void updateCard(int numberOfCard, int typeOfCard) {
        // tu następuje zamiana wybranej karty na inną
    }

    @FXML protected void showIt(ActionEvent event) {
        myController.playCard(chosenCard);

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
        final List<List<Integer>> myList = new ArrayList<List<Integer>>();
        for(int i = 1; i<=8; i++) {
            myList.add(new ArrayList<Integer>());
        }
        myList.get(1).add(3);
        myList.get(1).add(2);

        Platform.runLater(new Runnable() {

              @Override
              public void run() {
                  if (Platform.isFxApplicationThread()) updateBoard(myList);
              }
          }
        );
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
