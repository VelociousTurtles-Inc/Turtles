package Adapters;

import Handlers.UpdateHandler;
import Model.Board.BoardGraph;
import Model.Cards.Card;

import java.util.List;

/**
 * Created by larhard on 05.05.14.
 */
public interface GameAdapter {
    public void addUpdateBoardHandler(UpdateHandler<BoardGraph> handler);

    public void addUpdatePlayerCardHandler(UpdateHandler<List<? extends Card>> handler);

    public void playCard(Card card);

    public void close(); // clear
}
