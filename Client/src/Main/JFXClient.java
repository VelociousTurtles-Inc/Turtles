package Main;

import Scenarios.JFXStandardScenario;
import Utility.DebugWriter;
import javafx.application.Application;
import javafx.stage.Stage;

public class JFXClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        int port;
        String host;


        // TODO entering other host and port
        host = "elgassia.tk";
        port = 8080;

        assert DebugWriter.write("Starting JFX application");
        Client.start(host, port, new JFXStandardScenario());
    }
}