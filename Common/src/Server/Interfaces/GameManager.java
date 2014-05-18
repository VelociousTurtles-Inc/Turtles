package Server.Interfaces;

import Client.Interfaces.GameWaiter;
import Client.Interfaces.SimpliestGameInfo;
import Interfaces.IBoard;
import Model.Cards.Card;

import java.rmi.Remote;
import java.util.Map;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface GameManager extends Remote {
    public IBoard getBoard() throws Exception;
    public int playCard(int cardID, int playerID) throws Exception;
    public Map<Integer, Card> getInGameCards() throws Exception;
    public void startGame() throws Exception;
    public boolean isStarted() throws Exception;
    public boolean isFull() throws Exception;
    public SimpliestGameInfo getGameInfo() throws Exception;
    void addPlayer(GameWaiter newWaiter) throws Exception;

    void removePlayer(GameWaiter oldWaiter) throws Exception;
    void setId(int id) throws Exception;
    public void update() throws Exception;

    void cancel() throws Exception;

}
