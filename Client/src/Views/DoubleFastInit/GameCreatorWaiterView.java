package Views.DoubleFastInit;

import Adapters.Interfaces.GameCreatorWaiterController;
import javafx.application.Platform;

import java.util.concurrent.Semaphore;

/**
 * Created by larhard on 20.05.14.
 */
public class GameCreatorWaiterView {
    public static Semaphore startSemaphore = new Semaphore(0);
    public static int gameID;
    public GameCreatorWaiterView(final GameCreatorWaiterController gameCreatorWaiterController) {
        System.err.println(this.getClass());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    System.err.println("Get gameID");
                    gameID = gameCreatorWaiterController.getGameID();
                    System.err.println("MenuView release");
                    MenuView.menuSemaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            startSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
