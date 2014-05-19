package Interfaces;

import Model.Cards.Card;

import java.util.List;
import java.util.Map;

/**
 * Created by mz18 on 15/05/14.
 */
public interface IGame {
    public IBoard getTheBoard();
    public List<Integer> getPlayerCards(int playerID);
    public void playCard(int cardID, int playerID);
    public Map<Integer, Card> getInGameCards();

    public boolean isFull();
    public boolean isStarted();

    public int newPlayer();
    public void start();
}
