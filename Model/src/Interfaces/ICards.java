package Interfaces;

import Model.Cards.Card;

import java.util.List;
import java.util.Map;

/**
 * Created by mz18 on 15/05/14.
 */
public interface ICards {
    public Map<Integer, Card> getCardsMap();
    public List<Integer> getPlayerCards(int playerID);
    public void playCard(int cardID, int playerID);
}
