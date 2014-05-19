package Views.FastInit;

import Adapters.Interfaces.MenuController;
import javafx.application.Platform;

/**
 * Created by larhard on 19.05.14.
 */
public class MenuView {
    public MenuView(final MenuController menuController) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    menuController.startGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
