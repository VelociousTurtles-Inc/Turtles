package Server.Interfaces;

import Client.Interfaces.GameWaiter;
import Interfaces.IBoard;
import Model.Cards.Card;

import java.rmi.Remote;
import java.util.Map;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface GameManager extends Remote {
    public IBoard getBoard();
    public int playCard(int cardID, int playerID);
    public Map<Integer, Card> getInGameCards();


}
