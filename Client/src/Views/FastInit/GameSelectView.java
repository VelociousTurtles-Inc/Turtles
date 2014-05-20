package Views.FastInit;

import Adapters.Interfaces.GameSelectController;

/**
 * Created by larhard on 19.05.14.
 */
public class GameSelectView {
    public GameSelectView(final GameSelectController gameSelectController) {
        System.err.println(this.getClass());
        gameSelectController.create();
    }
}
