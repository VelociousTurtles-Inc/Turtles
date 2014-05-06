package ViewTest;

import Views.GameViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mz18 on 6/05/14.
 */
public class GameControllerTest1 extends Application {
    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

                FXMLLoader myLoader = new FXMLLoader();
                myLoader.setLocation(getClass().getResource("Game.fxml"));
                try {
                    Parent game = (Parent) myLoader.load((getClass().getResource("Game.fxml")).openStream());


                    stage.setScene(new Scene(game));
                    stage.setResizable(false);

                    stage.show();
                    final GameViewController myOwnGameController = myLoader.getController();


                    Platform.runLater(new Runnable() {
                                          final GameViewController myCont = myOwnGameController;

                                          @Override
                                          public void run() {
                                              if (Platform.isFxApplicationThread()) myCont.setOnStartPositions();
                                              else System.out.println("Nie ten wątek :(");
                                          }
                                      }
                    );
                    final List<List<Integer>> myList = new ArrayList<List<Integer>>();
                    for(int i = 1; i<=8; i++) {
                        myList.add(new ArrayList<Integer>());
                    }
                    myList.get(1).add(3);
                    myList.get(1).add(2);

                    Thread.currentThread().sleep(2000);
                    Platform.runLater(new Runnable() {
                                          final GameViewController myCont = myOwnGameController;

                                          @Override
                                          public void run() {
                                              if (Platform.isFxApplicationThread()) myCont.updateBoard(myList);
                                          }
                                      }
                    );
                } catch (Exception e) {

                }
    }
}
