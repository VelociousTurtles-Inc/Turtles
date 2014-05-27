package Server.Interfaces;

import Client.Interfaces.GameClient;
import Model.Board.Board;
import Model.Cards.Card;
import org.cojen.dirmi.Asynchronous;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface PlayerService extends Remote {
    @Asynchronous
    void playCard(int cardID) throws RemoteException;

    Board getGameBoard() throws RemoteException;

    void setClient(GameClient myClient) throws RemoteException;

    List<Integer> getPlayerCards() throws RemoteException;

    Map<Integer,Card> getCardsMap() throws RemoteException;

    boolean isLocked() throws RemoteException;

    void leave() throws RemoteException;

    List<String> GetListOfPlayers() throws RemoteException;
}
