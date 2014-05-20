package Services;


import Client.Interfaces.GameWaiterClient;
import Model.SimplestGameInfo;
import Model.Board.Board;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Deck;
import Server.Interfaces.GameManager;
import Server.Interfaces.PlayerService;
import sun.net.www.content.text.plain;

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

    int playerOnMove;

    List<GameWaiterClient> myWaiters = new LinkedList<>();
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
        System.err.println(myDeck.getCardsMap().get(cardID));
        myDeck.returnCard(cardID);
        for(PlayerService myPlayer : myPlayers) {
            myPlayer.update();
        }
        myPlayers.get(playerOnMove).lock();
        playerOnMove = (playerOnMove+1)%numberOfPlayers;
        myPlayers.get(playerOnMove).unlock();
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
    public SimplestGameInfo getGameInfo() {
        String sstatus;
        if(started == true) {
            sstatus = "Started";
        }
        else {
            sstatus = "In preparation";
        }
        SimplestGameInfo myGameInfo = new SimplestGameInfo(name, sstatus, String.valueOf(numberOfPlayers));
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
    public void addPlayer(GameWaiterClient newWaiter) {
        myWaiters.add(newWaiter);
        numberOfPlayers++;
        return ;
    }

    @Override
    public void removePlayer(GameWaiterClient oldWaiter) {
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
            myPlayers.get(i).lock();
        }
        started = true;
        playerOnMove = 0;
        myPlayers.get(playerOnMove).unlock();
        for(GameWaiterClient myWaiter : myWaiters) {
            myWaiter.closeMe();
        }
    }

    public int getMyId() {
        return myId;
    }

    public void update() throws Exception {
        for(GameWaiterClient waiter : myWaiters) {
            waiter.update(numberOfPlayers);
        }
    }

    @Override
    public void cancel() throws Exception {
        for(GameWaiterClient waiter : myWaiters) {
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
