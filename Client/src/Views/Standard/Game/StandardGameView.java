package Views.Standard.Game;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameController;
import ModelHelpers.DebugWriter;
import ServicesTypes.CardInfo;
import Views.Board;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by mz18 on 8/05/14.
 */
public class StandardGameView {

    private List<ImageView> cards = new ArrayList<ImageView>();
    private List<ImageView> turtles = new ArrayList<ImageView>();
    private Board myBoard = Board.readBoard("sample board");
    private GameController myGameController;
    private Map<String, Image> cardImages;

    private void updateBoard(List<List<Integer>> updateForBoard) {
        assert DebugWriter.write("Real Updating Board", updateForBoard.toArray());
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

    private Map<String, Image> loadCardImages(String dir) {
        assert DebugWriter.write("Loading Card Images from " + dir);
        Map<String, Image> result = new HashMap<>();
        for (File file : new File(dir).listFiles()) {
            assert DebugWriter.write("loading " + file.getName());
            try {
                result.put(file.getName(), new Image(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                assert DebugWriter.write("failed to load " + file.getName());
            }
        }
        return result;
    }

    private void updateCards(List<CardInfo> cardsUpdate) {
        assert DebugWriter.write("Real Updating Cards", cardsUpdate);


        for (int i = 1; i <= 5; i++) {
            CardInfo cardInfo = cardsUpdate.get(i);
            cards.get(i).setImage(cardImages.get(cardInfo.getType()+cardInfo.getColor()+".png"));
        }
    }

    private class BoardUpdater implements Event {
        @Override
        public void call() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    assert DebugWriter.write("Board Updating");
                    updateBoard(myGameController.getBoard());
                }
            });
        }
    }

    private class CardsUpdater implements Event {
        @Override
        public void call() {
            assert DebugWriter.write("Cards Updating");
            updateCards(myGameController.getCards());
        }
    }

    public StandardGameView(final GameController myGameController) {
        assert DebugWriter.write("Create new StandardGameView", myGameController);

        this.myGameController = myGameController;

        myGameController.registerUpdateBoardEvent(new BoardUpdater());
        myGameController.registerUpdateCardsEvent(new CardsUpdater());

        System.out.println(Arrays.toString(new File(".").listFiles()));
        cardImages = loadCardImages("./Client/src/Views/Images/Cards/");
        assert cardImages != null;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                assert DebugWriter.write("Launching StandardGameView");
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
                cards = myOwnGameButtons.getCards();

                for(int i = 1; i<=5; i++) {
                    turtles.get(i).setX(myBoard.startPositions.get(i).x);
                    turtles.get(i).setY(myBoard.startPositions.get(i).y);
                }
            }
        });
    }
}
