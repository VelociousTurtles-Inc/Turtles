package Services;

import Client.Interfaces.GameSelectClient;
import Client.Interfaces.GameWaiterClient;
import Client.Interfaces.LoginClient;
import Client.Interfaces.ThreeStringsGet;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.GameManager;
import Server.Interfaces.PlayerService;
import Server.Interfaces.WaiterService;

import java.rmi.RemoteException;

public class StandardWaiterService implements WaiterService {

    private final GameDispenser myDispenser;
    private GameSelectClient mySelector;
    private GameWaiterClient myWaiter;
    private int chosenGame;
    private final String myName;

    public StandardWaiterService(String name, LoginClient selector, GameDispenser dispenser) throws RemoteException {
        myDispenser = dispenser;
        myName = name;

        selector.toGameSelect(this);
    }

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public GameManager connectToGame(int id, GameWaiterClient mySel) throws RemoteException {
        myWaiter = mySel;
        chosenGame = id;
        return myDispenser.connectToGame(id, this);
    }

    @Override
    public Integer createNewGame(String name, GameWaiterClient mySel) throws RemoteException {
        myWaiter = mySel;
        chosenGame = myDispenser.createNewGame(name, this);
        return chosenGame;
    }

 /*   @Override
    public void unregisterGameSelector(GameSelectClient mySelector) throws RemoteException {

    }*/

    @Override
    public void leaveGame() throws RemoteException {
        myDispenser.leaveGame(chosenGame, this);
        myWaiter = null;
        chosenGame = -1;
    }

    @Override
    public void cancelGame() throws RemoteException {
        myDispenser.cancelGame(chosenGame);
        myWaiter = null;
        chosenGame = -1;
    }

    @Override
    public void setGameSelector(GameSelectClient mySel) throws RemoteException {
        mySelector = mySel;
    }

    @Override
    public String getGameName() throws RemoteException {
        return myDispenser.getGameName(chosenGame);
    }

    @Override
    public void updateMe() throws RemoteException {
        myDispenser.updateMe();
    }

    @Override
    public void cancel() throws RemoteException {
        myWaiter.cancel();
    }

    @Override
    public void startGame() throws RemoteException {
        myDispenser.startGame(chosenGame);
    }

    public void update(ThreeStringsGet myTSG) throws RemoteException {
        mySelector.update(myTSG);
    }

    @Override
    public void start(PlayerService playerService) throws RemoteException {
        myWaiter.start(playerService);
    }

    public void closeMe() throws RemoteException {
        myWaiter.closeMe();
    }

    @Override
    public void updateWaiter(int numberOfPlayers) throws RemoteException {
        myWaiter.update(numberOfPlayers);
    }

    public void ping() throws RemoteException {
        myWaiter.ping();
    }
}
