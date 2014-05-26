package Services;


import Client.Interfaces.GameWaiterClient;
import Model.Board.Board;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Deck;
import Model.GameInfo;
import Model.Utility.Utility;
import Server.Interfaces.*;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class StandardGameManager implements GameManager {

    final AtomicBoolean started = new AtomicBoolean();
    int numberOfPlayers;
    String name;

    Deck myDeck;
    Board board;

    private int myId;

    int playerOnMove;

    private final List<GameWaiterClient> gameWaiterClients = new LinkedList<>();
    private final List<ServerPlayerService> playerServices = new LinkedList<>();
    private final AtomicInteger zombiesCount = new AtomicInteger();
    private int gameId;
    private ServerGameDispenser gameDispenser;

    public StandardGameManager(String name, int gameId, ServerGameDispenser gameDispenser) {
        this.gameId = gameId;
        this.gameDispenser = gameDispenser;
        started.set(false);
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
        nextTurn();
        return myDeck.getCard();
    }

    @Override
    public void nextTurn() throws RemoteException {
        if (zombiesCount.get() == numberOfPlayers) {
            return;
        }
        playerServices.get(playerOnMove).lock();
        do {
            playerOnMove = (playerOnMove+1)%numberOfPlayers;
        } while (playerServices.get(playerOnMove).isZombie() && zombiesCount.get() < numberOfPlayers);
        playerServices.get(playerOnMove).unlock();
        for(ServerPlayerService player : playerServices) {
            player.setPlayerOnMove(playerOnMove);
        }
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
    public GameInfo getGameInfo() {
        String status;
        if(started.get() == true) {
            status = "Started";
        }
        else {
            status = "In preparation";
        }
        GameInfo myGameInfo = new GameInfo(name, status, String.valueOf(numberOfPlayers));
        myGameInfo.setMyID(myId);
        return myGameInfo;
    }

    @Override
    public void setId(int id) {
        myId = id;
    }

    @Override
    public boolean isStarted() {
        return started.get();
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

        for(int i = 0; i< gameWaiterClients.size(); i++) {
            playerServices.add(new StandardPlayerService(this, "Player" + i));
            gameWaiterClients.get(i).start((Server.Interfaces.PlayerService) playerServices.get(i));
            playerServices.get(i).lock();
        }
        started.set(true);
        playerOnMove = 0;
        playerServices.get(playerOnMove).unlock();
        for(GameWaiterClient myWaiter : gameWaiterClients) {
            myWaiter.closeMe();
        }
        gameDispenser.update();
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
        gameDispenser.cancelGame(gameId);
    }

    @Override
    public void checkForZombies() {
        if (isStarted()) {
            for (ServerPlayerService playerService : playerServices) {
                playerService.checkZombieness();
            }

            if (playerServices.isEmpty() || zombiesCount.get() == numberOfPlayers) {
                Utility.logInfo("Removing zombie game #" + gameId);
                gameDispenser.cancelGame(gameId);
            }
        }
        else {
            Utility.logInfo("Trying to clean");
            Iterator<GameWaiterClient> gameWaiterClientIterator = gameWaiterClients.iterator();
            do {
                GameWaiterClient gameWaiterClient = gameWaiterClientIterator.next();
                Utility.logInfo("nextClient");
                if (gameWaiterClient != null) {
                    try {
                        gameWaiterClient.ping();
                    } catch (RemoteException e) {
                        Utility.logInfo("Removing Zombie Waiter");
                        gameWaiterClientIterator.remove();
                    }
                }
            } while (gameWaiterClientIterator.hasNext());

            if (gameWaiterClients.isEmpty()) {
                Utility.logInfo("Removing zombie game #" + gameId);
                gameDispenser.cancelGame(gameId);
            }
        }
    }

    @Override
    public void addZombie() throws RemoteException {
        zombiesCount.incrementAndGet();
    }

    @Override
    public List<String> GetListOfPlayers() throws RemoteException {
        List<String> result = new LinkedList<>();
        for(ServerPlayerService player : playerServices) {
            result.add(player.getName());
            System.out.println(player.getName());
        }
        return result;
    }
}
