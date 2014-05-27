package Views.FastInit;

import Controllers.Interfaces.GameCreatorWaiterController;

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
