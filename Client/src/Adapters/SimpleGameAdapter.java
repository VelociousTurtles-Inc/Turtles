package Adapters;

// For now instantiating directly
import Handlers.UpdateHandler;
import Model.Cards.Card;
import Services.GameService;
import Views.GameView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by larhard on 05.05.14.
 */
public class SimpleGameAdapter implements GameAdapter {

    private Collection<? extends GameView> gameViews;

    public SimpleGameAdapter(Collection<? extends GameView> gameViews, String[] args) {
        this.gameViews = gameViews;
        GameService game = new GameService();
        for (GameView view : gameViews) {
            view.init(this, args);
        }
    }

    public void close() {
        for (GameView view : gameViews) {
            view.close();
        }
    }

    Collection<UpdateHandler<? extends List<? extends List<Integer>>>> updateBoardHandlers = new ArrayList<>();
    Collection<UpdateHandler<? extends Card>> updateCardHandlers = new ArrayList<>();

    @Override
    public void addUpdateBoardHandler(UpdateHandler<? extends List<? extends List<Integer>>> handler) {
        updateBoardHandlers.add(handler);
    }

    @Override
    public void addUpdateCardHandler(UpdateHandler<? extends Card> handler) {
        updateCardHandlers.add(handler);
    }

    @Override
    public void playCard(int card) {
        System.out.println("Card no " + card + " played");
    }
}
