package Services;

import Client.Interfaces.GameClient;
import Enums.Colors;
import Model.Board.Board;
import Model.Cards.Card;
import Utility.Utility;
import Server.Interfaces.GameManager;
import Server.Interfaces.PlayerService;
import Server.Interfaces.ServerPlayerService;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardPlayerService implements PlayerService, ServerPlayerService {

    private GameManager myManager;
    private GameClient myClient;
    private List<CardIDBox> myCards;
    private final AtomicBoolean locked = new AtomicBoolean();
    private final AtomicBoolean dead = new AtomicBoolean(false);

    private String myName;

    public String getName() {
        return myName;
    }

    @Override
    public void setPlayerOnMove(int playerOnMove) throws RemoteException {
        myClient.setPlayerOnMove(playerOnMove);
    }

    public StandardPlayerService(GameManager myManager, String name) throws Exception {
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
    public Map<Integer, Card> getCardsMap() throws Exception {
        return myManager.getInGameCards();
    }

    @Override
    public void playCard(int cardNumber) throws RemoteException {
        if (!isLocked()) myCards.get(cardNumber).setCardID(myManager.playCard(myCards.get(cardNumber).getCardID()));
    }

    @Override
    public Board getGameBoard() throws RemoteException {
        return myManager.getBoard();
    }


    @Override
    public List<String> GetListOfPlayers() throws RemoteException {
        return myManager.GetListOfPlayers();
    }

    public void leave() throws RemoteException {
        setZombie();
    }

// START OF NOTREMOTE SECTION
    @Override
    public boolean checkZombieness() {
        if (!isZombie()) {
            try {
                myClient.ping();
            } catch (RemoteException e) {
                e.printStackTrace();
                Utility.logInfo("Assumed player is zombie => removing");
                try {
                    leave();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return isZombie();
    }

    public void update() {
        try {
            myClient.updateBoards();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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

// END OF NOTREMOTE SECTION
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
