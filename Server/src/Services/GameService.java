package Services;

import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Deck;
import Model.GameInfo;
import Model.Turtle;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main model class for interacting with specific game.
 * For now there's only one game.
 */
@WebService
public class GameService {

    Deck deck = new Deck();

    Board board = new SimpleBoard();
    GameInfo info = new GameInfo();

    public GameService() {
        for (int i = 0; i < 4; i++)
        {
            info.turtles.add(new Turtle(i));
        }
        board.graph.start.turtles.addAll(info.turtles);

    }

    public List<Pair<Integer,CardInfo>> getDeckList()
    {
        //TODO: Change Signature to a value-type resembling CardInfo which will be possible for client to parse
        //TODO: Method should return a complete collection of CardInfo sygnatures
        return ;
    }

    @WebMethod
    public BoardGraph getGameBoardGraph()
    {
        return board.graph;
    }
    @WebMethod
    public GameInfo getGameState()
    {
        return info;
    }
    // TODO: Move to Player class

    List<Integer> hand = new ArrayList<Integer>();

    @WebMethod
    public List<Integer> getPlayerCards()
    {
        if (hand.size() < 5)drawCards();
        return hand;
    }
    private void drawCards()

    {
        assert (hand.size() < 5);
        for (Card c: deck)
        {
            hand.add(c.getID());
            if (hand.size() >= 5)
                break;
        }
    }

    @WebMethod
    public void playCard(int cardID)
    {
        // I changed signature to more WebService Friendly
        // TODO: Make it work again.
        throw new WebServiceException();
        /*if (!hand.contains(card))
            throw new WebServiceException("Zadany gracz nie posiada zadanej karty");
        hand.remove(card);
        deck.buryCard(card);
        card.play(board);*/
    }
}
