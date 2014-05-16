package Views.Standard.GameChoosing;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameChoosingController;
import Model.SimpleGameInfo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class GameChoosingButtons {

    public TableColumn numberColumn;
    public TableColumn statusColumn;
    public TableColumn nameColumn;

    ObservableList<SimpleGameInfo> myObservableTableList;
    List<SimpleGameInfo> myTableList;

    public TableView myTable;
    private GameChoosingController controller;
    private Stage myStage;

    @FXML
    public void cancel(ActionEvent actionEvent) {
        controller.cancel();
    }
    @FXML
    public void newGame(ActionEvent actionEvent) {
        controller.create();
    }
    @FXML
    public void join(ActionEvent actionEvent) {
        controller.join();
    }

    public void setController(GameChoosingController controller) {
        this.controller = controller;
        this.controller.setClosingEvent(new Event() {
            @Override
            public void call() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        myStage.close();
                    }
                });
            }
        });

        nameColumn.setCellFactory(new PropertyValueFactory<SimpleGameInfo,String>("gameName"));
        nameColumn.setCellFactory(new PropertyValueFactory<SimpleGameInfo,String>("gameStatus"));
        nameColumn.setCellFactory(new PropertyValueFactory<SimpleGameInfo,String>("numberOfPlayers"));

        myTableList = new ArrayList<>();
        myObservableTableList = FXCollections.observableArrayList(myTableList);

        myTable.setItems(myObservableTableList);
    }

    public GameChoosingController getController() {
        return controller;
    }

    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }
}
