package Views.FastInit;

import Adapters.Interfaces.MenuController;

/**
 * Created by larhard on 19.05.14.
 */
public class MenuView {
    public MenuView(final MenuController menuController) {
        System.err.println(this.getClass());
        try {
            menuController.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}