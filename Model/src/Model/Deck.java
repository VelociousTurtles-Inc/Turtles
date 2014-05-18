package Model;

import Model.Cards.Card;
import Model.Cards.SimpleForwardCard;

import java.util.*;

/**
 * Created by Maciej on 2014-05-05.
 */
public class Deck{
    public Map<Integer, Card> cardsMap;
    List<Integer> deckContainer;
    Set<Integer> lockContainer;
    Set<Integer> graveContainer;
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

    public Integer drawCard(){
        if (deckContainer.size() == 0) throw new ArrayIndexOutOfBoundsException();

        Integer temp = deckContainer.get(0);
        deckContainer.remove(0);
        lockContainer.add(temp);

        if (deckContainer.size() == 0){
            deckContainer.addAll(graveContainer);
            graveContainer.clear();

            Collections.shuffle(deckContainer);
        }

        return temp;
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
