package Main;

import Adapters.StandardMenuController;
import ModelHelpers.DebugWriter;

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
        StandardMenuController myMenuController = new StandardMenuController();

    }
}
