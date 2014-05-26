package Services;

import Client.Interfaces.ClientLogin;
import Client.Interfaces.GameSelectClient;
import Client.Interfaces.GameWaiterClient;
import Client.Interfaces.ThreeStringsGet;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.GameManager;
import Server.Interfaces.WaiterService;

/**
 * Created by michaziobro on 26.05.2014.
 */
public class StandardWaiterService implements WaiterService {

    private GameDispenser myDispenser;
    private GameSelectClient mySelector;
    private String myName;

    public StandardWaiterService(String name, ClientLogin selector, GameDispenser dispenser) throws Exception {
        myDispenser = dispenser;
        myName = name;

        selector.toGameSelect(this);
    }

    String getName() {
        return myName;
    }

    @Override
    public GameManager connectToGame(int id, GameWaiterClient mySel) throws Exception {
        return myDispenser.connectToGame(id, mySel);
    }

    @Override
    public Integer createNewGame(String name, GameWaiterClient mySel) throws Exception {
        return myDispenser.createNewGame(name, mySel);
    }

 /*   @Override
    public void unregisterGameSelector(GameSelectClient mySelector) throws Exception {

    }*/

    @Override
    public void leaveGame(int gameID, GameWaiterClient mySel) throws Exception {
        myDispenser.leaveGame(gameID, mySel);
    }

    @Override
    public void cancelGame(int gameID) throws Exception {
        myDispenser.cancelGame(gameID);
    }

    @Override
    public void setGameSelector(GameSelectClient mySel) throws Exception {
        mySelector = mySel;
    }

    @Override
    public String getGameName(int gameID) throws Exception {
        return myDispenser.getGameName(gameID);
    }

    @Override
    public void updateMe() throws Exception {
        myDispenser.updateMe();
    }

    @Override
    public void startGame(int gameID) throws Exception {
        myDispenser.startGame(gameID);
    }

    public void update(ThreeStringsGet myTSG) throws Exception {
        mySelector.update(myTSG);
    }
}
