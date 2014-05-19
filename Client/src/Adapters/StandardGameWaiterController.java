package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameWaiterController;
import Client.Interfaces.GameWaiterClient;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.PlayerService;
import Views.Standard.GameCreation.GameWaiting.GameWaiterView;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameWaiterController implements GameWaiterController, GameWaiterClient {
    private int gameID;
    private int myNumberOfPlayers;
    private GameDispenser myGameDispenser;
    Event updateEvent;
    private String gameName;
    private Event cancelEvent;

    public StandardGameWaiterController(int myID, GameDispenser myGameDispenser) throws Exception {
        gameID = myID;
        GameWaiterView myView = new GameWaiterView(this);
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

    @Override
    public void start(PlayerService player) throws Exception {
        SimpleGameAdapter myAdapter = new SimpleGameAdapter();
        myAdapter.start(player);
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
