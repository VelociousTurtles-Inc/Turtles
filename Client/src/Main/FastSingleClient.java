package Main;

import Utility.DebugWriter;
import Scenarios.FastInitScenario;

/**
 * Created by larhard on 20.05.14.
 */
public class FastSingleClient {

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
        Client.start(host, port, new FastInitScenario());
    }
}
