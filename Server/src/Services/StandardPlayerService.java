package Services;

import Interfaces.IBoard;
import Interfaces.IDeck;
import Server.Interfaces.GameManager;
import Server.Interfaces.Player;
import Server.Interfaces.PlayerService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardPlayerService implements PlayerService, Player {

    @Override
    public void update() {

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

    IDeck deck;
    List<CardIDBox> myCards;
    private GameManager myGameManager;
    private int myID;

    public StandardPlayerService(GameManager myGameManager) {
        this.myGameManager = myGameManager;
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
    public void playCard(int cardID) throws Exception {
        int newCard = myGameManager.playCard(cardID, myID);
        for(int i = 1; i<=5; i++) {

        }
    }

    @Override
    public IBoard getGameBoard() throws Exception {
        return myGameManager.getBoard();
    }
}
