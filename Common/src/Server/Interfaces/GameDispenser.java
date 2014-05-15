package Server.Interfaces;

import java.rmi.Remote;

/**
 * Created by larhard on 15.05.14.
 */
public interface GameDispenser extends Remote {
    // TODO authenticate

    GameService connectToGame(int id) throws Exception;
    Integer createNewGame() throws Exception;
}
