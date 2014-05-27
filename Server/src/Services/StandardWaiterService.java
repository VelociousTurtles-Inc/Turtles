package Services;

import Client.Interfaces.LoginClient;
import Client.Interfaces.GameSelectClient;
import Client.Interfaces.GameWaiterClient;
import Client.Interfaces.ThreeStringsGet;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.GameManager;
import Server.Interfaces.PlayerService;
import Server.Interfaces.WaiterService;

import java.rmi.RemoteException;

/**
 * Created by michaziobro on 26.05.2014.
 */
public class StandardWaiterService implements WaiterService {

    private GameDispenser myDispenser;
    private GameSelectClient mySelector;
    private GameWaiterClient myWaiter;
    private int chosenGame;
    private String myName;

    public StandardWaiterService(String name, LoginClient selector, GameDispenser dispenser) throws Exception {
        myDispenser = dispenser;
        myName = name;

        selector.toGameSelect(this);
    }

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public GameManager connectToGame(int id, GameWaiterClient mySel) throws Exception {
        myWaiter = mySel;
        chosenGame = id;
        return myDispenser.connectToGame(id, this);
    }

    @Override
    public Integer createNewGame(String name, GameWaiterClient mySel) throws Exception {
        myWaiter = mySel;
        chosenGame = myDispenser.createNewGame(name, this);
        return chosenGame;
    }

 /*   @Override
    public void unregisterGameSelector(GameSelectClient mySelector) throws Exception {

    }*/

    @Override
    public void leaveGame() throws Exception {
        myDispenser.leaveGame(chosenGame, this);
        myWaiter = null;
        chosenGame = -1;
    }

    @Override
    public void cancelGame() throws Exception {
        myDispenser.cancelGame(chosenGame);
        myWaiter = null;
        chosenGame = -1;
    }

    @Override
    public void setGameSelector(GameSelectClient mySel) throws Exception {
        mySelector = mySel;
    }

    @Override
    public String getGameName() throws Exception {
        return myDispenser.getGameName(chosenGame);
    }

    @Override
    public void updateMe() throws Exception {
        myDispenser.updateMe();
    }

    @Override
    public void cancel() throws Exception {
        myWaiter.cancel();
    }

    @Override
    public void startGame() throws Exception {
        myDispenser.startGame(chosenGame);
    }

    public void update(ThreeStringsGet myTSG) throws Exception {
        mySelector.update(myTSG);
    }

    @Override
    public void start(PlayerService playerService) throws Exception {
        myWaiter.start(playerService);
    }

    public void closeMe() throws RemoteException {
        myWaiter.closeMe();
    }

    @Override
    public void updateWaiter(int numberOfPlayers) throws Exception {
        myWaiter.update(numberOfPlayers);
    }

    public void ping() throws RemoteException {
        myWaiter.ping();
    }
}
