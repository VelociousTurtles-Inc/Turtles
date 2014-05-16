package Model;

import Interfaces.IDeck;
import Model.Cards.Card;
import Model.Cards.SimpleForwardCard;
import java.util.*;

/**
 * Created by Maciej on 2014-05-05.
 */

public class Deck implements IDeck {
    List<Integer> avaibleCards;
    List<Integer> deadCards;

    public Deck() {
        avaibleCards = new LinkedList<>();
        deadCards = new LinkedList<>();


        Collections.shuffle(avaibleCards);
    }

    public int getCard() {
        int result = avaibleCards.get(avaibleCards.size()-1);
        avaibleCards.remove(avaibleCards.size()-1);
        return result;
    }
    public void returnCard(int cardID) {
        deadCards.add(cardID);
    }
}