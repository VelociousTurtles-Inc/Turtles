package Model;

import java.io.Serializable;

public class GameInfo implements Serializable {

    private static final long serialVersionUID = -4605840894396370671L;
    private int myID;
        private String gameName;
        private String gameStatus;
        private String numberOfPlayers;

        public GameInfo(String fName, String lName, String numberOfPlayers) {
            this.gameName = new String(fName);
            this.gameStatus = new String(lName);
            this.numberOfPlayers = new String(numberOfPlayers);
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String fName) {
            gameName = fName;
        }

        public String getGameStatus() {
            return gameStatus;
        }

        public void setGameStatus(String fName) {
            gameStatus = fName;
        }

        public String getNumberOfPlayers() {
            return numberOfPlayers;
        }

        public void setNumberOfPlayers(String fName) {
            numberOfPlayers = fName;
        }

    public int getMyID() {
        return myID;
    }

    public void setMyID(int myID) {
        this.myID = myID;
    }
}
