package Server.Interfaces;

import Client.Interfaces.GameWaiter;
import Client.Interfaces.SimpliestGameInfo;
import Model.Board.Board;
import Model.Cards.Card;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface GameManager extends Remote {

    public Board getBoard() throws RemoteException;

    public int playCard(int cardID) throws RemoteException;
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

    List<Integer> getHand() throws RemoteException;
}
