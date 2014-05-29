package Main;

import GameDispenser.StandardGameDispenser;
import Utility.Utility;
import Model.PlayerDispenser;
import Utility.Scenario;
import Scenarios.StandardServerScenario;
import org.cojen.dirmi.Environment;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
    private static int port;
    public static Scenario scenario;

    /**
     *
     * @param args Commandline arguments. If supplied, first argugment specifies port on which server app will operate.
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        scenario = new StandardServerScenario();
        port = args.length > 1 ? Integer.valueOf(args[0]) : 8080;
        Environment environment = null;
        StandardGameDispenser gameDispenser = null;
        try {
            System.out.println("Server starting...");
            environment = new Environment();
            gameDispenser = new StandardGameDispenser();
            PlayerDispenser playerDispenser = new PlayerDispenser();
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
