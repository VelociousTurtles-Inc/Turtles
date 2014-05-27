package Server.Interfaces;

import Client.Interfaces.GameSelectClient;
import Client.Interfaces.GameWaiterClient;
import Client.Interfaces.ThreeStringsGet;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WaiterService extends Remote {
    // Methods for server
    void ping() throws RemoteException;
    void updateWaiter(int numberOfPlayers) throws RemoteException;
    void update(ThreeStringsGet myTSG) throws RemoteException;
    void start(PlayerService playerService) throws RemoteException;

    String getName() throws RemoteException;

    // Methods for client
    GameManager connectToGame(int id, GameWaiterClient mySel) throws RemoteException;
    public Integer createNewGame(String name, GameWaiterClient mySel) throws RemoteException;
    void leaveGame() throws RemoteException;
    void cancelGame() throws RemoteException;

    void closeMe() throws RemoteException;
    public void setGameSelector(GameSelectClient mySel) throws RemoteException;
    public String getGameName() throws RemoteException;
    void startGame() throws RemoteException;
    void updateMe() throws RemoteException;

    void cancel() throws RemoteException;
}
