package Main;

import Adapters.Interfaces.GameController;
import Adapters.SimpleGameAdapter;
import Adapters.StandardMenuController;
import ModelHelpers.DebugWriter;
import Scenario.Scenario;
import Views.Standard.Game.StandardGameView;

/**
 * Created by michaziobro on 01.05.2014.
 */
public class Client {
    private static int port;
    private static String host;

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }

    public static Scenario scenario = new Scenario();

    public static void main(String[] args) {
        host = "localhost";
        if (args.length >= 1) {
            host = args[0];
        }
        port = 8080;
        if (args.length >= 2) {
            host = args[1];
        }

        scenario.add(GameController.class, StandardGameView.class);

        assert DebugWriter.write("Starting application", args);
        StandardMenuController myMenuController = new StandardMenuController();

    }
}
