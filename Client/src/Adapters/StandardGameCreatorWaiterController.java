package Adapters;

import Client.Interfaces.GameWaiter;
import Views.Standard.GameCreation.GameCreatorsWaiting.GameCreatorsWaitingView;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorWaiterController implements GameWaiter {
    public StandardGameCreatorWaiterController() {
        GameCreatorsWaitingView myView = new GameCreatorsWaitingView(this);
        myView.start();
    }

    @Override
    public void update(int newNumberOfPlayers) {
        
    }

    public void start() {
        
    }

    public void cancel() {

    }
}
