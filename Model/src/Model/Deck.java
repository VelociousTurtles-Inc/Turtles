package Model;

import Model.Cards.Card;
import Model.Cards.SimpleForwardCard;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by Maciej on 2014-05-05.
 */
public class Deck implements Iterable<Integer>{
    Map<Integer, Card> cardsMap;
    List<Integer> deckContainer;
    Set<Integer> lockContainer;
    Set<Integer> graveContainer;
    public Map<Integer,Card> getCards()
    {
        return cardsMap;
    }
    public Deck()
    {
        deckContainer = new LinkedList<Integer>();
        lockContainer = new HashSet<Integer>();
        graveContainer = new HashSet<Integer>();
        cardsMap = new HashMap<Integer, Card>();
        for(Card temp : SimpleForwardCard.populate()){
            cardsMap.put(temp.getID(), temp);
            deckContainer.add(temp.getID());
        }

        Collections.shuffle(deckContainer);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Iterator<Integer> inner = deckContainer.iterator();
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                if (inner.hasNext())
                {
                    Integer temp = inner.next();
                    inner.remove();
                    lockContainer.add(temp);
                    return temp;
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
    private void moveCard(Integer cardID, Collection<Integer> A, Collection<Integer> B)
    {
        if (A.remove(cardID))
            B.add(cardID);
        else
            throw new MissingFormatArgumentException("Karta nie należy do tego stosu");
    }
    public void lockCard(Integer cardID)
    {
        moveCard(cardID, deckContainer,lockContainer);
    }
    public void buryCard(Integer cardID)
    {
        moveCard(cardID, lockContainer,graveContainer);
    }
    public void ressurectCard(Integer cardID)
    {
        moveCard(cardID, lockContainer,graveContainer);
    }
}
