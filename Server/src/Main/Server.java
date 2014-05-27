package Main;

import GameDispenser.StandardGameDispenser;
import Utility.Utility;
import Scenarios.Scenario;
import Scenarios.StandardScenario;
import Server.Interfaces.GameEntry;
import Server.Interfaces.ServerGameDispenser;
import org.cojen.dirmi.Environment;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by larhard on 15.05.14.
 */
public class Server {
    private static int port;
    public static Scenario scenario;
    public static void main(String[] args) throws Exception {
        scenario = new StandardScenario();
        port = args.length > 1 ? Integer.valueOf(args[0]) : 8080;
        Environment environment = null;
        StandardGameDispenser gameDispenser = null;
        try {
            System.out.println("Server starting...");
            environment = new Environment();
            gameDispenser = new StandardGameDispenser();
            environment.newSessionAcceptor(port).acceptAll(gameDispenser);
            System.out.println("Server started");
            System.out.println("type exit to stop server");
            Scanner scanner = new Scanner(System.in);
            for (; ; ) {
                try {
                    String command = scanner.nextLine();
                    if (command.equals("exit")) {
                        break;
                    }
                } catch (NoSuchElementException e) {
                    Utility.logInfo("Assumed there is no input stream connected");
                    Thread.sleep(86400000);
                }
            }
        } finally {
            System.out.println("Server stopping...");
            if (gameDispenser != null) {
                gameDispenser.close();
            }
            if (environment != null) {
                environment.close();
            }
            System.out.println("Server stopped");
        }
    }
}
