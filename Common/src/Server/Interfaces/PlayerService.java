package Server.Interfaces;

import Client.Interfaces.GameClient;
import Interfaces.IBoard;
import Model.Board.Board;
import Model.Cards.Card;
import org.cojen.dirmi.Asynchronous;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface PlayerService extends Remote {
    @Asynchronous
    void playCard(int cardID) throws RemoteException;

    Board getGameBoard() throws RemoteException;

    void setClient(GameClient myClient) throws Exception;


    List<Integer> getPlayerCards() throws RemoteException;

    Map<Integer,Card> getCardsMap() throws Exception;

    @Asynchronous
    void update() throws RemoteException;
}
