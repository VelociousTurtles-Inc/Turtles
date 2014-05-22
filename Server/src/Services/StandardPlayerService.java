package Services;

import Client.Interfaces.GameClient;
import Model.Board.Board;
import Model.Cards.Card;
import Server.Interfaces.GameManager;
import Server.Interfaces.PlayerService;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardPlayerService implements PlayerService {

    private GameManager myManager;
    private GameClient myClient;
    private List<CardIDBox> myCards;
    private final AtomicBoolean locked = new AtomicBoolean();

    public StandardPlayerService(GameManager myManager) throws Exception {
        this.myManager = myManager;
        List<Integer> tmpList = myManager.getHand();
        myCards = new LinkedList<>();
        for(Integer i : tmpList) {
            myCards.add(new CardIDBox(i));
        }
    }

    @Override
    public void setClient(GameClient myClient) throws RemoteException {
        this.myClient = myClient;
        if(locked.get()) myClient.lock();
        else myClient.unlock();
    }

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

    public void update() throws RemoteException {
        myClient.updateBoards();
        myClient.updateCards();
    }

    @Override
    public void lockMeOrNot() throws RemoteException {
        if(locked.get()) myClient.lock();
        else myClient.unlock();
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
    public void lock() throws RemoteException {
        this.locked.set(true);
        myClient.lock();
    }

    @Override
    public void unlock() throws RemoteException {
        this.locked.set(false);
        myClient.unlock();
    }

    @Override
    public Board getGameBoard() throws RemoteException {
        return myManager.getBoard();
    }
}
