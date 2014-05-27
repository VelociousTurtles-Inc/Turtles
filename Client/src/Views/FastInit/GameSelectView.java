package Views.FastInit;

import Controllers.Interfaces.GameSelectController;

public class GameSelectView {
    public GameSelectView(final GameSelectController gameSelectController) {
        System.err.println(this.getClass());
        gameSelectController.create();
    }
}
