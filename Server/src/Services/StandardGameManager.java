package Services;


import Client.Interfaces.GameWaiter;
import Client.Interfaces.SimpliestGameInfo;
import Model.Board.Board;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Deck;
import Server.Interfaces.GameManager;
import Server.Interfaces.PlayerService;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Main model class for interacting with specific game.
 * For now there's only one game.
 */

public class StandardGameManager implements GameManager {

    boolean started;
    int numberOfPlayers;
    String name;

    Deck myDeck;
    Board board;

    private int myId;

    List<GameWaiter> myWaiters = new LinkedList<>();
    List<PlayerService> myPlayers;

    public StandardGameManager(String name) {
        started = false;
        this.name = name;
        numberOfPlayers = 0;
    }

    @Override
    public Board getBoard() throws RemoteException {
        return board;
    }

    @Override
    public int playCard(int cardID) throws RemoteException {
        myDeck.getCardsMap().get(cardID).play(board);
        myDeck.returnCard(cardID);
        for(PlayerService myPlayer : myPlayers) {
            myPlayer.update();
        }
        return myDeck.getCard();
    }

    @Override
    public Map<Integer, Card> getInGameCards() throws Exception {
        return myDeck.getCardsMap();
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
    public void startGame() throws Exception {
        myDeck = new Deck();
        board = new SimpleBoard();

        myPlayers = new LinkedList<>();

        for(int i = 0; i<myWaiters.size(); i++) {
            myPlayers.add(new StandardPlayerService(this));
            myWaiters.get(i).start(myPlayers.get(i));
        }
        started = true;
    }

    public int getMyId() {
        return myId;
    }

    public void update() throws Exception {
        for(GameWaiter waiter : myWaiters) {
            waiter.update(numberOfPlayers);
        }
    }

    @Override
    public void cancel() throws Exception {
        for(GameWaiter waiter : myWaiters) {
            waiter.cancel();
        }
    }

    @Override
    public List<Integer> getHand() throws RemoteException {
        List<Integer> result = new LinkedList<>();
        for(int i = 0; i<5; i++) {
            result.add(myDeck.getCard());
        }
        return result;
    }
}
