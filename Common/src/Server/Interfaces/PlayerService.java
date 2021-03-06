package Server.Interfaces;

import Chat.Message.Message;
import Chat.Message.NoSuchMessageException;
import Client.Interfaces.GameClient;
import Model.Cards.Card;
import org.cojen.dirmi.Asynchronous;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface PlayerService extends Remote {
    @Asynchronous
    void playCard(int cardID) throws RemoteException;

    Model.Board.BoardGraph getGameBoardGraph() throws RemoteException;

    void setClient(GameClient myClient) throws RemoteException;

    List<Integer> getPlayerCards() throws RemoteException;

    Map<Integer,Card> getCardsMap() throws RemoteException;

    boolean isLocked() throws RemoteException;

    int getTurtleColor() throws RemoteException;

    void leave() throws RemoteException;

    void postMessage(String a) throws RemoteException;

    List<String> GetListOfPlayers() throws RemoteException;

    LinkedList<Message> newChatMessages(int lastMessageId) throws RemoteException, NoSuchMessageException;
}
