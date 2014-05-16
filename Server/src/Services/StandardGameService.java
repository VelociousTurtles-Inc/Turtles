package Services;

import Client.Interfaces.GameClient;
import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Board.SimpleBoard;
import Model.Cards.Card;
import Model.Cards.CardInfoPair;
import Model.Cards.PlayedCard;
import Model.Deck;
import Model.Game.GameInfo;
import Model.Turtles.Turtle;
import Model.Utility.Utility;
import Server.Interfaces.GameService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Main model class for interacting with specific game.
 * For now there's only one game.
 */

public class StandardGameService implements GameService {

    private Deck deck = new Deck();

    private Board board = new SimpleBoard();
    private GameInfo info = new GameInfo();

    {
        for (int i = 0; i < 5; i++)
        {
            info.turtles.add(new Turtle(i));
        }
        board.graph.start.turtles.addAll(info.turtles);

    }

    private GameClient gameClient;

    public StandardGameService() {
        drawCards();

    }

    @Override
    public void registerClient(GameClient gameClient) throws Exception {
        this.gameClient = gameClient;
    }

    @Override
    public List<CardInfoPair> getDeckList() throws Exception
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

    @Override
    public BoardGraph getGameBoardGraph() throws Exception
    {
           Utility.Debug.log(Level.INFO,"[getGameBoard DEBUG] board>>"+board.graph.toString());
        return board.graph;
    }

    @Override
    public GameInfo getGameState() throws Exception
    {
        return info;
    }
    // TODO: Move to Player class

    List<Integer> hand = new ArrayList<>();

    @Override
    public List<Integer> getPlayerCards() throws Exception
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

    @Override
    public void playCard(int cardID) throws Exception
    {
        if (!hand.contains(cardID))
            throw new RemoteException("No player has card: "+cardID+" "+hand.toString());
        hand.remove((Object)cardID);
        deck.buryCard(cardID);
        deck.cardsMap.get(cardID).play(board);
    }

    @Override
    public List<PlayedCard> getPlayedCards() throws Exception {
        // TODO make it work
        return Arrays.asList(new PlayedCard(1, 1), new PlayedCard(1, 2), new PlayedCard(1, 3));
    }
}
