package Adapters.Interfaces;

import Common.Interfaces.Event;

import java.rmi.RemoteException;

/**
 * Created by larhard on 20.05.14.
 */
public interface GameWaiterController {
    void registerClosingEvent(Event closingEvent);

    void registerCancelEvent(Event cancelEvent);

    void registerUpdate(Event update);

    int getNumberOfPlayers();

    void leave() throws RemoteException;

    String getGameName() throws RemoteException;
}
