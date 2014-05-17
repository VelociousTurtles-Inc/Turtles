package Adapters;

import Client.Interfaces.GameWaiter;
import Views.Standard.GameCreation.GameWaiting.GameWaitingView;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameWaiterController implements GameWaiter {
    public StandardGameWaiterController() {
        GameWaitingView myView = new GameWaitingView(this);
        myView.start();
    }

    @Override
    public void update(int newNumberOfPlayers) {

    }
    public void leave() {

    }
}
