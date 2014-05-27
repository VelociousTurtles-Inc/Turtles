package Views;

import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

public class GUIApplication extends Application {
    public static Semaphore guiSemaphore = new Semaphore(0);

    @Override
    public void start(Stage stage) throws RemoteException {
        guiSemaphore.release();
    }
}
