package Views.DoubleFastInit;

import Controllers.Interfaces.GameWaiterController;

public class GameWaiterView {
    public GameWaiterView(GameWaiterController gameWaiterController) {
        System.err.println(this.getClass());
        GameCreatorWaiterView.startSemaphore.release();
    }
}
