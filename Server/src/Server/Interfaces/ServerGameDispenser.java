package Server.Interfaces;

import Common.Interfaces.Event;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Created by larhard on 25.05.14.
 */
public interface ServerGameDispenser {
    void registerCloseEvent(Event event);
    void close();

    void update() throws RemoteException;

    Collection<GameManager> getGameManagers();
    void cancelGame(int gameID);
}
