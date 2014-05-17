package GameDispenser;

import Client.Interfaces.GameSelecter;
import Client.Interfaces.GameWaiter;
import Client.Interfaces.ThreeStringsGet;
import Model.SimpleGameInfo;
import Model.Utility.Utility;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.GameManager;
import Server.Interfaces.GameStarter;

import java.util.*;
import java.util.logging.Level;

/**
 * Created by larhard on 15.05.14.
 */
public class StandardGameDispenser implements GameDispenser {
    private Map<Integer, GameStarter> gameServices = new HashMap<>();
    private List<GameSelecter> mySelecters = new LinkedList<>();

    private int getEmptyId() {
        Random random = new Random();
        for(;;) {
            int result = random.nextInt();
            if (!gameServices.containsKey(result)) {
                return result;
            }
        }
    }

    @Override
    public GameStarter connectToGame(int id) throws Exception {
        if (gameServices.containsKey(id)) {
            Utility.Debug.log(Level.INFO, "[ StandardGameDispenser ] new client connected to game #" + id);
            update();
            return gameServices.get(id);
        } else {
            Utility.Debug.log(Level.INFO, "[ StandardGameDispenser ] client failed to connect to game #" + id);
            return null;
        }
    }

    @Override
    public Integer createNewGame(String name, GameWaiter mySel) throws Exception {
        int id = getEmptyId();
        gameServices.put(id, new Services.StandardGameManager(name));
        gameServices.get(id).addPlayer();
        update();
        Utility.Debug.log(Level.INFO, "[ StandardGameDispenser ] created new game #" + id);
        return id;
    }

    public void registerGameSelector(GameSelecter mySelector) throws Exception {
        mySelecters.add(mySelector);
    }

    @Override
    public void leaveGame(int playerID) throws Exception {

    }

    @Override
    public void cancelGame() throws Exception{

    }

    public void startGame() throws Exception {

    }

    private void update() throws Exception {
        List<ThreeStringsGet> myList = new LinkedList<>();
        for(GameStarter myGame : gameServices.values()) {
             myList.add(myGame.getGameInfo());
        }
        for(GameSelecter mySel : mySelecters) {
            mySel.update(myList);
        }
    }
}
