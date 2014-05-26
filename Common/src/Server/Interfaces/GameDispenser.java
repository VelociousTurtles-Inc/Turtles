package Server.Interfaces;

import Client.Interfaces.GameSelectClient;
import Client.Interfaces.GameWaiterClient;
import Events.Event;

import java.rmi.Remote;
import java.util.Collection;

/**
 * Created by larhard on 15.05.14.
 */
public interface GameDispenser {
    // TODO authenticate

    void leaveGame(int gameID, WaiterService mySel) throws Exception;
    void cancelGame(int gameID) throws Exception;

    GameManager connectToGame(int id, WaiterService mySel) throws Exception;

    Integer createNewGame(String name, WaiterService mySel) throws Exception;

    public String getGameName(int gameID) throws Exception;

    void updateMe() throws Exception;

    void startGame(int gameID) throws Exception;
}
