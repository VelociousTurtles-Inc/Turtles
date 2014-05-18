package Adapters;

import Adapters.Interfaces.Event;
import Client.Interfaces.GameWaiter;
import Server.Interfaces.GameDispenser;
import Views.Standard.GameCreation.GameWaiting.GameWaitingView;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameWaiterController implements GameWaiter {
    private int gameID;
    private int myNumberOfPlayers;
    private GameDispenser myGameDispenser;
    Event updateEvent;
    private String gameName;
    private Event cancelEvent;

    public StandardGameWaiterController(int myID, GameDispenser myGameDispenser) throws Exception {
        gameID = myID;
        GameWaitingView myView = new GameWaitingView(this);
        myView.start();
        this.myGameDispenser = myGameDispenser;
        gameName = myGameDispenser.getGameName(gameID);
        myGameDispenser.connectToGame(myID, this);
    }

    @Override
    public void update(int newNumberOfPlayers) {
        myNumberOfPlayers = newNumberOfPlayers;
        updateEvent.call();
    }

    @Override
    public void cancel() throws Exception {
        cancelEvent.call();
    }

    public void registerCancelEvent(Event cancelEvent) {
        this.cancelEvent = cancelEvent;
    }

    public void registerUpdate(Event update) {
        updateEvent = update;
    }

    public int getNumberOfPlayers() {
        return myNumberOfPlayers;
    }

    public void leave() throws Exception {
        myGameDispenser.leaveGame(gameID,this);
    }

    public String getGameName() throws Exception {
        return gameName;
    }
}
