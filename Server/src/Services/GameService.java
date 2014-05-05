package Services;

import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Cards.Card;
import Model.Cards.Deck;
import Model.Turtle;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;


/**
 * Main model class for interacting with specific game.
 * For now there's only one game.
 */
@WebService
public class GameService{

    Deck deck = new Deck();

    Board board = new Board();

    @WebMethod
    public BoardGraph getGameBoardGraph()
    {
        return board.graph;
    }
    @WebMethod
    public List<Turtle> getGameState()
    {
        return new ArrayList<>();
    }
    @WebMethod
    public List<Card> getPlayerCards()
    {
        return new ArrayList<>();
    }
    @WebMethod
    public void playCard(Card card)
    {

    }
}
