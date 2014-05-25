package Views.Standard.GameChoosing;

import Events.Event;
import Adapters.Interfaces.GameSelectController;
import Adapters.SimpleGameInfo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
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
    private GameSelectController controller;
    private Stage myStage;

    @FXML
    public void cancel(ActionEvent actionEvent) {
        controller.cancel();
    }
    @FXML
    public void newGame(ActionEvent actionEvent) {
        //controller.create();
        myObservableTableList.add(new SimpleGameInfo("Something1", "Something2", "Something3"));
    }
    @FXML
    public void join(ActionEvent actionEvent) {
        myObservableTableList.remove(0);
    }

    public void setController(GameSelectController controller) {
        this.controller = controller;
        this.controller.registerClosingEvent(new Event() {
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

        nameColumn.setCellValueFactory(new PropertyValueFactory<SimpleGameInfo, String>("gameName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<SimpleGameInfo, String>("gameStatus"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<SimpleGameInfo, String>("numberOfPlayers"));

        myTableList = new ArrayList<>();
        myTableList.add(new SimpleGameInfo("nazwa1", "nazwa2", "nazwa3"));

        myObservableTableList = FXCollections.observableArrayList(myTableList);

        myTable.setItems(myObservableTableList);
    }

    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }
}
