package Adapters;


import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameController;
import Client.Interfaces.GameClient;
import Model.Board.BoardGraph;
import Model.Cards.Card;
import Model.Turtles.Turtle;
import ModelHelpers.DebugWriter;
import ModelHelpers.ServicesHelper;
import Server.Interfaces.PlayerService;
import Views.Board;
import Views.Standard.Game.StandardGameView;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by larhard on 05.05.14.
 */

public class SimpleGameAdapter extends Thread implements GameController, GameClient {

    Map<Integer, Card> normalCardsMap;
    PlayerService playerService;

    List<Event> boardUpdates;
    List<Event> cardsUpdates;

    List<Integer> playerHand;

    public SimpleGameAdapter() throws Exception {
        normalCardsMap = new HashMap<>();

        boardUpdates = new LinkedList<>();
        cardsUpdates = new LinkedList<>();

        //WebServiceFeature[] enabledRequiredwsf = {new AddressingFeature(true, true)};

        // TODO achieving gameStarter
        //gameStarter = new ServicesTypes.GameService_Service().getGameServicePort();//new ServicesTypes.GameService_Service().getPort(GameStarter.class,enabledRequiredwsf);
/*      Environment environment = new Environment();
        Session session = environment.newSessionConnector(Client.getHost(), Client.getPort()).connect();

        gameService = (GameStarter) session.receive();
        normalCardsMap = gameService.getDeckMap(0);
        =======
        GameDispenser gameDispenser = (GameDispenser) session.receive();
        Integer gameId = gameDispenser.createNewGame();
        if (gameId == null) {
            throw new NullPointerException();
        }
        gameService = gameDispenser.connectToGame(gameId);
        gameService.registerClient(this);

        for(CardInfoPair myPair : gameService.getDeckList()) {
            normalCardsMap.put(myPair.getKey(), myPair.getValue());
        }*/
    }

    @Override
    public void updateCards() {
        for(Event up : cardsUpdates) {
            up.call();
        }
    }

    @Override
    public void updateBoards() {
        for(Event up : boardUpdates) {
            up.call();
        }
    }

    @Override
    public void start(PlayerService myService) throws Exception {
        playerService = myService;
        normalCardsMap = myService.getCardsMap();
        myService.setClient(this);
        StandardGameView myGameView = new StandardGameView(this);
    }

    @Override
    public void playCard(int card) throws Exception {
        /*if (playerHand == null)getCards();
        int cardID = playerHand.get(card-1);
        playerService.playCard(cardID);
        updateCards();
        updateBoards();*/
        playerService.playCard(card);
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
    public List<Card> getCards() throws Exception {
        playerHand = playerService.getPlayerCards();
        List<Card> resultCards = new LinkedList<Card>();
        for(Integer i : playerHand) {
            resultCards.add(normalCardsMap.get(i));
        }
        return resultCards;
    }

    @Override
    public List<List<Integer>> getBoard() throws Exception {

        List<List<Integer>> result = new LinkedList<>();
        BoardGraph myBoard = playerService.getGameBoard().graph;

        for(BoardGraph.Field f : ServicesHelper.getIterableBoard(myBoard)) {
            result.add(new LinkedList<Integer>());
            for(Turtle turtle : f.getTurtles()) {
                result.get(result.size()-1).add(turtle.getColor()+1);
            }
        }
        return result;

    }

    @Override
    public void cardsPlayed() throws RemoteException {

    }

    /*@Override
    public void cardsPlayed() throws RemoteException {
    }*/
}
