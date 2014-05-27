package Adapters.Interfaces;

import Common.Interfaces.Event;

import java.rmi.RemoteException;

public interface GameCreatorController {

    void registerClosingEvent(Event closingEvent);

    void create(String s) throws RemoteException;
}
