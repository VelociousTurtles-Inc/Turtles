package Adapters;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by michaziobro on 16.05.2014.
 */

public class SimpleGameInfo {

    private int myID;
    private final SimpleStringProperty gameName;
    private final SimpleStringProperty gameStatus;
    private final SimpleStringProperty numberOfPlayers;

    public SimpleGameInfo(String fName, String lName, String numberOfPlayers) {
        this.gameName = new SimpleStringProperty(fName);
        this.gameStatus = new SimpleStringProperty(lName);
        this.numberOfPlayers = new SimpleStringProperty(numberOfPlayers);
    }

    public String getGameName() {
        return gameName.get();
    }

    public void setGameName(String fName) {
        gameName.set(fName);
    }

    public String getGameStatus() {
        return gameStatus.get();
    }

    public void setGameStatus(String fName) {
        gameStatus.set(fName);
    }

    public String getNumberOfPlayers() {
        return numberOfPlayers.get();
    }

    public void setNumberOfPlayers(String fName) {
        numberOfPlayers.set(fName);
    }

    public int getMyID() {
        return myID;
    }

    public void setMyID(int myID) {
        this.myID = myID;
    }
}

