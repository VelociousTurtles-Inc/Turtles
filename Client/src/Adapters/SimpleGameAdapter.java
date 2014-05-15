package Adapters;


import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameController;
import Model.Board.BoardGraph;
import Model.Cards.CardInfo;
import Model.Cards.CardInfoPair;
import Model.Turtles.Turtle;
import ModelHelpers.DebugWriter;
import ModelHelpers.ServicesHelper;
import Server.Interfaces.GameService;
import Views.Standard.Game.StandardGameView;
import org.cojen.dirmi.Environment;
import org.cojen.dirmi.Session;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import Main.Client;

/**
 * Created by larhard on 05.05.14.
 */

public class SimpleGameAdapter extends Thread implements GameController {

    Map<Integer, CardInfo> normalCardsMap;
    GameService gameService;

    //List of events for Board updates

    List<Event> boardUpdates;

    //List of events for Cards updates

    List<Event> cardsUpdates;

    List<Integer> playerHand;

    public SimpleGameAdapter() throws Exception {
        normalCardsMap = new HashMap<>();

        boardUpdates = new LinkedList<Event>();
        cardsUpdates = new LinkedList<Event>();

        //WebServiceFeature[] enabledRequiredwsf = {new AddressingFeature(true, true)};

        // TODO achieving gameService
        //gameService = new ServicesTypes.GameService_Service().getGameServicePort();//new ServicesTypes.GameService_Service().getPort(GameService.class,enabledRequiredwsf);
        Environment environment = new Environment();
        Session session = environment.newSessionConnector(Client.getHost(), Client.getPort()).connect();
        gameService = (GameService) session.receive();
        for(CardInfoPair myPair : gameService.getDeckList()) {
            normalCardsMap.put(myPair.getKey(), myPair.getValue());
        }

        StandardGameView myGameView = new StandardGameView(this);
    }

    public void updateCards() {
        for(Event up : cardsUpdates) {
            up.call();
        }
    }

    public void updateBoards() {
        for(Event up : boardUpdates) {
            up.call();
        }
    }

    @Override
    public void playCard(int card) throws Exception {
        if (playerHand == null)getCards();
        int cardID = playerHand.get(card-1);
        gameService.playCard(cardID);
        updateCards();
        updateBoards();
    }

    @Override
    public void surrender() {
        System.out.println("I surended!");
    }

    @Override
    public void registerUpdateBoardEvent(Event updateBoardEvent) {
        assert DebugWriter.write("Registering new Update Board Event");
        boardUpdates.add(updateBoardEvent);
    }

    @Override
    public void registerUpdateCardsEvent(Event updateCardEvent) {
        assert DebugWriter.write("Registering new Update Cards Event");
        cardsUpdates.add(updateCardEvent);
        updateCardEvent.call();
    }

    @Override
    public List<CardInfo> getCards() throws Exception {
        playerHand = gameService.getPlayerCards();
        List<CardInfo> resultCards = new LinkedList<CardInfo>();
        for(Integer i : playerHand) {
            resultCards.add(normalCardsMap.get(i));
        }
        return resultCards;
    }

    @Override
    public List<List<Integer>> getBoard() throws Exception {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        BoardGraph myBoard = gameService.getGameBoardGraph();

        for(BoardGraph.Field f : ServicesHelper.getIterableBoard(myBoard)) {
            result.add(new LinkedList<Integer>());
            for(Turtle turtle : f.getTurtles()) {
                result.get(result.size()-1).add(turtle.getColor()+1);
            }
        }
        return result;
    }
}
