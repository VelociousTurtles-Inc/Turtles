package Adapters;

import Handlers.UpdateHandler;
import ServicesTypes.BoardGraph;


import java.util.List;

/**
 * Created by larhard on 05.05.14.
 */

public interface GameAdapter {
    public void addUpdateBoardHandler(UpdateHandler<BoardGraph> handler);

    public void addUpdatePlayerCardHandler(UpdateHandler<List<Integer>> handler);

    public void playCard(Integer card);

    public void close(); // clear
}
