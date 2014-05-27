package Controllers.Interfaces;

import Common.Interfaces.Event;
import Model.GameInfo;

import java.rmi.RemoteException;
import java.util.List;

public interface GameSelectController {
    public void join(int myID) throws RemoteException;
    public void cancel();
    public void create();
    public void registerClosingEvent(Event myClosingEvent);
    public void registerUpdateEvent(Event updateEvent);

    List<GameInfo> getUpdateList();

    void initValues() throws RemoteException;
}
