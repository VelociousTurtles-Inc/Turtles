package Adapters;

<<<<<<< HEAD
/**
 * Created by larhard on 05.05.14.
 */
/*public class MainAdapter {
    public static void main(String[] args) {
        final GameService gameService = new GameService();
=======
import Views.SimpleGameTextView.SimpleGameTextView;

import java.util.Arrays;
import ServicesTypes.*;
/**
 * Created by larhard on 05.05.14.
 */
public class MainAdapter {
    public static void main(String[] args) {
        final GameService gameService = new ServicesTypes.GameServiceService().getGameServicePort();
>>>>>>> origin/DangerouslyManyChangesToCheck
        final String[] finalArgs = args;

        // TODO player data
        Thread p1 = new Thread() {
            @Override
            public void run() {
                GameAdapter gameAdapter = new SimpleGameAdapter(Arrays.asList(new SimpleGameTextView()), gameService, finalArgs);
            }
        };
        p1.start();
    }
<<<<<<< HEAD
}*/
=======
}
>>>>>>> origin/DangerouslyManyChangesToCheck
