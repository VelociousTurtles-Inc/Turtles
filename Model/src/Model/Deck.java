package Model;

import Interfaces.IDeck;
import Model.Cards.Card;
import Model.Cards.DoubleForwardCard;
import Model.Cards.SingleBackwardCard;
import Model.Cards.SingleForwardCard;

import java.util.*;

/**
 * Created by Maciej on 2014-05-05.
 */

public class Deck implements IDeck {

    HashMap<Integer, Card> cardsMap;

    List<Integer> avaibleCards;
    List<Integer> deadCards;

    public Deck() {
        avaibleCards = new LinkedList<>();
        deadCards = new LinkedList<>();
        cardsMap = new HashMap<>();

        addCards(SingleForwardCard.populate());
        addCards(DoubleForwardCard.populate());
        addCards(SingleBackwardCard.populate());

        Collections.shuffle(avaibleCards);
    }

    private void addCards(Iterable<Card> cards) {
        for(Card temp : cards) {
            cardsMap.put(temp.getID(), temp);
            avaibleCards.add(temp.getID());
        }
    }

    @Override
    public int getCard() {
        int result = avaibleCards.get(avaibleCards.size()-1);
        avaibleCards.remove(avaibleCards.size()-1);

        if(avaibleCards.size() == 0) {
            avaibleCards = deadCards;
            deadCards = new LinkedList<>();
            Collections.shuffle(avaibleCards);
        }

        return result;
    }

    @Override
    public void returnCard(int cardID) {
        deadCards.add(cardID);
    }

    public Map<Integer, Card> getCardsMap() {
        return cardsMap;
    }
}
