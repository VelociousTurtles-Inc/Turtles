package Adapters;


import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameController;
import ModelHelpers.ServicesHelper;
import ServicesTypes.*;
import Views.Standard.Game.StandardGameView;

import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.soap.AddressingFeature;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public SimpleGameAdapter() {
        normalCardsMap = new HashMap<>();

        boardUpdates = new LinkedList<Event>();
        cardsUpdates = new LinkedList<Event>();

        //WebServiceFeature[] enabledRequiredwsf = {new AddressingFeature(true, true)};

        gameService = new ServicesTypes.GameService_Service().getGameServicePort();//new ServicesTypes.GameService_Service().getPort(GameService.class,enabledRequiredwsf);
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
    public void playCard(int card) {
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
        boardUpdates.add(updateBoardEvent);
    }

    @Override
    public void registerUpdateCardsEvent(Event updateCardEvent) {
        cardsUpdates.add(updateCardEvent);
    }

    @Override
    public List<CardInfo> getCards() {
        playerHand = gameService.getPlayerCards();
        List<CardInfo> resultCards = new LinkedList<CardInfo>();
        for(Integer i : playerHand) {
            resultCards.add(normalCardsMap.get(i));
        }
        return resultCards;
    }

    @Override
    public List<List<Integer>> getBoard() {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        BoardGraph myBoard = gameService.getGameBoardGraph();

        for(Field f : ServicesHelper.getIterableBoard(myBoard)) {
            result.add(new LinkedList<Integer>());
            for(Turtle turtle : f.getTurtles()) {
                result.get(result.size()-1).add(turtle.getColor());
            }
        }
        return result;
    }
}
