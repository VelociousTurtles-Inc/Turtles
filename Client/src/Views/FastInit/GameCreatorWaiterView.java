package Views.FastInit;

import Adapters.Interfaces.GameCreatorWaiterController;
import javafx.application.Platform;

/**
 * Created by larhard on 20.05.14.
 */
public class GameCreatorWaiterView {
    public GameCreatorWaiterView(final GameCreatorWaiterController gameCreatorWaiterController) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    gameCreatorWaiterController.startAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
