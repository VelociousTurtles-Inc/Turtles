package Views.DoubleFastInit;

import Adapters.Interfaces.GameWaiterController;

/**
 * Created by larhard on 20.05.14.
 */
public class GameWaiterView {
    public GameWaiterView(GameWaiterController gameWaiterController) {
        System.err.println(this.getClass());
        GameCreatorWaiterView.startSemaphore.release();
    }
}
