package Model;

import Model.Cards.Card;
import Model.Cards.DoubleForwardCard;
import Model.Cards.SingleBackwardCard;
import Model.Cards.SingleForwardCard;

import java.util.*;

/**
 * Created by Maciej on 2014-05-05.
 */

public class Deck{

    HashMap<Integer, Card> cardsMap;

    List<Integer> availableCards;
    List<Integer> deadCards;

    public Deck() {
        availableCards = new LinkedList<>();
        deadCards = new LinkedList<>();
        cardsMap = new HashMap<>();

        addCards(SingleForwardCard.populate());
        addCards(DoubleForwardCard.populate());
        addCards(SingleBackwardCard.populate());

        Collections.shuffle(availableCards);
    }

    private void addCards(Iterable<Card> cards) {
        for(Card temp : cards) {
            cardsMap.put(temp.getID(), temp);
            availableCards.add(temp.getID());
        }
    }

    public int getCard() {
        int result = availableCards.get(availableCards.size()-1);
        availableCards.remove(availableCards.size()-1);

        if(availableCards.size() == 0) {
            availableCards = deadCards;
            deadCards = new LinkedList<>();
            Collections.shuffle(availableCards);
        }

        return result;
    }

    public void returnCard(int cardID) {
        deadCards.add(cardID);
    }

    public Map<Integer, Card> getCardsMap() {
        return cardsMap;
    }
}
