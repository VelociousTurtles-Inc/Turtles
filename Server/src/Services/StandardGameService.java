package Services;

import Model.Cards.Card;
import Model.Game;
import Model.GameInfo;
import Server.Interfaces.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main model class for interacting with specific game.
 * For now there's only one game.
 */

public class StandardGameService implements GameService {

    private static List<Game> myGames;

    static GameInfo info = new GameInfo();

    public StandardGameService() {

    }

    // TODO: Move to Player class

    List<Integer> hand = new ArrayList<Integer>();

    @Override
    public Map<Integer, Card> getDeckMap(int gameID) throws Exception {
        return myGames.get(gameID).getInGameCards();
    }

    @Override
    public Interfaces.IBoard getGameBoard(int gameID) throws Exception {
        return myGames.get(gameID).getTheBoard();
    }

    @Override
    public List<Integer> getPlayerCards(int gameID, int playerID) throws Exception {
        return myGames.get(gameID).getPlayerCards(playerID);
    }

    @Override
    public void playCard(int cardID, int gameID, int playerID) throws Exception {
        myGames.get(gameID).playCard(cardID, playerID);
    }

    @Override
    public int newGame() {
        myGames.add(new Game());
        int gameID = myGames.size()-1;
        myGames.get(gameID).newPlayer();
        return gameID;
    }

    @Override
    public void joinGame(int gameID) {
        myGames.get(gameID).newPlayer();
    }

    @Override
    public void startGame(int gameID) {
        myGames.get(gameID).start();
    }

}
