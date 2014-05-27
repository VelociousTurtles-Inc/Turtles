package Main;

import Controllers.StandardMenuController;
import Utility.Scenario;
import Scenarios.StandardScenario;
import Utility.DebugWriter;

public class Client {
    private static int port;
    private static String host;

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }

    public static Scenario scenario;

    public static void start(String host, int port, Scenario scenario) {
        Client.host = host;
        Client.port = port;
        Client.scenario = scenario;

        assert DebugWriter.write("Launching game", "host = " + host, "port = " + port);
        new StandardMenuController();
    }

    public static void main(String[] args) {
        String host = "localhost";
        if (args.length >= 1) {
            host = args[0];
        }
        int port = 8080;
        if (args.length >= 2) {
            host = args[1];
        }

        assert DebugWriter.write("Starting application", args);
        start(host, port, new StandardScenario());
    }
}
