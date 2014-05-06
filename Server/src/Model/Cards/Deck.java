package Model.Cards;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by Maciej on 2014-05-05.
 */
public class Deck implements Iterable<Card>{
    LinkedList<Card> deckContainer;
    LinkedList<Card> lockContainer;
    LinkedList<Card> graveContainer;
    public Collection<Card> getCards()
    {
        return deckContainer;
    }
    public Deck()
    {
        deckContainer = new LinkedList<>();
        deckContainer.addAll(SimpleForwardCard.populate());
        /*
            ...
            all card types here
        */
        Collections.shuffle(deckContainer);
    }

    @Override
    public Iterator<Card> iterator() {
        return new Iterator<Card>() {
            Iterator<Card> inner = deckContainer.iterator();
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Card next() {
                if (inner.hasNext())
                {
                    Card tmp = inner.next();
                    lockCard(tmp);
                    return tmp;
                }
                else
                {
                    deckContainer.addAll(graveContainer);
                    graveContainer.clear();
                    Collections.shuffle(deckContainer);
                    inner = deckContainer.iterator();
                    if (!inner.hasNext())throw new ArrayIndexOutOfBoundsException();
                    return next();
                }
            }

            @Override
            public void remove() {
                throw new NotImplementedException();
            }
        };
    }
    private void moveCard(Card card, LinkedList<Card> A, LinkedList<Card> B)
    {
        if (A.remove(card))
            B.add(card);
        else
            throw new MissingFormatArgumentException("Karta nie należy do tego stosu");
    }
    public void lockCard(Card card)
    {
        moveCard(card,deckContainer,lockContainer);
    }
    public void buryCard(Card card)
    {
        moveCard(card,lockContainer,graveContainer);
    }
    public void ressurectCard(Card card)
    {
        moveCard(card,lockContainer,graveContainer);
    }
}
