package Adapters;

import Client.Interfaces.GameSelecter;
import Model.SimpleGameInfo;
import Client.Interfaces.GameWaiter;
import Server.Interfaces.GameDispenser;
import Views.Standard.GameCreation.GameCreator.GameCreatorView;

import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorController implements GameWaiter {
    GameDispenser myDispenser;

    public  StandardGameCreatorController(GameDispenser myDispenser) {
        this.myDispenser = myDispenser;
        GameCreatorView myView = new GameCreatorView(this);
        myView.start();
    }

    public void create(String s) throws Exception {
        myDispenser.createNewGame(s, this);
        new StandardGameCreatorWaiterController();
    }

    @Override
    public void update(int newNumberOfPlayers) {

    }
}
