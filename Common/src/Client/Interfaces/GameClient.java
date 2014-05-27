package Client.Interfaces;

import org.cojen.dirmi.Asynchronous;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by larhard on 16.05.14.
 */
public interface GameClient extends Remote {
    @Asynchronous
    void updateCards() throws RemoteException;

    @Asynchronous
    void updateBoards() throws RemoteException;

    @Asynchronous
    void updateLock() throws RemoteException;

    @Asynchronous
    void cardsPlayed() throws RemoteException;

    @Asynchronous
    void close() throws RemoteException;

    void ping() throws RemoteException;

    @Asynchronous
    void announceWinner(Integer winner) throws RemoteException;
}
