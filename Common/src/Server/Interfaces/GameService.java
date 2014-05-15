package Server.Interfaces;

import Model.Board.BoardGraph;
import Model.Game.GameInfo;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by larhard on 15.05.14.
 */
public interface GameService extends Remote {
    public BoardGraph getGameBoardGraph() throws Exception;
    public GameInfo getGameState() throws Exception;
    public List<Integer> getPlayerCards() throws Exception;
    public void playCard(int cardID) throws Exception;
}
