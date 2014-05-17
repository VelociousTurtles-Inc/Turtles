package Adapters;

import Client.Interfaces.GameSelecter;
import Model.SimpleGameInfo;
import Client.Interfaces.GameWaiter;
import Views.Standard.GameCreation.GameCreator.GameCreatorView;

import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorController {
    public  StandardGameCreatorController() {
        GameCreatorView myView = new GameCreatorView(this);
        myView.start();
    }

    public void create(String s) {
        new StandardGameCreatorWaiterController();
    }
}
