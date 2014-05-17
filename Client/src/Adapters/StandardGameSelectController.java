package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameSelectController;
import Client.Interfaces.GameSelecter;
import Client.Interfaces.ThreeStringsGet;
import Main.Client;
import Model.SimpleGameInfo;
import Views.Standard.GameSelect.GameSelectView;
import Server.Interfaces.GameDispenser;

import org.cojen.dirmi.Environment;
import org.cojen.dirmi.Session;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class StandardGameSelectController implements GameSelectController, GameSelecter {

    GameDispenser myGameDispenser;
    List<ThreeStringsGet> simpleGameInfos;

    Event endIt;
    Event updateIt;

    public StandardGameSelectController() throws Exception {
        simpleGameInfos = new LinkedList<>();

        GameSelectView myView = new GameSelectView(this);
        myView.start();

        Environment environment = new Environment();
        Session session = environment.newSessionConnector(Client.getHost(), Client.getPort()).connect();

        myGameDispenser = (GameDispenser) session.receive();
        myGameDispenser.registerGameSelector(this);

    }

    @Override
    public void join() {
        StandardGameWaiterController mySGWC = new StandardGameWaiterController();
    }

    @Override
    public void cancel() {
        endIt.call();
    }

    @Override
    public void create() {
        StandardGameCreatorController mySGCC = new StandardGameCreatorController(myGameDispenser);
    }

    @Override
    public void setClosingEvent(Event myClosingEvent) {
        this.endIt = myClosingEvent;
    }

    @Override
    public void setUpdateEvent(Event myUpdateEvent) {
        this.updateIt = myUpdateEvent;
    }

    @Override
    public void update(List<ThreeStringsGet> updateGameInfo) {
        simpleGameInfos = updateGameInfo;
        updateIt.call();
    }

    @Override
    public List<ThreeStringsGet> getUpdateList() {
        return simpleGameInfos;
    }

}
