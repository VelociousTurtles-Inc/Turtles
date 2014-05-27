package Server.Interfaces;

import Client.Interfaces.GameSelectClient;
import Client.Interfaces.GameWaiterClient;
import Client.Interfaces.ThreeStringsGet;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface WaiterService extends Remote {
    // Methods for server
    void ping() throws RemoteException;
    void updateWaiter(int numberOfPlayers) throws Exception;
    void update(ThreeStringsGet myTSG) throws Exception;
    void start(PlayerService playerService) throws Exception;

    String getName() throws RemoteException;

    // Methods for client
    GameManager connectToGame(int id, GameWaiterClient mySel) throws Exception;
    public Integer createNewGame(String name, GameWaiterClient mySel) throws Exception;
    void leaveGame() throws Exception;
    void cancelGame() throws Exception;

    void closeMe() throws RemoteException;
    public void setGameSelector(GameSelectClient mySel) throws Exception;
    public String getGameName() throws Exception;
    void startGame() throws Exception;
    void updateMe() throws Exception;

    void cancel() throws Exception;
}
