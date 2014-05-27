package Utility;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Utility {
    public static final Logger Debug = Logger.getLogger("Server");

    public static void logInfo(String message) {
        Debug.log(Level.INFO, message);
    }

    public static void logWarning(String message) {
        Debug.log(Level.WARNING, message);
    }
}
