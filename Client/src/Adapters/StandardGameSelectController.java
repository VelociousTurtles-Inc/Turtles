package Adapters;

import Events.Event;
import Adapters.Interfaces.GameSelectController;
import Client.Interfaces.GameSelectClient;
import Client.Interfaces.ThreeStringsGet;
import Main.Client;
import Model.GameInfo;
import Server.Interfaces.WaiterService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class StandardGameSelectController implements GameSelectController, GameSelectClient {

    private WaiterService myWaiter;
    private List<GameInfo> simpleGameInfos;

    private final List<Event> cancelEvents = new ArrayList<>();
    private final List<Event> updateEvents = new ArrayList<>();

    public StandardGameSelectController(WaiterService waiter) throws Exception {
        simpleGameInfos = new LinkedList<>();
        myWaiter = waiter;
        myWaiter.setGameSelector(this);
        Client.scenario.invoke(GameSelectController.class, this);
    }

    @Override
    public void join(int gameID) throws Exception {
        StandardGameWaiterController mySGWC = new StandardGameWaiterController(gameID, myWaiter);
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
        StandardGameCreatorController mySGCC = new StandardGameCreatorController(myWaiter);
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
    public void update(ThreeStringsGet updateGameInfo) throws Exception {
        simpleGameInfos = updateGameInfo.getList();
        synchronized (updateEvents) {
            for (Event e : updateEvents) {
                e.call();
            }
        }
    }

    @Override
    public List<GameInfo> getUpdateList() {
        return simpleGameInfos;
    }

    @Override
    public void initValues() throws Exception {
        myWaiter.updateMe();
    }

}
