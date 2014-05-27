package Adapters.Interfaces;

import Common.Interfaces.Event;

import java.rmi.RemoteException;

/**
 * Created by larhard on 20.05.14.
 */
public interface GameCreatorController {

    void registerClosingEvent(Event closingEvent);

    void create(String s) throws RemoteException;
}
