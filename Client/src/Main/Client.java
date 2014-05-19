package Main;

import Adapters.Interfaces.GameController;
import Adapters.Interfaces.GameSelectController;
import Adapters.Interfaces.MenuController;
import Adapters.StandardMenuController;
import ModelHelpers.DebugWriter;
import Scenario.Scenario;
import Views.GUIApplication;
import Views.Standard.Game.StandardGameView;
import Views.Standard.GameSelect.GameSelectView;
import Views.Standard.Menu.StandardMenuView;

import java.util.concurrent.Semaphore;

import static javafx.application.Application.launch;

/**
 * Created by michaziobro on 01.05.2014.
 */
public class Client {
    public static Semaphore guiSemaphore = new Semaphore(0);

    static GUIApplication guiApplication;
    public static Scenario StandardScenario() {
        Scenario result = new Scenario();
        new Thread() {
            @Override
            public void run() {
                launch(GUIApplication.class);
            }
        }.start();
        try {
            guiSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result.add(MenuController.class, StandardMenuView.class);
        result.add(GameSelectController.class, GameSelectView.class);
        result.add(GameController.class, StandardGameView.class);
        return result;
    }

    private static int port;
    private static String host;

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }

    public static Scenario scenario = new Scenario();

    public static void start(String host, int port, Scenario scenario) {
        Client.scenario = scenario;

        assert DebugWriter.write("Launching game", "host = " + host, "port = " + port);
        StandardMenuController myMenuController = new StandardMenuController();
    }

    public static void main(String[] args) {
        host = "localhost";
        if (args.length >= 1) {
            host = args[0];
        }
        port = 8080;
        if (args.length >= 2) {
            host = args[1];
        }

        assert DebugWriter.write("Starting application", args);
        start(host, port, StandardScenario());
    }
}
