package Server.Interfaces;

import Enums.Colors;

import java.rmi.RemoteException;

public interface ServerPlayerService {
    void close();

    void lock();

    void unlock();

    void updateBoard();

    void checkZombieness();

    boolean isZombie();

    void updateChat(String a) throws RemoteException;

    String getName();
    void announceWinner(Colors winner);

    void setPlayerOnMove(int playerOnMove) throws RemoteException;

    void setLastCard(String lastPlayed) throws RemoteException;
}
