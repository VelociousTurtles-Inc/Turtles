package Model.Cards;

import Interfaces.IDeck;
import Interfaces.IPlayer;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by mz18 on 15/05/14.
 */
public class Player implements IPlayer {

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

    public Player(IDeck deck) {
        this.deck = deck;
        drawCards();
    }

    @Override
    public List<Integer> getCards() {
        List<Integer> result = new LinkedList<>();
        for(CardIDBox i : myCards) {
            result.add(i.getCardID());
        }
        return result;
    }

    @Override
    public int changeCard(int oldCardID) {
        deck.returnCard(oldCardID);
        int result = 0;
        for(CardIDBox i : myCards) {
            if(i.getCardID() == oldCardID) {
                i.setCardID(deck.getCard());
                result = i.getCardID();
                break;
            }
        }
        return result;
    }

    @Override
    public void drawCards() {
        while(myCards.size() < 5) {
            myCards.add(new CardIDBox(deck.getCard()));
        }
    }
}