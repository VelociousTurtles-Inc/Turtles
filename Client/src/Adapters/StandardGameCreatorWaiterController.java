package Adapters;

import Adapters.Interfaces.Event;
import Client.Interfaces.GameWaiter;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.PlayerService;
import Views.Standard.GameCreation.GameCreatorsWaiting.GameCreatorsWaitingView;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorWaiterController implements GameWaiter {
    GameDispenser myDispenser;
    private  int numberOfPlayers;
    private Event myUpdateEvent;
    private Event cancelEvent;
    private String gameName;
    private int gameID;

    public StandardGameCreatorWaiterController(String s, GameDispenser standardGameCreatorController) throws Exception {
        GameCreatorsWaitingView myView = new GameCreatorsWaitingView(this);
        myView.start();

        this.myDispenser = standardGameCreatorController;
        int id = myDispenser.createNewGame(s, this);
        gameName =  myDispenser.getGameName(id);
        gameID = id;
        update(numberOfPlayers);
    }

    @Override
    public void update(int newNumberOfPlayers) {
        this.numberOfPlayers = newNumberOfPlayers;
        myUpdateEvent.call();
    }

    @Override
    public void cancel() throws Exception {
        cancelEvent.call();
    }

    public void registerUpdateEvent(Event updateEvent) {
        myUpdateEvent = updateEvent;
    }

    public void start(PlayerService player) throws Exception {
        SimpleGameAdapter myAdapter = new SimpleGameAdapter();
        myAdapter.start(player);
    }

    public void startAll() throws Exception {
        myDispenser.startGame(gameID);
    }

    public void cancelAll() throws Exception {
        myDispenser.cancelGame(gameID);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void registerCancelEvent(Event cancelEvent) {
        this.cancelEvent = cancelEvent;
    }

    public String getGameName() {
        return gameName;
    }
}
