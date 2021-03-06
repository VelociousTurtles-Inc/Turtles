package Main;

import Scenarios.FastDoubleInitScenario;
import Utility.DebugWriter;

/**
 * initializes random game with 2 random players
 *
 * bugs : start in MenuView does not work properly
 */
public class FastDoubleClient {
    public static void main(String[] args) {
        int port;
        String host;

        host = "localhost";
        if (args.length >= 1) {
            host = args[0];
        }
        port = 8080;
        if (args.length >= 2) {
            host = args[1];
        }

        assert DebugWriter.write("Starting application", args);
        Client.start(host, port, new FastDoubleInitScenario());
    }
}
