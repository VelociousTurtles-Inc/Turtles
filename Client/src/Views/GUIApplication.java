package Views;

import Main.Client;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by larhard on 19.05.14.
 */
public class GUIApplication extends Application {
    public static Semaphore guiSemaphore = new Semaphore(0);

    @Override
    public void start(Stage stage) throws Exception {
        guiSemaphore.release();
    }
}
