package Adapters;

import Model.SimpleGameInfo;
import Server.Interfaces.GameWaiter;
import Views.Standard.GameCreation.GameCreator.GameCreatorView;

import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorController implements GameWaiter {
    public  StandardGameCreatorController() {
        GameCreatorView myView = new GameCreatorView(this);
        myView.start();
    }
    @Override
    public void update(List<SimpleGameInfo> updateGameInfo) {

    }
}
