package Adapters;

import Handlers.UpdateHandler;
<<<<<<< HEAD
import Model.Board.BoardGraph;
import Model.Cards.Card;
=======
import ServicesTypes.BoardGraph;

>>>>>>> origin/DangerouslyManyChangesToCheck

import java.util.List;

/**
 * Created by larhard on 05.05.14.
 */

public interface GameAdapter {
    public void addUpdateBoardHandler(UpdateHandler<BoardGraph> handler);

<<<<<<< HEAD
    public void addUpdatePlayerCardHandler(UpdateHandler<List<? extends Card>> handler);

    public void playCard(Card card);
=======
    public void addUpdatePlayerCardHandler(UpdateHandler<List<Integer>> handler);

    public void playCard(Integer card);
>>>>>>> origin/DangerouslyManyChangesToCheck

    public void close(); // clear
}
