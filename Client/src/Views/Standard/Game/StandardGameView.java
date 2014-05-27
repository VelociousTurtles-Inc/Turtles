package Views.Standard.Game;

import Adapters.Interfaces.GameController;
import Enums.Colors;
import Common.Interfaces.Event;
import Images.ImageContainer;
import Images.Images;
import Model.Cards.Card;
import Utility.DebugWriter;
import Common.Interfaces.BoolRunnable;
import Utility.Utility;
import Views.Board;
import Views.BoardBootstrap;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.im.InputContext;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mz18 on 8/05/14.
 */
public class StandardGameView {
    // cards -> slots
    private List<ImageView> slots = new ArrayList<>();
    private List<ImageView> turtles = new ArrayList<>();
    private Board myBoard = BoardBootstrap.createSampleBoard();   //Board.readBoard("sampleBoard");
    private GameController gameController;

    private Stage stage;

    private ImageContainer imageContainer = new ImageContainer(this.getClass().getClassLoader(), "Resources/Images/Cards");
    private javafx.scene.control.Label winnerLabel;
    private Pane winnerPane;

    private void updateBoard(final List<List<Integer>> updateForBoard) {
        assert new BoolRunnable() {
            @Override
            public boolean run() {
                System.err.print("BoardUpdate : ");
                for (List<Integer> field : updateForBoard) {
                    System.err.print("[");
                    for (int i : field) {
                        System.err.print(Colors.asString(i) + ", ");
                    }
                    System.err.print("], ");
                }
                System.err.println();
                return true;
            }
        }.run();
        for(int i = 0; i<myBoard.size; i++) {
            if(updateForBoard.get(i) != null && updateForBoard.get(i).size() != 0) {
                List<Integer> temp = updateForBoard.get(i);
                Collections.reverse(temp);

                int diff = (updateForBoard.get(i).size() - 1) * 5;
                for (int j = updateForBoard.get(i).size() - 1; j >= 0; j--) {
                    turtles.get(temp.get(j)).setY(myBoard.positions.get(i).y - diff + j * 10);
                    turtles.get(temp.get(j)).setX(myBoard.positions.get(i).x);
                    turtles.get(temp.get(j)).toFront();
                }
            }
        }
    }

    private void updateCards(List<Card> cardsUpdate) {
        assert DebugWriter.write("Real Updating Cards", cardsUpdate);

        for (int i = 1; i <= 5; i++) {
            Card cardInfo = cardsUpdate.get(i-1);
            try {
                slots.get(i).setImage(imageContainer.get(cardInfo.getImageName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                        updateBoard(gameController.getBoard());
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
                cardInfos = gameController.getCards();
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

    private class CloseEvent implements Event {
        @Override
        public void call() {
            assert DebugWriter.write("Closing View");
            final Stage finalStage = stage;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    finalStage.close();
                }
            });
        }
    }

    public StandardGameView(final GameController gameController) {
        assert DebugWriter.write("Create new StandardGameView", gameController);

        this.gameController = gameController;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                start();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        gameController.registerUpdateBoardEvent(new BoardUpdater());
        gameController.registerUpdateCardsEvent(new CardsUpdater());
        gameController.registerCloseEvent(new CloseEvent());
        gameController.registerWinnerUpdateEvent(new WinnerUpdateEvent());
    }

    private class WinnerUpdateEvent implements Event {
        @Override
        public void call() {
            final Colors winner = gameController.getWinner();
            Utility.logInfo("GV");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    winnerLabel.setText(winner.toString());
                    winnerPane.setVisible(true);
                }
            });
        }
    }

    public void start() throws RemoteException {
        assert DebugWriter.write("Launching StandardGameView");
        stage = new Stage();

        FXMLLoader myLoader = new FXMLLoader();
        myLoader.setLocation(getClass().getResource("Game.fxml"));
        Parent game = null;
        try {
            game = (Parent) myLoader.load((getClass().getResource("Game.fxml")).openStream());
        } catch (IOException e) {

        }

        stage.setScene(new Scene(game));
        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                assert DebugWriter.write("handled CloseRequest from " + this);
                try {
                    gameController.leave();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        stage.show();

        StandardGameButtons myOwnGameButtons = myLoader.getController();
        myOwnGameButtons.init(gameController);

        turtles = myOwnGameButtons.getTurtles();
        slots = myOwnGameButtons.getCardSlots();

        winnerLabel = myOwnGameButtons.getWinnerLabel();
        winnerPane = myOwnGameButtons.getWinnerPane();

        for (int i : Colors.getRealIntegers()) {
            try {
                turtles.get(i).setImage(Images.load(this.getClass().getClassLoader(), "Resources/Images/Turtles/turtle" + Colors.asString(i) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            turtles.get(i).setX(myBoard.positions.get(i-1).x);
            turtles.get(i).setY(myBoard.positions.get(i-1).y);
        }
    }
}
