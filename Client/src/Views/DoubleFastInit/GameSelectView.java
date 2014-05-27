package Views.DoubleFastInit;

import Controllers.Interfaces.GameSelectController;

public class GameSelectView {
    public GameSelectView(final GameSelectController gameSelectController) {
        System.err.println(this.getClass());
        if (MenuView.mode == 0) {
            gameSelectController.create();
        } else {
            try {
                System.err.println("Connecting to " + GameCreatorWaiterView.gameID);
                gameSelectController.join(GameCreatorWaiterView.gameID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
