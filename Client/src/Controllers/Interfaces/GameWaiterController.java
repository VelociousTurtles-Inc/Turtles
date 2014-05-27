package Controllers.Interfaces;

import Common.Interfaces.Event;

import java.rmi.RemoteException;

public interface GameWaiterController {
    void registerClosingEvent(Event closingEvent);

    void registerCancelEvent(Event cancelEvent);

    void registerUpdate(Event update);

    int getNumberOfPlayers();

    void leave() throws RemoteException;

    String getGameName();
}
