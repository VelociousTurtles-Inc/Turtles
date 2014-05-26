package Server.Interfaces;

import Client.Interfaces.GameWaiterClient;
import Model.Board.Board;
import Model.Cards.Card;
import Model.SimplestGameInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface GameManager extends Remote {
    // TODO server side GameManager interface

    public Board getBoard() throws RemoteException;

    public int playCard(int cardID) throws RemoteException;

    void nextTurn() throws RemoteException;

    public Map<Integer, Card> getInGameCards() throws Exception;
    public void startGame() throws Exception;
    public boolean isStarted() throws Exception;
    public boolean isFull() throws Exception;
    public SimplestGameInfo getGameInfo() throws Exception;
    void addPlayer(GameWaiterClient newWaiter) throws Exception;

    void removePlayer(GameWaiterClient oldWaiter) throws Exception;
    void setId(int id) throws Exception;
    public void update() throws Exception;

    void cancel() throws Exception;

    List<Integer> getHand() throws RemoteException;

    void leave() throws RemoteException;

    void checkForZombies() throws RemoteException;

    void addZombie() throws RemoteException;
}
