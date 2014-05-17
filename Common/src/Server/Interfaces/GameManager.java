package Server.Interfaces;

import Interfaces.IBoard;
import Model.Cards.Card;

import java.util.Map;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface GameManager {
    public IBoard getBoard();
    public int playCard(int cardID, int playerID);
    public Map<Integer, Card> getInGameCards();
}
