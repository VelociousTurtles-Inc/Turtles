package Views.FastInit;

import Adapters.Interfaces.GameSelectController;

public class GameSelectView {
    public GameSelectView(final GameSelectController gameSelectController) {
        System.err.println(this.getClass());
        gameSelectController.create();
    }
}
