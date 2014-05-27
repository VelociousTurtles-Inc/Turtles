package Server.Interfaces;

import Enums.Colors;

import java.rmi.RemoteException;

public interface ServerPlayerService {
    void close();

    void lock();

    void unlock();

    void updateBoard();

    boolean checkZombieness();

    boolean isZombie();

    String getName();
    void announceWinner(Colors winner);

    void setPlayerOnMove(int playerOnMove) throws RemoteException;
}
