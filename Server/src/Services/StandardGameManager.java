package Services;


import Interfaces.IBoard;
import Interfaces.ICards;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Cards.Cards;
import Model.SimpleGameInfo;
import Server.Interfaces.GameManager;
import Server.Interfaces.GameStarter;

import java.util.List;
import java.util.Map;

/**
 * Main model class for interacting with specific game.
 * For now there's only one game.
 */

public class StandardGameManager implements GameStarter, GameManager {

    boolean started;
    int numberOfPlayers;
    String name;

    IBoard board;
    ICards cards;

    public StandardGameManager(String name) {
        started = false;
        this.name = name;
        numberOfPlayers = 0;
    }

    @Override
    public IBoard getBoard() {
        return board;
    }

    @Override
    public int playCard(int cardID, int playerID) {
        cards.getCardsMap().get(cardID).play(null);
        return 0;
    }

    @Override
    public Map<Integer, Card> getInGameCards() {
        return cards.getCardsMap();
    }

    @Override
    public boolean isFull() {
        if(numberOfPlayers == 7) return true;
        else return false;
    }

    @Override
    public SimpleGameInfo getGameInfo() {
        String sstatus;
        if(started == true) {
            sstatus = "Started";
        }
        else {
            sstatus = "In preparation";
        }
        return new SimpleGameInfo(name, sstatus, String.valueOf(numberOfPlayers));
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void addPlayer() {
        numberOfPlayers++;
        return ;
    }

    @Override
    public void removePlayer(int playerID) {

    }

    @Override
    public void startGame() {
        board = new SimpleBoard();
        cards = new Cards(numberOfPlayers);
    }

}
