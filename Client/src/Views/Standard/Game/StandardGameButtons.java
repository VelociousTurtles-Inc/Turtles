package Views.Standard.Game;

import Common.Interfaces.Event;
import Controllers.Interfaces.GameController;
import Enums.Colors;
import Images.ImageContainer;
import Images.Images;
import Utility.DebugWriter;
import Views.Standard.PlayerNode.PlayerNodeControl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class StandardGameButtons {

    public Button playItButton;
    public VBox myBox;
    private GameController myGameController;

    @FXML private ImageView myTurtleColor;

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

    @FXML private TextArea inputChatArea;
    @FXML private TextArea outputChatArea;
    @FXML private Button sendChatButton;
    private boolean sendButtonDisabled = true;

    @FXML private Label winner;
    @FXML private Pane winnerPane;

    @FXML private Button firstCard;
    @FXML private Button secondCard;
    @FXML private Button thirdCard;
    @FXML private Button fourthCard;
    @FXML private Button fifthCard;
    private ImageContainer imgContainer;

    @FXML protected String getChatInput() {
        String temp = inputChatArea.getText();
        inputChatArea.clear();
        return temp;
    }

    public TextArea getOutputChatArea() {
        return outputChatArea;
    }

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

    final List<PlayerNodeControl> myPlayers = new LinkedList<>();

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

    private int chosenCard = 0;
    private final AtomicBoolean locked = new AtomicBoolean();

    void checkSendability() {
        if (inputChatArea.getText().isEmpty() != sendButtonDisabled) {
            sendButtonDisabled = inputChatArea.getText().isEmpty();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    sendChatButton.setDisable(sendButtonDisabled);
                }
            });
        }
    }

    public void init(final GameController myGameController, final ImageContainer imgContainer) throws RemoteException {
        this.myGameController = myGameController;
        this.imgContainer = imgContainer;

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
                        myPlayers.get(lastMoving).setNotOnMove();
                        try {
                            myPlayers.get(lastMoving).setLastPlayedCard(imgContainer.get(myGameController.getLastCard()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        lastMoving = myGameController.getLastMoving();
                        myPlayers.get(lastMoving).setOnMove();
                    }
                });
            }
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setMyTurtleColor(myGameController.getTurtleColor());
            }
        });

        inputChatArea.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED && keyEvent.isShiftDown()) {
                        inputChatArea.appendText("\n");
                    } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                        try {
                            postMessage();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    keyEvent.consume();
                }
                checkSendability();
            }
        });

        playItButton.setDisable(myGameController.isLocked());
        List<String> players = myGameController.getPlayers();
        //myBox.getChildren().add(new Label("something"));
        for(final String player : players) {
            myPlayers.add(new PlayerNodeControl(player));
        }
        for(final PlayerNodeControl wow : myPlayers) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    myBox.getChildren().add(wow);
                }
            });
        }
        myPlayers.get(0).setOnMove();
    }

    @FXML protected void surrenderIt(ActionEvent event) {
        assert DebugWriter.write("Surrender");
        myGameController.surrender();
    }

    @FXML protected void playIt(ActionEvent event) {
        assert DebugWriter.write("Play Card " + chosenCard);
        if (chosenCard > 0) {
            try {
                //myGameController.playCard(chosenCard);
                //
                // MB: later we use chosenCard as number in list,
                //     so we should decrease chosenCard by 1:

                myGameController.playCard(chosenCard - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void resetButtonEffects() {
        firstCard.setEffect(null);
        secondCard.setEffect(null);
        thirdCard.setEffect(null);
        fourthCard.setEffect(null);
        fifthCard.setEffect(null);
    }

    @FXML protected void chooseFirst(ActionEvent event) {
        assert DebugWriter.write("Chosed 1st Card");
        resetButtonEffects();
        firstCard.setEffect(new DropShadow());
        chosenCard = 1;
    }
    @FXML protected void chooseSecond(ActionEvent event) {
        assert DebugWriter.write("Chosed 2nd Card");
        resetButtonEffects();
        secondCard.setEffect(new DropShadow());
        chosenCard = 2;
    }
    @FXML protected void chooseThird(ActionEvent event) {
        assert DebugWriter.write("Chosed 3rd Card");
        resetButtonEffects();
        thirdCard.setEffect(new DropShadow());
        chosenCard = 3;
    }
    @FXML protected void chooseFourth(ActionEvent event) {
        assert DebugWriter.write("Chosed 4th Card");
        resetButtonEffects();
        fourthCard.setEffect(new DropShadow());
        chosenCard = 4;
    }
    @FXML protected void chooseFifth(ActionEvent event) {
        assert DebugWriter.write("Chosed 5th Card");
        resetButtonEffects();
        fifthCard.setEffect(new DropShadow());
        chosenCard = 5;
    }

    @FXML public void sendTextToServer(ActionEvent actionEvent) throws RemoteException {
        assert DebugWriter.write("Message sent to server");
        postMessage();
    }

    private void postMessage() throws RemoteException {
        String message = getChatInput();
        if (!message.isEmpty()) {
            myGameController.postMessage(message);
        }
        checkSendability();
    }

    public Pane getWinnerPane() {
        return winnerPane;
    }

    public Label getWinnerLabel() {
        return winner;
    }

    private void setMyTurtleColor(Colors color)
    {
        System.out.println("Resources/Images/Turtles/new" + Colors.asString(color) + ".png");
        System.out.println(myTurtleColor);
        try {
            myTurtleColor.setImage(Images.load(this.getClass().getClassLoader(), "Resources/Images/Turtles/new" + Colors.asString(color) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
