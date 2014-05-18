package Adapters;

import Adapters.Interfaces.Event;
import Client.Interfaces.GameWaiter;
import Server.Interfaces.GameDispenser;
import Views.Standard.GameCreation.GameCreatorsWaiting.GameCreatorsWaitingView;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorWaiterController implements GameWaiter {
    GameDispenser myDispenser;
    private  int numberOfPlayers;
    private Event myUpdateEvent;

    public StandardGameCreatorWaiterController(String s, GameDispenser standardGameCreatorController) throws Exception {
        GameCreatorsWaitingView myView = new GameCreatorsWaitingView(this);
        myView.start();

        this.myDispenser = standardGameCreatorController;
        myDispenser.createNewGame(s, this);
    }

    @Override
    public void update(int newNumberOfPlayers) {
        this.numberOfPlayers = newNumberOfPlayers;
        myUpdateEvent.call();
    }

    public void registerUpdateEvent(Event updateEvent) {
        myUpdateEvent = updateEvent;
    }

    public void start() {
        
    }

    public void cancel() {

    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
