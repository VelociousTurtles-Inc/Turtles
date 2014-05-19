package Views.FastInit;

import Adapters.Interfaces.GameSelectController;
import javafx.application.Platform;

/**
 * Created by larhard on 19.05.14.
 */
public class GameSelectView {
    public GameSelectView(final GameSelectController gameSelectController) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gameSelectController.create();
            }
        });
    }
}
