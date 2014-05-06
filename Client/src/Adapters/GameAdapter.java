package Adapters;

import Handlers.UpdateHandler;
import Model.Cards.Card;

import java.util.List;

/**
 * Created by larhard on 05.05.14.
 */
public interface GameAdapter {
    public void addUpdateBoardHandler(UpdateHandler<? extends List<? extends List<Integer>>> handler);

    public void addUpdateCardHandler(UpdateHandler<? extends Card> handler);

    public void playCard(Card card);
}
