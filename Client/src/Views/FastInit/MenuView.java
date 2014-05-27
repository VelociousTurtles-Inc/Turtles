package Views.FastInit;

import Controllers.Interfaces.MenuController;

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
