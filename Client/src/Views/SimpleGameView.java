package Views;

import Adapters.GameAdapter;
import Handlers.UpdateHandler;
import Model.Cards.Card;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by larhard on 05.05.14.
 */
public class SimpleGameView extends Application implements GameView {
    List<? extends List<Integer>> gameBoard = null;
    private GameAdapter adapter;

    @Override
    public void init(GameAdapter adapter, String[] args) {
        this.adapter = adapter;
        launch(args);

        adapter.addUpdateBoardHandler(new UpdateHandler<List<? extends List<Integer>>>() {
            @Override
            public synchronized void update(List<? extends List<Integer>> gameBoard) {
                SimpleGameView.this.gameBoard = gameBoard;
                // update view
            }
        });

        adapter.addUpdateCardHandler(new UpdateHandler<Card> () {
            @Override
            public synchronized void update(Card data) {
                // update view
            }
        });
    }

    @Override
    public void close() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage GameStage = new Stage();
        FXMLLoader myLoader = new FXMLLoader();
        Parent game = myLoader.load(getClass().getResource("Game.fxml"));

        GameStage.setScene(new Scene(game));
        GameStage.setResizable(false);

        GameStage.show();
    }
}
