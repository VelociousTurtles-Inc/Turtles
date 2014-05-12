package Services;

<<<<<<< HEAD
import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Cards.Deck;
import Model.GameInfo;
import Model.Turtle;
=======
>>>>>>> origin/DangerouslyManyChangesToCheck

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Iterator;
=======
import java.util.HashMap;
>>>>>>> origin/DangerouslyManyChangesToCheck
import java.util.List;
import java.util.Map;

import Model.*;
import Model.Board.*;
import Model.Cards.*;

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
<<<<<<< HEAD
=======

    public void getDeckList()
    {
        //TODO: Change Signature to a value-type resembling CardInfo which will be possible for client to parse
        //TODO: Method should return a complete collection of CardInfo sygnatures
        return ;
    }
>>>>>>> origin/DangerouslyManyChangesToCheck

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
<<<<<<< HEAD
    List<Card> hand = new ArrayList<Card>();
=======
    List<Integer> hand = new ArrayList<>();
>>>>>>> origin/DangerouslyManyChangesToCheck

    @WebMethod
    public List<Integer> getPlayerCards()
    {
        if (hand.size() < 5)drawCards();
        return hand;
    }
    private void drawCards()
<<<<<<< HEAD
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
=======
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
>>>>>>> origin/DangerouslyManyChangesToCheck
    }
}
