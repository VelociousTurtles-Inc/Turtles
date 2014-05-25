package Server.Interfaces;

import Client.Interfaces.GameSelectClient;
import Client.Interfaces.GameWaiterClient;
import Events.Event;

import java.rmi.Remote;
import java.util.Collection;

/**
 * Created by larhard on 15.05.14.
 */
public interface GameDispenser extends Remote {
    // TODO authenticate

    GameManager connectToGame(int id, GameWaiterClient mySel) throws Exception;
    public Integer createNewGame(String name, GameWaiterClient mySel) throws Exception;
    void unregisterGameSelector(GameSelectClient mySelector) throws Exception;
    void leaveGame(int gameID, GameWaiterClient mySel) throws Exception;
    void cancelGame(int gameID) throws Exception;
    public void registerGameSelector(GameSelectClient mySel) throws Exception;
    public String getGameName(int gameID) throws Exception;

    void updateMe() throws Exception;

    void startGame(int gameID) throws Exception;
}
