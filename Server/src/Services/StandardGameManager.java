package Services;

import Chat.Message.Message;
import Enums.Colors;
import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Deck;
import Model.GameInfo;
import Server.Interfaces.*;
import Utility.Utility;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class StandardGameManager implements GameManager {

    final AtomicBoolean started = new AtomicBoolean();
    int numberOfPlayers;
    final String name;

    Deck myDeck;
    Board board;

    private int myId;

    int playerOnMove;

    private final List<WaiterService> gameWaiterClients = new LinkedList<>();
    private final List<StandardPlayerService> playerServices = new LinkedList<>();
    private final AtomicInteger zombiesCount = new AtomicInteger();
    private final int gameId;
    private final ServerGameDispenser gameDispenser;
    private Colors winner;

    private final List<Message> chatLog = new LinkedList<>();

    public StandardGameManager(String name, int gameId, ServerGameDispenser gameDispenser) {
        this.gameId = gameId;
        this.gameDispenser = gameDispenser;
        started.set(false);
        this.name = name;
        numberOfPlayers = 0;
        chatLog.add(new Message("Host", "The game has began", new Date()));
    }

    @Override
    public List<Message> getChat() {
        return chatLog;
    }

    public void addMessage(Message message) throws RemoteException {
        chatLog.add(message);
        updateChat();
    }

    public void updateChat() throws RemoteException {
        for(ServerPlayerService myPlayer : playerServices) {
            myPlayer.updateChat();
        }
    }

    @Override
    public BoardGraph getBoardGraph() throws RemoteException {
        return board.graph;
    }

    @Override
    public void updateBoard() {
        for (ServerPlayerService myPlayer : playerServices) {
            myPlayer.updateBoard();
        }
    }


    @Override
    public void playCard(int cardID, ServerPlayerService player) throws RemoteException {
        Card card = myDeck.getCardsMap().get(cardID);
        card.play(board);

        String lastPlayed = card.getImageName();

        for(ServerPlayerService myPlayer : playerServices) {
            myPlayer.setLastCard(lastPlayed);
        }

        myDeck.returnCard(cardID);
        checkGameStatus();
        updateBoard();
        if (winner == null) {
            nextTurn();
        } else {
            lockAll();
        }
    }

    @Override
    public int getNextCard() {
        return myDeck.getCard();
    }

    public void checkGameStatus() {
        winner = board.checkWins();
        if (winner != null) {
            lockAll();
            announceWinner(winner);
            try {
                announceTurtleColors();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void announceWinner(Colors winner) {
        for (ServerPlayerService playerService : playerServices) {
            playerService.announceWinner(winner);
        }
    }

    public void lockAll() {
        for (ServerPlayerService playerService : playerServices) {
            playerService.lock();
        }
    }

    @Override
    public void nextTurn() throws RemoteException {
        if (zombiesCount.get() == numberOfPlayers) {
            lockAll();
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
    public Map<Integer, Card> getInGameCards() throws RemoteException {
        return myDeck.getCardsMap();
    }

    @Override
    public boolean isFull() {
        return numberOfPlayers == 7;
    }

    @Override
    public GameInfo getGameInfo() {
        String status;
        if(started.get()) {
            status = "Started";
        }
        else {
            status = "In preparation";
        }
        GameInfo myGameInfo = new GameInfo(name, status, String.valueOf(numberOfPlayers));
        myGameInfo.setId(myId);
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
    public void addPlayer(WaiterService newWaiter) {
        gameWaiterClients.add(newWaiter);
        numberOfPlayers++;
    }

    @Override
    public void removePlayer(WaiterService oldWaiter) {
        gameWaiterClients.remove(oldWaiter);
        numberOfPlayers--;
    }

    @Override
    public void startGame() throws RemoteException {
        myDeck = new Deck();
        board = new SimpleBoard();
        List<Colors> list = Colors.getAllColors();
        Collections.shuffle(list);
        Iterator<Colors> it = list.iterator();
        for(int i = 0; i< gameWaiterClients.size(); i++) {
            if (!it.hasNext())throw new ArrayIndexOutOfBoundsException("Too little turtles");
            Colors color = it.next();
            playerServices.add(new StandardPlayerService(this, gameWaiterClients.get(i).getName(),color));
            gameWaiterClients.get(i).start(playerServices.get(i));
            playerServices.get(i).lock();
        }
        started.set(true);
        playerOnMove = 0;
        playerServices.get(playerOnMove).unlock();
        for(WaiterService myWaiter : gameWaiterClients) {
            myWaiter.closeMe();
        }
        gameDispenser.update();
    }

    public void update() throws RemoteException {
        for(WaiterService waiter : gameWaiterClients) {
            waiter.updateWaiter(numberOfPlayers);
        }
    }

    @Override
    public void cancel() throws RemoteException {
        for(WaiterService waiter : gameWaiterClients) {
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
            Iterator<WaiterService> gameWaiterClientIterator = gameWaiterClients.iterator();
            while (gameWaiterClientIterator.hasNext()) {
                WaiterService gameWaiterClient = gameWaiterClientIterator.next();
                Utility.logInfo("nextClient");
                if (gameWaiterClient != null) {
                    try {
                        gameWaiterClient.ping();
                    } catch (RemoteException e) {
                        Utility.logInfo("Removing Zombie Waiter");
                        gameWaiterClientIterator.remove();
                    }
                }
            }

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

    @Override
    public void announceTurtleColors() throws RemoteException{
        for (StandardPlayerService playerService : playerServices)
        {
            addMessage(new Message("host", playerService.getName()+ " grał żółwiem - "+ Colors.asString(playerService.getTurtleColor()),new Date()));
        }
    }
}
