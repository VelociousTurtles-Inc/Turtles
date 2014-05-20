package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameSelectController;
import Client.Interfaces.GameSelectClient;
import Client.Interfaces.SimpliestGameInfo;
import Client.Interfaces.ThreeStringsGet;
import Main.Client;
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

    GameDispenser myGameDispenser;
    List<SimpliestGameInfo> simpleGameInfos;

    List<Event> endIt = new ArrayList<>();
    List<Event> updateIt = new ArrayList<>();

    public StandardGameSelectController() throws Exception {
        simpleGameInfos = new LinkedList<>();

        Client.scenario.invoke(GameSelectController.class, this);

        Environment environment = new Environment();
        Session session = environment.newSessionConnector(Client.getHost(), Client.getPort()).connect();

        myGameDispenser = (GameDispenser) session.receive();
        myGameDispenser.registerGameSelector(this);

    }

    @Override
    public void join(int gameID) throws Exception {
        StandardGameWaiterController mySGWC = new StandardGameWaiterController(gameID, myGameDispenser);
    }

    @Override
    public void cancel() {
        for (Event e : endIt) {
            e.call();
        }
    }

    @Override
    public void create() {
        StandardGameCreatorController mySGCC = new StandardGameCreatorController(myGameDispenser);
    }

    @Override
    public void registerClosingEvent(Event myClosingEvent) {
        endIt.add(myClosingEvent);
    }

    @Override
    public void registerUpdateEvent(Event myUpdateEvent) {
        updateIt.add(myUpdateEvent);
    }

    @Override
    public void update(ThreeStringsGet updateGameInfo) throws Exception {
        simpleGameInfos = updateGameInfo.getList();
        for (Event e : updateIt) {
            e.call();
        }
    }

    @Override
    public List<SimpliestGameInfo> getUpdateList() {
        return simpleGameInfos;
    }

}
