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

public class GraphicGameView implements GVCInterface {

    @FXML private ImageView firstTurtle;
    @FXML private ImageView secondTurtle;
    @FXML private ImageView thirdTurtle;
    @FXML private ImageView fourthTurtle;
    @FXML private ImageView fifthTurtle;

    private Board myBoard = Board.readBoard("sample board");

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

        firstTurtle.setX(myBoard.startPositions.get(1).x);
        firstTurtle.setY(myBoard.startPositions.get(1).y);
        secondTurtle.setX(myBoard.startPositions.get(2).x);
        secondTurtle.setY(myBoard.startPositions.get(2).y);
        thirdTurtle.setX(myBoard.startPositions.get(3).x);
        thirdTurtle.setY(myBoard.startPositions.get(3).y);
        fourthTurtle.setX(myBoard.startPositions.get(4).x);
        fourthTurtle.setY(myBoard.startPositions.get(4).y);
        fifthTurtle.setX(myBoard.startPositions.get(5).x);
        fifthTurtle.setY(myBoard.startPositions.get(5).y);
    }



    public void init(int numberOfBoard, GameAdapter myController) {
        if(!initialized) {

            this.myController = myController;


        }
    }

    public void updateBoard(List<List<Integer>> updateForBoard) {
        for(int i = 0; i<myBoard.size; i++) {
            if(updateForBoard.get(i) != null && updateForBoard.get(i).size() != 0) {
                if(i == 0) {
                    for(int j = 0; j<updateForBoard.get(i).size(); j++) {
                        turtles.get(updateForBoard.get(i).get(j)).setY(myBoard.startPositions.get(updateForBoard.get(i).get(j)).y);
                        turtles.get(updateForBoard.get(i).get(j)).setX(myBoard.startPositions.get(updateForBoard.get(i).get(j)).x);
                        turtles.get(updateForBoard.get(i).get(j)).toBack();
                    }
                }
                int diff = (updateForBoard.get(i).size() - 1)*5;
                for(int j = updateForBoard.get(i).size()-1; j>=0; j--) {
                    turtles.get(updateForBoard.get(i).get(j)).setY(myBoard.positions.get(i).y - diff + j*10);
                    turtles.get(updateForBoard.get(i).get(j)).setX(myBoard.positions.get(i).x);
                    turtles.get(updateForBoard.get(i).get(j)).toFront();
                }
            }
        }
    }

    public void updateCard(int numberOfCard, int typeOfCard) {
        // tu następuje zamiana wybranej karty na inną
    }

    @FXML protected void playIt(ActionEvent event) {
        //myController.playCard(chosenCard);
//    @FXML protected void showIt(ActionEvent event) {
////        myController.playCard(chosenCard);

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
        myList.get(4).add(3);
        myList.get(4).add(2);
        myList.get(3).add(1);
        myList.get(3).add(2);

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
