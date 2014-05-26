package Views.Standard.Game;

import Events.Event;
import Adapters.Interfaces.GameController;
import Utility.DebugWriter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mz18 on 8/05/14.
 */
public class StandardGameButtons {

    public Button playItButton;
    public VBox myBox;
    private GameController myGameController;

    @FXML private ImageView firstTurtle;
    @FXML private ImageView secondTurtle;
    @FXML private ImageView thirdTurtle;
    @FXML private ImageView fourthTurtle;
    @FXML private ImageView fifthTurtle;

    @FXML private ImageView firstCardImage;
    @FXML private ImageView secondCardImage;
    @FXML private ImageView thirdCardImage;
    @FXML private ImageView fourthCardImage;
    @FXML private ImageView fifthCardImage;

    List<ImageView> getCardSlots() {
        List<ImageView> cards = new ArrayList<>();

        cards.add(null);
        cards.add(firstCardImage);
        cards.add(secondCardImage);
        cards.add(thirdCardImage);
        cards.add(fourthCardImage);
        cards.add(fifthCardImage);

        return cards;
    }

    List<Label> myPlayers = new LinkedList<>();

    private int lastMoving = 0;

    List<ImageView> getTurtles() {
        List<ImageView> turtles = new ArrayList<>();

        turtles.add(null);
        turtles.add(firstTurtle);
        turtles.add(secondTurtle);
        turtles.add(thirdTurtle);
        turtles.add(fourthTurtle);
        turtles.add(fifthTurtle);

        return turtles;
    }

    private int chosenCard = 1;
    private final AtomicBoolean locked = new AtomicBoolean();

    public void init(final GameController myGameController) throws RemoteException {
        this.myGameController = myGameController;

        myGameController.registerLockingEvent(new Event() {
            @Override
            public void call() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (locked) {
                            locked.set(myGameController.isLocked());
                            playItButton.setDisable(locked.get());
                        }
                    }
                });
            }
        });
        myGameController.registerChangeMovingPlayerEvent(new Event() {
            @Override
            public void call() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        myPlayers.get(lastMoving).setTextFill(Color.BLACK);
                        lastMoving = myGameController.getLastMoving();
                        myPlayers.get(lastMoving).setTextFill(Color.GREEN);
                    }
                });
            }
        });
        playItButton.setDisable(myGameController.isLocked());
        List<String> players = myGameController.getPlayers();
        //myBox.getChildren().add(new Label("something"));
        for(final String player : players) {
            myPlayers.add(new Label(player));
        }
        for(final Label wow : myPlayers) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    myBox.getChildren().add(wow);
                }
            });
        }
        myPlayers.get(0).setTextFill(Color.GREEN);
    }

    @FXML protected void surrIt(ActionEvent event) {
        assert DebugWriter.write("Surrender");
        myGameController.surrender();
    }

    @FXML protected void playIt(ActionEvent event) {
        assert DebugWriter.write("Play Card " + chosenCard);
        try {
            //myGameController.playCard(chosenCard);
            //
            // MB: later we use chosenCard as number in list,
            //     so we should decrease chosenCard by 1:

            myGameController.playCard(chosenCard-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML protected void chooseFirst(ActionEvent event) {
        assert DebugWriter.write("Chosed 1st Card");
        chosenCard = 1;
    }
    @FXML protected void chooseSecond(ActionEvent event) {
        assert DebugWriter.write("Chosed 2nd Card");
        chosenCard = 2;
    }
    @FXML protected void chooseThird(ActionEvent event) {
        assert DebugWriter.write("Chosed 3rd Card");
        chosenCard = 3;
    }
    @FXML protected void chooseFourth(ActionEvent event) {
        assert DebugWriter.write("Chosed 4th Card");
        chosenCard = 4;
    }
    @FXML protected void chooseFifth(ActionEvent event) {
        assert DebugWriter.write("Chosed 5th Card");
        chosenCard = 5;
    }

}
