package Server.Interfaces;

import Client.Interfaces.GameSelectClient;
import Client.Interfaces.GameWaiterClient;

import java.rmi.Remote;


public interface WaiterService extends Remote {

    GameManager connectToGame(int id, GameWaiterClient mySel) throws Exception;
    public Integer createNewGame(String name, GameWaiterClient mySel) throws Exception;
//    void unregisterGameSelector(GameSelectClient mySelector) throws Exception;
    void leaveGame(int gameID, GameWaiterClient mySel) throws Exception;
    void cancelGame(int gameID) throws Exception;
    public void setGameSelector(GameSelectClient mySel) throws Exception;
    public String getGameName(int gameID) throws Exception;

    void updateMe() throws Exception;

    void startGame(int gameID) throws Exception;
}
