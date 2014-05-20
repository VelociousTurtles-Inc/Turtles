package Adapters;


import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameController;
import Client.Interfaces.GameClient;
import Main.Client;
import Model.Board.BoardGraph;
import Model.Cards.Card;
import Model.Turtles.Turtle;
import Utility.DebugWriter;
import Server.Interfaces.PlayerService;

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

    List<Event> unlockingEvents;
    List<Event> lockingEvents;

    private boolean locked;

    List<Integer> playerHand;

    public SimpleGameAdapter() throws Exception {
        normalCardsMap = new HashMap<>();

        boardUpdates = new LinkedList<>();
        cardsUpdates = new LinkedList<>();

        lockingEvents = new LinkedList<>();
        unlockingEvents = new LinkedList<>();

        locked = true;

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

//    public boolean checkForWinner() throws Exception {
//        BoardGraph myBoardGraph = gameService.getGameBoardGraph();
//
//        if(myBoardGraph.end.getTurtles().size() != 0) {
//            Turtle temp = myBoardGraph.end.getTurtles().get(myBoardGraph.end.getTurtles().size() - 1);
//            System.out.println("The " + Colors.asString(temp.getColor() + 1) + " turtle has won the game.");
//            return true;
//        } else {
//            return false;
//        }
//
//    }

    @Override
    public void start(PlayerService myService) throws Exception {
        playerService = myService;
        normalCardsMap = myService.getCardsMap();
        myService.setClient(this);
        Client.scenario.invoke(GameController.class, this);
        //myService.lockMeOrNot();
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

    /*
        Changed on 18 may 2014 by Szymon to match the new way the BoardGraph is stored
        Only works for the SimpleBoard, have to be very careful with it.
        Dirty, needs changing as soon as it will be clear how are we going to implement
        the Graph
     */
    @Override
    public List<List<Integer>> getBoard() throws Exception {

        List<List<Integer>> result = new LinkedList<>();
        BoardGraph myBoardGraph = playerService.getGameBoard().graph;

        result.add(new LinkedList<Integer>());
        for(BoardGraph.Field f : myBoardGraph.starts)
            for(Turtle t : f.getTurtles())
                result.get(0).add(t.getColor()+1);

        BoardGraph.Field temp = myBoardGraph.starts.get(0);

        while(temp.getSuccessors().size() != 0) {
            LinkedList<Integer> A = new LinkedList<>();
            temp = temp.getSuccessors().get(0);
            for(Turtle t : temp.getTurtles())
                A.add(t.getColor()+1);
            result.add(A);
        }

        return result;
    }

    @Override
    public void lock() throws RemoteException {
        locked = true;
        System.out.println("Locked");
        for(Event ev : lockingEvents) {
            ev.call();
        }
    }

    @Override
    public void unlock() throws RemoteException  {
        locked = false;
        System.out.println("Unlocked");
        for(Event ev : unlockingEvents) {
            ev.call();
        }
    }

    @Override
    public void registerLockingEvent(Event lockingEvent) {
        lockingEvents.add(lockingEvent);
    }

    @Override
    public void registerUnlockingEvent(Event unlockingEvent) {
        unlockingEvents.add(unlockingEvent);
    }

    @Override
    public void cardsPlayed() throws RemoteException {

    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    /*@Override
    public void cardsPlayed() throws RemoteException {
    }*/
}
