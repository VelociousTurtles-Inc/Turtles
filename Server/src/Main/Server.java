package Main;

import Server.Interfaces.GameService;
import Services.StandardGameService;
import org.cojen.dirmi.Environment;

import java.util.Scanner;

/**
 * Created by larhard on 15.05.14.
 */
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("Server starting...");
        int port = 8080;
        if (args.length > 1) {
            port = Integer.valueOf(args[0]);
        }
        Environment environment = new Environment();
        GameService gameService = new StandardGameService();
        environment.newSessionAcceptor(port).acceptAll(gameService);
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
