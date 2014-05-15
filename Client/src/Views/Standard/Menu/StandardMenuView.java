package Views.Standard.Menu;

import Adapters.Interfaces.MenuController;
import ModelHelpers.DebugWriter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by mz18 on 8/05/14.
 */
public class StandardMenuView extends Application {

    private StandardMenuButtons myMenuButtons;
    private static MenuController myMenuController;

    public StandardMenuView() {

    }

    public StandardMenuView(MenuController myMenuController) {
        assert DebugWriter.write("Create new StandardMenuView", myMenuController);
        StandardMenuView.myMenuController = myMenuController;
        Thread myRun = new Thread() {
            @Override
            public void run() {
                launch(StandardMenuView.class);
            }
        };
        myRun.start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        assert DebugWriter.write("Launching StandardMenuView " + this);
        FXMLLoader myLoader = new FXMLLoader();
        Parent root = (Parent) myLoader.load(getClass().getResource("Menu.fxml").openStream());

        myMenuButtons = myLoader.getController();
        myMenuButtons.setMyMenuController(myMenuController);

        Scene sc1 = new Scene(root);
        stage.setScene(sc1);
        stage.show();

        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                assert DebugWriter.write("handled CloseRequest from " + this);
                System.exit(0);
            }
        });

    }

}