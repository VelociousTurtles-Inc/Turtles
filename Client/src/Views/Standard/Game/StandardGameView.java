package Views.Standard.Game;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameController;
import Model.Cards.Card;
import Views.Standard.Point;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mz18 on 8/05/14.
 */
public class StandardGameView {

    private List<ImageView> turtles = new ArrayList<ImageView>();
    private List<Point> startPositions = new ArrayList<Point>();
    private List<Point> myBoard = new ArrayList<Point>();
    private int size;

    private GameController myGameController;


    private void updateBoard(List<List<Integer>> updateForBoard) {
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

    private void updateCards(List<Card> cardsUpdate) {

    }

    private class BoardUpdater implements Event {
        @Override
        public void call() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    updateBoard(myGameController.getBoard());
                }
            });
        }
    }

    private class CardsUpdater implements Event {
        @Override
        public void call() {
            updateCards(myGameController.getCards());
        }
    }

    public StandardGameView(final GameController myGameController) {

        this.myGameController = myGameController;

        myGameController.registerUpdateBoardEvent(new BoardUpdater());
        myGameController.registerUpdateCardsEvent(new CardsUpdater());

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage GameStage = new Stage();

                FXMLLoader myLoader = new FXMLLoader();
                myLoader.setLocation(getClass().getResource("Game.fxml"));
                Parent game = null;
                try {
                    game = (Parent) myLoader.load((getClass().getResource("Game.fxml")).openStream());
                } catch (IOException e) {

                }

                GameStage.setScene(new Scene(game));
                GameStage.setResizable(false);

                GameStage.show();

                StandardGameButtons myOwnGameButtons = myLoader.getController();
                myOwnGameButtons.init(myGameController);

                turtles = myOwnGameButtons.getTurtles();

                startPositions.add(new Point(42, 42));

                startPositions.add(new Point(60, 120));
                startPositions.add(new Point(45, 170));
                startPositions.add(new Point(60, 220));
                startPositions.add(new Point(45, 270));
                startPositions.add(new Point(60, 320));

                myBoard.add(new Point(42, 42));

                myBoard.add(new Point(170, 340));
                myBoard.add(new Point(280, 290));
                myBoard.add(new Point(380, 190));
                myBoard.add(new Point(500, 130));
                myBoard.add(new Point(610, 150));
                myBoard.add(new Point(720, 200));
                myBoard.add(new Point(840, 220));

                size = myBoard.size();

                for(int i = 1; i<=5; i++) {
                    turtles.get(i).setX(startPositions.get(i).x);
                    turtles.get(i).setY(startPositions.get(i).y);
                }
            }
        });
    }
}
