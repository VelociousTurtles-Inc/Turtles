package Server.Interfaces;

import Common.Interfaces.Event;

import java.rmi.RemoteException;
import java.util.Collection;

public interface ServerGameDispenser {
    void registerCloseEvent(Event event);
    void close();

    void update() throws RemoteException;

    Collection<GameManager> getGameManagers();
    void cancelGame(int gameID);
}
