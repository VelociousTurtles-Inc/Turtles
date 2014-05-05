package Model.Cards;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Maciej on 2014-05-05.
 */
public class Deck {
    ArrayList<Card> container;
    public Collection<Card> getCards()
    {
        return container;
    }
    public Deck()
    {
        container = new ArrayList<>();
        container.addAll(SimpleForwardCard.populate());
        /*
            ...
            all card types here
        */
    }
}
