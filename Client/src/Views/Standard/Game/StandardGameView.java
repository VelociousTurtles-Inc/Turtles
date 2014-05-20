package Views.Standard.Game;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameController;
import Colors.Colors;
import Model.Cards.Card;
import Utility.DebugWriter;
import Views.Board;
import Views.BoardBootstrap;
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

    // cards -> slots
    private List<ImageView> slots = new ArrayList<>();
    private List<ImageView> turtles = new ArrayList<>();
    private Board myBoard = BoardBootstrap.createSampleBoard();
    private GameController myGameController;
    private Map<String, Image> cardImages;

    private void updateBoard(List<List<Integer>> updateForBoard) {
        assert DebugWriter.write("Real Updating Board", updateForBoard.toArray());
        System.out.println(updateForBoard);
        for(int i = 0; i<myBoard.size; i++) {
            if(updateForBoard.get(i) != null && updateForBoard.get(i).size() != 0) {
                List<Integer> temp = updateForBoard.get(i);
                Collections.reverse(temp);
                if(i == 0) {
                    for(int j = 0; j<temp.size(); j++) {
                        turtles.get(temp.get(j)).setY(myBoard.startPositions.get(temp.get(j)).y);
                        turtles.get(temp.get(j)).setX(myBoard.startPositions.get(temp.get(j)).x);
                        turtles.get(temp.get(j)).toFront();
                    }
                } else {
                    int diff = (updateForBoard.get(i).size() - 1) * 5;
                    for (int j = updateForBoard.get(i).size() - 1; j >= 0; j--) {
                        turtles.get(temp.get(j)).setY(myBoard.positions.get(i).y - diff + j * 10);
                        turtles.get(temp.get(j)).setX(myBoard.positions.get(i).x);
                        turtles.get(temp.get(j)).toFront();
                    }
                }
            }
        }
    }

    private Map<String, Image> loadCardImages(String dir) {
        // TODO load files from jar
        // InputStream stream = Client.class.getClassLoader().getResource("Views/Images/Cards/Backwards0.png").openStream();
        // new Image(stream);
        // stream.close();
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

    private void updateCards(List<Card> cardsUpdate) {
        assert DebugWriter.write("Real Updating Cards", cardsUpdate);

        for (int i = 1; i <= 5; i++) {
            Card cardInfo = cardsUpdate.get(i-1);
            slots.get(i).setImage(cardImages.get(cardInfo.getType() + Colors.asString(cardInfo.getColor()) + ".png"));
        }
    }

    private class BoardUpdater implements Event {
        @Override
        public void call() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    assert DebugWriter.write("Board Updating");
                    try {
                        updateBoard(myGameController.getBoard());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private class CardsUpdater implements Event {
        @Override
        public void call() {
            assert DebugWriter.write("Cards Updating");
            final List<Card> cardInfos;
            try {
                cardInfos = myGameController.getCards();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateCards(cardInfos);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public StandardGameView(final GameController myGameController) {
        assert DebugWriter.write("Create new StandardGameView", myGameController);

        this.myGameController = myGameController;

        cardImages = loadCardImages(this.getClass().getClassLoader().getResource("Resources/Images/Cards").getFile());
        assert cardImages != null;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                start();
            }
        });

        myGameController.registerUpdateBoardEvent(new BoardUpdater());
        myGameController.registerUpdateCardsEvent(new CardsUpdater());
    }

    public void start() {
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
        slots = myOwnGameButtons.getCardSlots();

        for(int i = 1; i<=5; i++) {
            turtles.get(i).setX(myBoard.startPositions.get(i).x);
            turtles.get(i).setY(myBoard.startPositions.get(i).y);
        }
    }
}
