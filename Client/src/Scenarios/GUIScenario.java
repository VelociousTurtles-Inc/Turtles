package Scenarios;

import Views.GUIApplication;

import java.util.concurrent.Semaphore;

import static javafx.application.Application.launch;

/**
 * Created by larhard on 19.05.14.
 */
public class GUIScenario extends Scenario {
    public static Semaphore guiSemaphore = new Semaphore(0);

    public GUIScenario() {
        new Thread() {
            @Override
            public void run() {
                launch(GUIApplication.class);
            }
        }.start();
        try {
            GUIApplication.guiSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
