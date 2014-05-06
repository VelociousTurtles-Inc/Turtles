package Services;

import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Cards.Deck;
import Model.GameInfo;
import Model.Turtle;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Main model class for interacting with specific game.
 * For now there's only one game.
 */
@WebService
public class GameService{

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
    List<Card> hand = new ArrayList<>();

    @WebMethod
    public List<Card> getPlayerCards()
    {
        if (hand.size() < 5)drawCards();
        return hand;
    }
    private void drawCards()
    {
        assert (hand.size() < 5);
        for (Card c: deck)
        {
            hand.add(c);
            if (hand.size() >= 5)
                break;
        }
    }

    @WebMethod
    public void playCard(Card card)
    {
        if (!hand.contains(card))
            throw new WebServiceException("Zadany gracz nie posiada zadanej karty");
        hand.remove(card);
        deck.buryCard(card);
        switch (card.getType())
        {
            case SimpleForward:
                outer: for (BoardGraph.Field field : board.graph)
                {
                    for (Iterator<Turtle> it = field.turtles.iterator(); it.hasNext();)
                    {
                        Turtle turtle = it.next();
                        if (field.successors.size() != 1)throw new IllegalArgumentException();
                        if (turtle.color == card.getColor())
                        {
                            do {
                                field.successors.get(0).turtles.add(turtle);
                                if (it.hasNext())turtle = it.next();
                                else turtle = null;
                            }while(turtle != null);
                            break outer;
                        }

                    }
                }
        }
    }
}
