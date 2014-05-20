package Views.DoubleFastInit;

import Adapters.Interfaces.GameSelectController;
import javafx.application.Platform;

/**
 * Created by larhard on 19.05.14.
 */
public class GameSelectView {
    public GameSelectView(final GameSelectController gameSelectController) {
        System.err.println(this.getClass());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (MenuView.mode == 0) {
                    new Thread () {
                        @Override
                        public void run() {
                            gameSelectController.create();
                        }
                    }.start();
                } else {
                    new Thread () {
                        @Override
                        public void run() {
                            try {
                                System.err.println("Connecting to " + GameCreatorWaiterView.gameID);
                                gameSelectController.join(GameCreatorWaiterView.gameID);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
        });
    }
}
