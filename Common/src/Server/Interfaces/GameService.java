package Server.Interfaces;

import Model.Cards.Card;

import java.rmi.Remote;
import java.util.List;
import java.util.Map;

/**
 * Created by larhard on 15.05.14.
 */
public interface GameService extends Remote {
    public Map<Integer, Card> getDeckMap(int gameID) throws Exception;
    public Interfaces.IBoard getGameBoard(int gameID) throws Exception;
    //public GameInfo getGameState(int gameID) throws Exception;
    public List<Integer> getPlayerCards(int gameID, int playerID) throws Exception;
    public void playCard(int cardID, int gameID, int playerID) throws Exception;
    public int newGame();
    public void joinGame(int gameID);
    public void startGame(int gameID);
}
