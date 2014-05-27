package Views.DoubleFastInit;

import Adapters.Interfaces.GameCreatorWaiterController;

import java.util.concurrent.Semaphore;

public class GameCreatorWaiterView {
    public static Semaphore startSemaphore = new Semaphore(0);
    public static int gameID;
    public GameCreatorWaiterView(final GameCreatorWaiterController gameCreatorWaiterController) {
        System.err.println(this.getClass());
        try {
            System.err.println("Get gameID");
            gameID = gameCreatorWaiterController.getGameID();
            System.err.println("MenuView release");
            MenuView.menuSemaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            startSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            gameCreatorWaiterController.startAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
