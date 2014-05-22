package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameSelectController;
import Client.Interfaces.GameSelectClient;
import Client.Interfaces.ThreeStringsGet;
import Main.Client;
import Model.SimplestGameInfo;
import Server.Interfaces.GameDispenser;
import org.cojen.dirmi.Environment;
import org.cojen.dirmi.Session;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class StandardGameSelectController implements GameSelectController, GameSelectClient {

    private GameDispenser myGameDispenser;
    private List<SimplestGameInfo> simpleGameInfos;

    private final List<Event> cancelEvents = new ArrayList<>();
    private final List<Event> updateEvents = new ArrayList<>();

    public StandardGameSelectController() throws Exception {
        simpleGameInfos = new LinkedList<>();

        Environment environment = new Environment();
        Session session = environment.newSessionConnector(Client.getHost(), Client.getPort()).connect();

        myGameDispenser = (GameDispenser) session.receive();
        myGameDispenser.registerGameSelector(this);

        Client.scenario.invoke(GameSelectController.class, this);
    }

    @Override
    public void join(int gameID) throws Exception {
        StandardGameWaiterController mySGWC = new StandardGameWaiterController(gameID, myGameDispenser);
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
        StandardGameCreatorController mySGCC = new StandardGameCreatorController(myGameDispenser);
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
    public List<SimplestGameInfo> getUpdateList() {
        return simpleGameInfos;
    }

    @Override
    public void initValues() throws Exception {
        myGameDispenser.updateMe();
    }

}
