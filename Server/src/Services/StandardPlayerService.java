package Services;

import Chat.Message.Message;
import Client.Interfaces.GameClient;
import Enums.Colors;
import Model.Board.BoardGraph;
import Model.Cards.Card;
import Server.Interfaces.GameManager;
import Server.Interfaces.PlayerService;
import Server.Interfaces.ServerPlayerService;
import Utility.Utility;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class StandardPlayerService implements PlayerService, ServerPlayerService {

    private final GameManager myManager;
    private GameClient myClient;
    private List<CardIDBox> myCards;
    private final AtomicBoolean locked = new AtomicBoolean();
    private final AtomicBoolean dead = new AtomicBoolean(false);

    private final String myName;

    public String getName() {
        return myName;
    }

    @Override
    public void setPlayerOnMove(int playerOnMove) throws RemoteException {
        myClient.setPlayerOnMove(playerOnMove);
    }

    public StandardPlayerService(GameManager myManager, String name) throws RemoteException {
        this.myManager = myManager;
        this.myName = name;
        List<Integer> tmpList = myManager.getHand();
        myCards = new LinkedList<>();
        for(Integer i : tmpList) {
            myCards.add(new CardIDBox(i));
        }
    }

    @Override
    public void setClient(GameClient myClient) throws RemoteException {
        this.myClient = myClient;
        if(locked.get()) lock();
        else unlock();
    }

    @Override
    public boolean isLocked() {
        synchronized (locked) {
            return locked.get();
        }
    }

    private class CardIDBox {
        int cardID;

        CardIDBox(int cardID) {
            this.cardID = cardID;
        }
        public int getCardID() {
            return cardID;
        }

        public void setCardID(int cardID) {
            this.cardID = cardID;
        }
    }

    @Override
    public List<Integer> getPlayerCards() {
        List<Integer> result = new LinkedList<>();
        for(CardIDBox i : myCards) {
            result.add(i.getCardID());
        }
        return result;
    }

    @Override
    public Map<Integer, Card> getCardsMap() throws RemoteException {
        return myManager.getInGameCards();
    }

    @Override
    public void playCard(int cardNumber) throws RemoteException {
        if (!isLocked()) {
            myManager.playCard(myCards.get(cardNumber).getCardID());
            myCards.get(cardNumber).setCardID(myManager.getNextCard());
            updateCards();
        }
    }

    @Override
    public String chatText() throws RemoteException {
        return myManager.getChatLog();
    }

    @Override
    public void postMessage(String a) throws RemoteException {
        myManager.addMessage(new Message(getName(), a, new Date()));
    }

    @Override
    public BoardGraph getGameBoardGraph() throws RemoteException {
        return myManager.getBoardGraph();
    }

    @Override
    public List<String> GetListOfPlayers() throws RemoteException {
        return myManager.GetListOfPlayers();
    }

    public void leave() throws RemoteException {
        setZombie();
    }

// START OF NO REMOTE SECTION
    @Override
    public void checkZombieness() {
        if (!isZombie()) {
            try {
                myClient.ping();
            } catch (RemoteException e) {
                Utility.logInfo("Assumed player is zombie => removing");
                try {
                    leave();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateChat(String a) {
        try {
            myClient.updateChat(a);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void updateBoard() {
        try {
            myClient.updateBoards();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void updateCards() {
        try {
            myClient.updateCards();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isZombie() {
        return dead.get();
    }

    public void setZombie() {
        try {
            myManager.addZombie();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        dead.set(true);
        if (!isLocked()) {
            try {
                myManager.nextTurn();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void close() {
        try {
            myClient.close();
        } catch (RemoteException e) {
            e.printStackTrace();
    }
    }

    @Override
    public void lock() {
        synchronized (locked) {
            this.locked.set(true);
            try {
                myClient.updateLock();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (locked) {
            this.locked.set(false);
            try {
                myClient.updateLock();
            } catch (RemoteException e) {
                e.printStackTrace();
                setZombie();
            }
        }
    }

// END OF NO REMOTE SECTION
    @Override
    public void announceWinner(Colors winner) {
        try {
            myClient.announceWinner(winner.toInteger());
        } catch (RemoteException e) {
            e.printStackTrace();
            setZombie();
        }
    }
}
