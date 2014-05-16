package Model;

import Interfaces.IBoard;
import Interfaces.ICards;
import Interfaces.IGame;
import Model.Board.Board;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Cards.Cards;

import java.util.List;
import java.util.Map;

/**
 * Created by mz18 on 15/05/14.
 */
public class Game implements IGame {

    boolean started;
    int numberOfPlayers;

    IBoard board;
    ICards cards;

    public Game() {
    }

    @Override
    public IBoard getTheBoard() {
        return board;
    }

    @Override
    public List<Integer> getPlayerCards(int playerID) {
        return null;
    }

    @Override
    public void playCard(int cardID, int playerID) {
        cards.getCardsMap().get(cardID).play(null);
    }

    @Override
    public Map<Integer, Card> getInGameCards() {
        return cards.getCardsMap();
    }

    @Override
    public boolean isFull() {
        if(numberOfPlayers == 7) return true;
        else return false;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public int newPlayer() {
        numberOfPlayers++;
        return numberOfPlayers;
    }

    @Override
    public void start() {
        board = new SimpleBoard();
        cards = new Cards(numberOfPlayers);
    }

}
