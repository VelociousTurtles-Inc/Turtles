package Client.Interfaces;

import org.cojen.dirmi.Asynchronous;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameClient extends Remote {
    @Asynchronous
    void updateCards() throws RemoteException;

    @Asynchronous
    void updateBoards() throws RemoteException;

    @Asynchronous
    void updateChat() throws RemoteException;

    @Asynchronous
    void updateLock() throws RemoteException;

    @Asynchronous
    void cardsPlayed() throws RemoteException;

    @Asynchronous
    void close() throws RemoteException;

    void ping() throws RemoteException;

    void setPlayerOnMove(int playerOnMove) throws RemoteException;
    @Asynchronous
    void announceWinner(Integer winner) throws RemoteException;
}
