package Views.FastInit;

import Adapters.Interfaces.GameCreatorController;
import javafx.application.Platform;

import java.util.Random;

/**
 * Created by larhard on 20.05.14.
 */
public class GameCreatorView {
    public GameCreatorView(final GameCreatorController gameCreatorController) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                try {
                    gameCreatorController.create("Test-" + random.nextInt());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
