package Adapters;

import Handlers.UpdateHandler;
import Model.Board.BoardGraph;
import Model.Cards.Card;
import Services.GameService;
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
    private Collection<UpdateHandler<List<? extends Card>>> updateCardHandlers = new ArrayList<>();

    @Override
    public void addUpdateBoardHandler(UpdateHandler<BoardGraph> handler) {
        synchronized (updateBoardHandlers) {
            updateBoardHandlers.add(handler);
        }
        handler.update(gameService.getGameBoardGraph());
    }

    public void updateBoard() {
        BoardGraph boardGraph = gameService.getGameBoardGraph();
        for (UpdateHandler<BoardGraph> handler : updateBoardHandlers) {
            handler.update(boardGraph);
        }
    }

    @Override
    public void addUpdatePlayerCardHandler(UpdateHandler<List<? extends Card>> handler) {
        synchronized (updateCardHandlers) {
            updateCardHandlers.add(handler);
        }
        handler.update(gameService.getPlayerCards());
    }

    public void updatePlayerCards() {
        List<? extends Card> cards = gameService.getPlayerCards();
        for (UpdateHandler<List<? extends Card>> handler : updateCardHandlers) {
            handler.update(cards);
        }
    }

    private Lock playCardLock = new ReentrantLock();

    @Override
    public void playCard(Card card) {
        playCardLock.lock();
        gameService.playCard(card);
        System.out.println("Card " + card + " played");
        playCardLock.unlock();
    }
}
