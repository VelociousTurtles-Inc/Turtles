package Views;

import Main.Client;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by larhard on 19.05.14.
 */
public class GUIApplication extends Application {
    public GUIApplication() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        Client.guiSemaphore.release();
    }
}
