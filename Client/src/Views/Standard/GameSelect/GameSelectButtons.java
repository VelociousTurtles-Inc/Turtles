package Views.Standard.GameSelect;

import Common.Interfaces.Event;
import Adapters.Interfaces.GameSelectController;
import Adapters.SimpleGameInfo;
import Model.GameInfo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameSelectButtons {

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
        controller.create();
    }
    @FXML
    public void join(ActionEvent actionEvent) throws RemoteException {
        SimpleGameInfo myInfo = (SimpleGameInfo) myTable.getSelectionModel().getSelectedItem();
        System.out.println(myInfo.getMyID());
        controller.join(myInfo.getMyID());
    }

    public void setController(final GameSelectController controller) throws RemoteException {
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
        this.controller.registerUpdateEvent(new Event() {
            @Override
            public void call() {
                List<GameInfo> tmpList = controller.getUpdateList();
                List<SimpleGameInfo> myList = new LinkedList<>();
                for (GameInfo simple : tmpList) {
                    SimpleGameInfo myInfo = new SimpleGameInfo(simple.getGameName(), simple.getGameStatus(), simple.getNumberOfPlayers());
                    myInfo.setMyID(simple.getId());
                    myList.add(myInfo);
                }
                myObservableTableList = FXCollections.observableArrayList(myList);
                myTable.setItems(myObservableTableList);
            }
        });

        nameColumn.setCellValueFactory(new PropertyValueFactory<SimpleGameInfo, String>("gameName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<SimpleGameInfo, String>("gameStatus"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<SimpleGameInfo, String>("numberOfPlayers"));

        myTableList = new ArrayList<>();
        myTableList.add(new SimpleGameInfo("nazwa1", "nazwa2", "nazwa3"));

        myObservableTableList = FXCollections.observableArrayList(myTableList);

        myTable.setItems(myObservableTableList);
        controller.initValues();
    }

    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }
}
