package Services;


import Client.Interfaces.GameWaiter;
import Client.Interfaces.SimpliestGameInfo;
import Interfaces.IBoard;
import Interfaces.ICards;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Cards.Cards;
import Server.Interfaces.GameManager;
import Server.Interfaces.GameStarter;

import java.util.LinkedList;
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
    private int myId;

    List<GameWaiter> myWaiters = new LinkedList<>();

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
    public SimpliestGameInfo getGameInfo() {
        String sstatus;
        if(started == true) {
            sstatus = "Started";
        }
        else {
            sstatus = "In preparation";
        }
        SimpliestGameInfo myGameInfo = new SimpliestGameInfo(name, sstatus, String.valueOf(numberOfPlayers));
        myGameInfo.setMyID(myId);
        return myGameInfo;
    }

    @Override
    public void setId(int id) {
        myId = id;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void addPlayer(GameWaiter newWaiter) {
        myWaiters.add(newWaiter);
        numberOfPlayers++;
        return ;
    }

    @Override
    public void removePlayer(GameWaiter oldWaiter) {
        myWaiters.remove(oldWaiter);
        numberOfPlayers--;
        return ;
    }

    @Override
    public void startGame() {
        board = new SimpleBoard();
        cards = new Cards(numberOfPlayers);
    }

    public int getMyId() {
        return myId;
    }

    public void update() throws Exception {
        for(GameWaiter waiter : myWaiters) {
            waiter.update(numberOfPlayers);
        }
    }
}
