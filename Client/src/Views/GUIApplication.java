package Views;

import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

/**
 * Created by larhard on 19.05.14.
 */
public class GUIApplication extends Application {
    public static Semaphore guiSemaphore = new Semaphore(0);

    @Override
    public void start(Stage stage) throws RemoteException {
        guiSemaphore.release();
    }
}
