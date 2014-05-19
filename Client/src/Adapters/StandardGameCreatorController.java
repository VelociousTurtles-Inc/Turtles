package Adapters;

import Client.Interfaces.GameWaiter;
import Server.Interfaces.GameDispenser;
import Views.Standard.GameCreation.GameCreator.GameCreatorView;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorController {
    GameDispenser myDispenser;

    public  StandardGameCreatorController(GameDispenser myDispenser) {
        this.myDispenser = myDispenser;
        GameCreatorView myView = new GameCreatorView(this);
        myView.start();
    }

    public void create(String s) throws Exception {
        new StandardGameCreatorWaiterController(s, myDispenser);
    }
}
