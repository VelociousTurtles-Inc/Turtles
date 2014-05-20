package Views.DoubleFastInit;

import Adapters.Interfaces.MenuController;

import java.util.concurrent.Semaphore;

/**
 * Created by larhard on 20.05.14.
 */
public class MenuView {
    public static Semaphore menuSemaphore = new Semaphore(0);
    public static int mode = 0;
    public MenuView(final MenuController menuController) {
        System.err.println(this.getClass());
        try {
            menuController.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.err.println("MenuView waiting for semaphore");
            menuSemaphore.acquire();
            System.err.println("has MenuView semaphore");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mode = 1;
        try {
            menuController.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
