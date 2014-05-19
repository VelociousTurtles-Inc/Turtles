package Model.Cards;

import Interfaces.ICards;
import Model.Deck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mz18 on 15/05/14.
 */
public class Cards implements ICards {

    HashMap<Integer, Card> cardsMap;
    List<Player> players;
    Deck deck;

    public Cards(int numberOfPlayers)
    {
        deck = new Deck();
        for(int i = 1; i<=numberOfPlayers; i++) {
            players.add(new Player(deck));
        }

        cardsMap = new HashMap<>();

        for(Card temp : SimpleForwardCard.populate()) {
            cardsMap.put(temp.getID(), temp);
        }

    }
    @Override
    public Map<Integer, Card> getCardsMap() {
        return null;
    }

    @Override
    public List<Integer> getPlayerCards(int playerID) {
        return null;
    }

    @Override
    public void playCard(int cardID, int playerID) {
        players.get(playerID).changeCard(cardID);
    }
}
