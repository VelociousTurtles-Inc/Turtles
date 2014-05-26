package Server.Interfaces;

import Client.Interfaces.GameWaiterClient;

/**
 * Created by larhard on 15.05.14.
 */
public interface GameDispenser {
    // TODO authenticate

    GameManager connectToGame(int id, GameWaiterClient mySel) throws Exception;
    public Integer createNewGame(String name, GameWaiterClient mySel) throws Exception;
    void leaveGame(int gameID, GameWaiterClient mySel) throws Exception;
    void cancelGame(int gameID) throws Exception;
    public String getGameName(int gameID) throws Exception;

    void updateMe() throws Exception;

    void startGame(int gameID) throws Exception;
}
