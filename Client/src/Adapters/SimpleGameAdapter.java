package Adapters;

import Model.Cards.Card;
import Handlers.UpdateHandler;
import ServicesTypes.BoardGraph;
import ServicesTypes.GameService;
import Views.GameView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by larhard on 05.05.14.
 */
public class SimpleGameAdapter extends Thread implements GameAdapter {

    private final GameService gameService;
    private Collection<? extends GameView> gameViews;
    private final String[] args;

    public SimpleGameAdapter(Collection<? extends GameView> gameViews, GameService gameService, String[] args) {
        this.gameService = gameService;
        this.gameViews = gameViews;
        this.args = args;

        if (gameViews != null) {
            for (GameView view : gameViews) {
                view.init(this, args);
            }
        }
    }

    // TODO online view connecting

    @Override
    public void close() {
        for (GameView view : gameViews) {
            view.close();
        }
        gameViews.clear();
    }

    private Collection<UpdateHandler<BoardGraph>> updateBoardHandlers = new ArrayList<>();
    private Collection<UpdateHandler<List<Integer>>> updateCardHandlers = new ArrayList<>();

    @Override
    public void addUpdateBoardHandler(UpdateHandler<BoardGraph> handler) {
        synchronized (updateBoardHandlers) {
            updateBoardHandlers.add(handler);
        }
        handler.update(gameService.getGameBoardGraph());
    }

    public void updateBoard() {
        ServicesTypes.BoardGraph boardGraph = gameService.getGameBoardGraph();
        for (UpdateHandler<BoardGraph> handler : updateBoardHandlers) {
            handler.update(boardGraph);
        }
    }

    @Override
    public void addUpdatePlayerCardHandler(UpdateHandler<List<Integer>> handler) {
        synchronized (updateCardHandlers) {
            updateCardHandlers.add(handler);
        }
        handler.update(gameService.getPlayerCards());
    }

    public void updatePlayerCards() {
        List<Integer> cards = gameService.getPlayerCards();
        for (UpdateHandler<List<Integer>> handler : updateCardHandlers) {
            handler.update(cards);
        }
    }

    private Lock playCardLock = new ReentrantLock();

    @Override
    public void playCard(Integer cardID) {
        playCardLock.lock();
        gameService.playCard(cardID);
        System.out.println("Card " + cardID + " played");
        playCardLock.unlock();
    }
}
