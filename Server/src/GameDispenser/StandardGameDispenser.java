package GameDispenser;

import Model.Utility.Utility;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.GameService;
import Services.StandardGameService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

/**
 * Created by larhard on 15.05.14.
 */
public class StandardGameDispenser implements GameDispenser {
    private Map<Integer, GameService> gameServices = new HashMap<>();

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
    public GameService connectToGame(int id) throws Exception {
        if (gameServices.containsKey(id)) {
            Utility.Debug.log(Level.INFO, "[ StandardGameDispenser ] new client connected to game #" + id);
            return gameServices.get(id);
        } else {
            Utility.Debug.log(Level.INFO, "[ StandardGameDispenser ] client failed to connect to game #" + id);
            return null;
        }
    }

    @Override
    public Integer createNewGame() throws Exception {
        int id = getEmptyId();
        gameServices.put(id, new StandardGameService());
        Utility.Debug.log(Level.INFO, "[ StandardGameDispenser ] created new game #" + id);
        return id;
    }
}
