package GameDispenser;

import Client.Interfaces.LoginClient;
import Client.Interfaces.ThreeStringsGet;
import Main.Server;
import Model.GameInfo;
import Common.Interfaces.Event;
import Utility.Utility;
import Server.Interfaces.*;
import Services.StandardWaiterService;
import org.cojen.dirmi.ClosedException;

import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Level;

public class StandardGameDispenser implements GameDispenser, ServerGameDispenser, GameEntry {
    private Map<Integer, GameManager> gameServices = new HashMap<>();
    private Set<StandardWaiterService> mySelectors = new HashSet<>();

    private int getEmptyId() {
        Random random = new Random();
        for(;;) {
            int result = random.nextInt();
            if (!gameServices.containsKey(result)) {
                return result;
            }
        }
    }

    public StandardGameDispenser() {
        Server.scenario.invoke(ServerGameDispenser.class, this);
    }

    @Override
    public GameManager connectToGame(int id, WaiterService mySel) throws RemoteException {
        if (gameServices.containsKey(id)) {
            Utility.Debug.log(Level.INFO, "[ StandardGameDispenser ] new client connected to game #" + id);
            gameServices.get(id).addPlayer(mySel);
            gameServices.get(id).update();
            update();
            return gameServices.get(id);
        } else {
            Utility.Debug.log(Level.INFO, "[ StandardGameDispenser ] client failed to connect to game #" + id);
            return null;
        }
    }

    @Override
    public Integer createNewGame(String name, WaiterService mySel) throws RemoteException {
        int id = getEmptyId();
        gameServices.put(id, new Services.StandardGameManager(name, id, this));
        gameServices.get(id).addPlayer(mySel);
        gameServices.get(id).setId(id);
        gameServices.get(id).update();
        update();
        Utility.Debug.log(Level.INFO, "[ StandardGameDispenser ] created new game #" + id);
        return id;
    }

    Collection<Event> closeEvents = new LinkedList<>();

    private void callCloseEvents() {
        for (Event event : closeEvents) {
            event.call();
        }
        closeEvents.clear();
    }

    @Override
    public void registerCloseEvent(Event event) {
        closeEvents.add(event);
    }
/*
    @Override
    public void setGameSelector(GameSelectClient mySelector) throws RemoteException {
        mySelectors.add(mySelector);

        // update list of game
        /*List<SimpliestGameInfo> myList = new LinkedList<>();
        ThreeStringsGet myTSG = new ThreeStringsGet() {
            List<SimpliestGameInfo> list;
            @Override
            public void setList(List<SimpliestGameInfo> list) throws RemoteException{
                this.list = list;
            }

            @Override
            public List<SimpliestGameInfo> getList() throws RemoteException {
                return list;
            }
        };
        for(GameManager myGame : gameServices.values()) {
            myList.add(myGame.getGameInfo());
        }
        myTSG.setList(myList);

        try {
            mySelector.update(myTSG);
        } catch (ClosedException e) {
            mySelectors.remove(mySelector);
        }

        // or just - but we don't need update all
        update();
    }
*/
    @Override
    public String getGameName(int gameID) throws RemoteException {
        return gameServices.get(gameID).getGameInfo().getGameName();
    }

 /*   @Override
    public void unregisterGameSelector(GameSelectClient mySelector) throws RemoteException {
        mySelectors.remove(mySelector);
    }*/

    @Override
    public void leaveGame(int gameID, WaiterService mySel) throws RemoteException {
        gameServices.get(gameID).removePlayer(mySel);
        gameServices.get(gameID).update();
        update();
    }

    @Override
    public void cancelGame(int gameID) {
        try {
            gameServices.get(gameID).cancel();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        gameServices.remove(gameID);
        try {
            update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMe() throws RemoteException {
        update();
    }

    @Override
    public void startGame(int gameID) throws RemoteException {
        gameServices.get(gameID).startGame();
    }

    @Override
    public void update() throws RemoteException {
        List<GameInfo> myList = new LinkedList<>();
        ThreeStringsGet myTSG = new ThreeStringsGet(){
            private List<GameInfo> list;
            public void setList(List<GameInfo> list) throws RemoteException{
                this.list = list;
            }

            public List<GameInfo> getList() throws RemoteException {
                return list;
            }
        };
        for(GameManager myGame : gameServices.values()) {
             myList.add(myGame.getGameInfo());
        }
        myTSG.setList(myList);

        List<StandardWaiterService> closedSelectors = new ArrayList<>();
        for(StandardWaiterService mySel : mySelectors) {
            try {
                mySel.update(myTSG);
            } catch (ClosedException e) {
                closedSelectors.add(mySel);
            }
        }
        for (StandardWaiterService gameSelectClient : closedSelectors) {
            mySelectors.remove(gameSelectClient);
        }
    }

    @Override
    public Collection<GameManager> getGameManagers() {
        return new LinkedList<>(gameServices.values());
    }

    @Override
    public void close() {
        Utility.logInfo("Calling GameDispenser CloseEvents");
        callCloseEvents();
    }

    @Override
    public void newSelector(String name, LoginClient login) throws RemoteException {
        mySelectors.add(new StandardWaiterService(name, login, this));
    }
}
