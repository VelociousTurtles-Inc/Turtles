package Adapters;

import Adapters.Interfaces.GameCreatorController;
import Main.Client;
import Server.Interfaces.GameDispenser;
import Views.Standard.GameCreation.GameCreator.GameCreatorView;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorController implements GameCreatorController {
    GameDispenser myDispenser;

    public  StandardGameCreatorController(GameDispenser myDispenser) {
        this.myDispenser = myDispenser;
        Client.scenario.invoke(GameCreatorController.class, this);
    }

    @Override
    public void create(String name) throws Exception {
        new StandardGameCreatorWaiterController(name, myDispenser);
    }
}
