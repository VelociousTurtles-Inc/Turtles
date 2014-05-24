package Services;


import Client.Interfaces.GameWaiterClient;
import Model.Board.Board;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Deck;
import Model.SimplestGameInfo;
import Server.Interfaces.GameManager;
import Server.Interfaces.ServerPlayerService;

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

    List<GameWaiterClient> gameWaiterClients = new LinkedList<>();
    List<ServerPlayerService> playerServices;

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
        for(ServerPlayerService myPlayer : playerServices) {
            myPlayer.update();
        }
        playerServices.get(playerOnMove).lock();
        playerOnMove = (playerOnMove+1)%numberOfPlayers;
        playerServices.get(playerOnMove).unlock();
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
        gameWaiterClients.add(newWaiter);
        numberOfPlayers++;
        return ;
    }

    @Override
    public void removePlayer(GameWaiterClient oldWaiter) {
        gameWaiterClients.remove(oldWaiter);
        numberOfPlayers--;
        return ;
    }

    @Override
    public void startGame() throws Exception {
        myDeck = new Deck();
        board = new SimpleBoard();

        playerServices = new LinkedList<>();

        for(int i = 0; i< gameWaiterClients.size(); i++) {
            playerServices.add(new StandardPlayerService(this));
            gameWaiterClients.get(i).start((Server.Interfaces.PlayerService) playerServices.get(i));
            playerServices.get(i).lock();
        }
        started = true;
        playerOnMove = 0;
        playerServices.get(playerOnMove).unlock();
        for(GameWaiterClient myWaiter : gameWaiterClients) {
            myWaiter.closeMe();
        }
    }

    public int getMyId() {
        return myId;
    }

    public void update() throws Exception {
        for(GameWaiterClient waiter : gameWaiterClients) {
            waiter.update(numberOfPlayers);
        }
    }

    @Override
    public void cancel() throws Exception {
        for(GameWaiterClient waiter : gameWaiterClients) {
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

    @Override
    public void leave() {
        close();
    }

    public void close() {
        for (ServerPlayerService playerService : playerServices) {
            playerService.close();
        }
    }
}
