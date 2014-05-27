package Controllers.Interfaces;

import Common.Interfaces.Event;

import java.rmi.RemoteException;

public interface GameCreatorWaiterController {
    void registerClosingEvent(Event closingEvent);

    void registerUpdateEvent(Event updateEvent);

    void startAll() throws RemoteException;

    void cancelAll() throws RemoteException;

    int getNumberOfPlayers();

    void registerCancelEvent(Event cancelEvent);

    String getGameName();

    int getGameID();

    void initValues() throws RemoteException;
}
