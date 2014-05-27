package Controllers;

import Client.Interfaces.ThreeStringsGet;
import Common.Interfaces.Event;
import Controllers.Interfaces.GameSelectController;
import Client.Interfaces.GameSelectClient;
import Main.Client;
import Model.GameInfo;
import Server.Interfaces.WaiterService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StandardGameSelectController implements GameSelectController, GameSelectClient {

    private final WaiterService waiterService;
    private List<GameInfo> gameInfoList;

    private final List<Event> cancelEvents = new ArrayList<>();
    private final List<Event> updateEvents = new ArrayList<>();

    public StandardGameSelectController(WaiterService waiter) throws RemoteException {
        gameInfoList = new LinkedList<>();
        waiterService = waiter;
        waiterService.setGameSelector(this);
        Client.scenario.invoke(GameSelectController.class, this);
    }

    @Override
    public void join(int gameID) throws RemoteException {
        new StandardGameWaiterController(gameID, waiterService);
    }

    @Override
    public void cancel() {
        synchronized (cancelEvents) {
            for (Event e : cancelEvents) {
                e.call();
            }
        }
    }

    @Override
    public void create() {
        new StandardGameCreatorController(waiterService);
    }

    @Override
    public void registerClosingEvent(Event myClosingEvent) {
        synchronized (cancelEvents) {
            cancelEvents.add(myClosingEvent);
        }
    }

    @Override
    public void registerUpdateEvent(Event myUpdateEvent) {
        synchronized (updateEvents) {
            updateEvents.add(myUpdateEvent);
        }
    }

    @Override
    public void update(ThreeStringsGet updateGameInfo) throws RemoteException {
        gameInfoList = updateGameInfo.getList();
        synchronized (updateEvents) {
            for (Event e : updateEvents) {
                e.call();
            }
        }
    }

    @Override
    public List<GameInfo> getUpdateList() {
        return gameInfoList;
    }

    @Override
    public void initValues() throws RemoteException {
        waiterService.updateMe();
    }

}
