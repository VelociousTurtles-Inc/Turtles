package Model;

import Interfaces.IDeck;
import Model.Cards.Card;
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

        for(Card temp : SingleForwardCard.populate()) {
            cardsMap.put(temp.getID(), temp);
            avaibleCards.add(temp.getID());
        }

        Collections.shuffle(avaibleCards);
    }

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
    public void returnCard(int cardID) {
        deadCards.add(cardID);
    }

    public Map<Integer, Card> getCardsMap() {
        return cardsMap;
    }
}
