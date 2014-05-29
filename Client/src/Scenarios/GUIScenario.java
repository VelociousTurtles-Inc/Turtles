package Scenarios;

import Utility.Scenario;
import Views.GUIApplication;

import java.util.concurrent.Semaphore;

import static javafx.application.Application.launch;

/**
 * scenario which initializes Application on init
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
