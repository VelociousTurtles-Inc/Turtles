package Views.DoubleFastInit;

import Adapters.Interfaces.GameCreatorController;
import javafx.application.Platform;

import java.util.Random;

/**
 * Created by larhard on 20.05.14.
 */
public class GameCreatorView {
    private static String lastGame;
    private static final Object lastGameSync = new Object();

    public GameCreatorView(final GameCreatorController gameCreatorController) {
        System.err.println(this.getClass());
        Random random = new Random();
        synchronized (lastGameSync) {
            lastGame = "Test-" + random.nextInt();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                gameCreatorController.create(lastGame);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            });
        }
    }

    public static String getLastGame() {
        synchronized (lastGameSync) {
            return lastGame;
        }
    }
}
