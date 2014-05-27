package Views.FastInit;

import Adapters.Interfaces.GameCreatorController;

import java.util.Random;

public class GameCreatorView {
    private static String lastGame;
    private static final Object lastGameSync = new Object();

    public GameCreatorView(final GameCreatorController gameCreatorController) {
        System.err.println(this.getClass());
        Random random = new Random();
        synchronized (lastGameSync) {
            lastGame = "Test-" + random.nextInt();
            try {
                gameCreatorController.create(lastGame);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getLastGame() {
        synchronized (lastGameSync) {
            return lastGame;
        }
    }
}
