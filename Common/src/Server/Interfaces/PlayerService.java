package Server.Interfaces;

import Interfaces.IBoard;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface PlayerService extends Remote {
    void playCard(int cardID) throws Exception;
    IBoard getGameBoard() throws Exception;
    List<Integer> getPlayerCards();
}
