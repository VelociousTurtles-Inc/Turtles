package Server.Interfaces;

import Client.Interfaces.GameSelecter;
import Client.Interfaces.GameWaiter;

import java.rmi.Remote;

/**
 * Created by larhard on 15.05.14.
 */
public interface GameDispenser extends Remote {
    // TODO authenticate

    GameStarter connectToGame(int id, GameWaiter mySel) throws Exception;

    public Integer createNewGame(String name, GameWaiter mySel) throws Exception;

    void unregisterGameSelector(GameSelecter mySelector) throws Exception;

    void leaveGame(int gameID, GameWaiter mySel) throws Exception;

    public void cancelGame() throws Exception;
    public void startGame() throws Exception;
    public void registerGameSelector(GameSelecter mySel) throws Exception;

    public String getGameName(int gameID) throws Exception;
}
