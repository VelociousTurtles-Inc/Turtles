package Services;

import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Cards.CardInfoPair;
import Model.Deck;
import Model.GameInfo;
import Model.Turtle;
import com.sun.xml.ws.developer.Stateful;
import com.sun.xml.ws.developer.servlet.HttpSessionScope;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Main model class for interacting with specific game.
 * For now there's only one game.
 */
/*@Stateful
@HttpSessionScope
@WebService(serviceName = "GameService")

        //portName = "TestPort", endpointInterface = "ServicesTypes.GameService",wsdlLocation = "WEB-INF/services/GameService?wsdl")
@Addressing(enabled=true, required=true)*/
@WebService(serviceName = "GameService")
public class GameService {

    static Deck deck = new Deck();

    static Board board = new SimpleBoard();
    static GameInfo info = new GameInfo();

    public GameService() {
        for (int i = 0; i < 4; i++)
        {
            info.turtles.add(new Turtle(i));
        }
        board.graph.start.turtles.addAll(info.turtles);
        drawCards();

    }

    public List<CardInfoPair> getDeckList()
    {
        //TODO: Change Signature to a value-type resembling CardInfo which will be possible for client to parse
        //TODO: Method should return a complete collection of CardInfo sygnatures
        List<CardInfoPair>result = new ArrayList<>();
        for (Map.Entry<Integer,Card> entry:deck.cardsMap.entrySet())
        {
            result.add(new CardInfoPair(entry.getKey(),entry.getValue().getCardInfo()));
        }
        return result;
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
        if (hand.size() >= 5)return;
        for (Integer c: deck)
        {
            hand.add(c);
            if (hand.size() >= 5)
                break;
        }
    }

    @WebMethod
    public void playCard(int cardID)
    {
        if (!hand.contains(cardID))
            throw new WebServiceException("Zadany gracz nie posiada zadanej karty: "+cardID+" "+hand.toString());
        for (Iterator<Integer> it = hand.iterator();it.hasNext();)
        {
            int i = it.next();
            if (i == cardID)it.remove();
        }
        deck.buryCard(cardID);
        deck.cardsMap.get(cardID).play(board);
    }
}
