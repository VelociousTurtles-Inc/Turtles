package GameDispenser;

import Client.Interfaces.GameSelectClient;
import Client.Interfaces.GameWaiterClient;
import Client.Interfaces.ThreeStringsGet;
import Events.Event;
import Main.Server;
import Model.GameInfo;
import Utility.Utility;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.GameManager;
import Server.Interfaces.ServerGameDispenser;
import org.cojen.dirmi.ClosedException;

import java.util.*;
import java.util.logging.Level;

/**
 * Created by larhard on 15.05.14.
 */
public class StandardGameDispenser implements GameDispenser, ServerGameDispenser {
    private Map<Integer, GameManager> gameServices = new HashMap<>();
    private Set<GameSelectClient> mySelecters = new HashSet<>();

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
    public GameManager connectToGame(int id, GameWaiterClient mySel) throws Exception {
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
    public Integer createNewGame(String name, GameWaiterClient mySel) throws Exception {
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

    @Override
    public void registerGameSelector(GameSelectClient mySelector) throws Exception {
        mySelecters.add(mySelector);

        // update list of game
        /*List<SimpliestGameInfo> myList = new LinkedList<>();
        ThreeStringsGet myTSG = new ThreeStringsGet() {
            List<SimpliestGameInfo> list;
            @Override
            public void setList(List<SimpliestGameInfo> list) throws Exception{
                this.list = list;
            }

            @Override
            public List<SimpliestGameInfo> getList() throws Exception {
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
            mySelecters.remove(mySelector);
        }*/

        // or just - but we don't need update all
        update();
    }

    @Override
    public String getGameName(int gameID) throws Exception {
        return gameServices.get(gameID).getGameInfo().getGameName();
    }

    @Override
    public void unregisterGameSelector(GameSelectClient mySelector) throws Exception {
        mySelecters.remove(mySelector);
    }

    @Override
    public void leaveGame(int gameID, GameWaiterClient mySel) throws Exception {
        gameServices.get(gameID).removePlayer(mySel);
        gameServices.get(gameID).update();
        update();
    }

    @Override
    public void cancelGame(int gameID) {
        try {
            gameServices.get(gameID).cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameServices.remove(gameID);
        try {
            update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMe() throws Exception {
        update();
    }

    @Override
    public void startGame(int gameID) throws Exception {
        gameServices.get(gameID).startGame();
    }

    @Override
    public void update() throws Exception {
        List<GameInfo> myList = new LinkedList<>();
        ThreeStringsGet myTSG = new ThreeStringsGet() {
            List<GameInfo> list;
            @Override
            public void setList(List<GameInfo> list) throws Exception{
                this.list = list;
            }

            @Override
            public List<GameInfo> getList() throws Exception {
                return list;
            }
        };
        for(GameManager myGame : gameServices.values()) {
             myList.add(myGame.getGameInfo());
        }
        myTSG.setList(myList);

        List<GameSelectClient> closedSelecters = new ArrayList<>();
        for(GameSelectClient mySel : mySelecters) {
            try {
                mySel.update(myTSG);
            } catch (ClosedException e) {
                closedSelecters.add(mySel);
            }
        }
        for (GameSelectClient gameSelectClient : closedSelecters) {
            mySelecters.remove(gameSelectClient);
        }
    }

    @Override
    public Collection<GameManager> getGameManagers() {
        return gameServices.values();
    }

    @Override
    public void close() {
        Utility.logInfo("Calling GameDispenser CloseEvents");
        callCloseEvents();
    }
}
