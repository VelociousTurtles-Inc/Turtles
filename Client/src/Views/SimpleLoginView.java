package Views;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by michaziobro on 01.05.2014.
 */
<<<<<<< HEAD:Client/src/Views/SimpleLoginView.java
public class SimpleLoginView extends Application {
    static ImageView t1;
=======
public class Main extends Application {
>>>>>>> origin/DangerouslyManyChangesToCheck:Client/src/Views/Main.java
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene sc1 = new Scene(root);
        stage.setScene(sc1);
        stage.show();

        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
