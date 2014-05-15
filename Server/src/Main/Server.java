package Main;

import GameDispenser.StandardGameDispenser;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.GameService;
import Services.StandardGameService;
import org.cojen.dirmi.Environment;

import java.util.Scanner;

/**
 * Created by larhard on 15.05.14.
 */
public class Server {
    private static int port;
    public static void main(String[] args) throws Exception {
        port = args.length > 1 ? Integer.valueOf(args[0]) : 8080;
        System.out.println("Server starting...");
        Environment environment = new Environment();
        GameDispenser gameDispenser = new StandardGameDispenser();
        environment.newSessionAcceptor(port).acceptAll(gameDispenser);
        System.out.println("Server started");
        System.out.println("type exit to stop server");
        Scanner scanner = new Scanner(System.in);
        for(;;) {
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
        }
        System.out.println("Server stopping...");
        environment.close();
        System.out.println("Server stopped");
    }
}
