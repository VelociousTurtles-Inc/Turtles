package Views.FastInit;

import Adapters.Interfaces.GameCreatorWaiterController;

/**
 * Created by larhard on 20.05.14.
 */
public class GameCreatorWaiterView {
    public GameCreatorWaiterView(final GameCreatorWaiterController gameCreatorWaiterController) {
        System.err.println(this.getClass());
        try {
            gameCreatorWaiterController.startAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
