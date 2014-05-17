package Client.Interfaces;

import java.rmi.Remote;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface ThreeStringsGet extends Remote {
    public String getGameName();
    public String getGameStatus();
    public String getNumberOfPlayers();
}
