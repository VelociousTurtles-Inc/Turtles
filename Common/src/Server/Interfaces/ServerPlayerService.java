package Server.Interfaces;

import java.rmi.RemoteException;

/**
 * Created by larhard on 24.05.14.
 */
public interface ServerPlayerService {
    void close();

    void lock();

    void unlock();

    void update();

    boolean checkZombieness();

    boolean isZombie();

    String getName();

    void setPlayerOnMove(int playerOnMove) throws RemoteException;
}
