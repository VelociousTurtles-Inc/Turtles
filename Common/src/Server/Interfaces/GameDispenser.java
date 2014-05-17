package Server.Interfaces;

import java.rmi.Remote;

/**
 * Created by larhard on 15.05.14.
 */
public interface GameDispenser extends Remote {
    // TODO authenticate

    public GameStarter connectToGame(int id) throws Exception;
    public Integer createNewGame() throws Exception;
    public void leaveGame(int playerID) throws Exception;
    public void cancelGame() throws Exception;
    public void startGame() throws Exception;
}
