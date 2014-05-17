package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameSelectController;
import Main.Client;
import Views.Standard.GameSelect.GameSelectView;
import Server.Interfaces.GameDispenser;

import org.cojen.dirmi.Environment;
import org.cojen.dirmi.Session;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class StandardGameSelectController implements GameSelectController {

    GameDispenser myGameDispenser;

    public StandardGameSelectController() throws Exception {
        GameSelectView myView = new GameSelectView(this);
        myView.start();

        Environment environment = new Environment();
        Session session = environment.newSessionConnector(Client.getHost(), Client.getPort()).connect();

        GameDispenser gameDispenser = (GameDispenser) session.receive();

        /*Integer gameId = gameDispenser.createNewGame();
        if (gameId == null) {
            throw new NullPointerException();
        }*/

    }

    Event endIt;

    @Override
    public void join() {
        //nothing
    }

    @Override
    public void cancel() {
        endIt.call();
    }

    @Override
    public void create() {
        StandardGameCreatorController mySGCC = new StandardGameCreatorController();

    }

    @Override
    public void setClosingEvent(Event myClosingEvent) {
        this.endIt = myClosingEvent;
    }
}
