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
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by larhard on 05.05.14.
 */

public class StandardGameController extends Thread implements GameController, GameClient {

    private Map<Integer, Card> normalCardsMap;
    private PlayerService playerService;

    private final List<Event> boardUpdateEvents = new LinkedList<>();
    private final List<Event> cardsUpdateEvents = new LinkedList<>();

    private final List<Event> unlockingEvents = new LinkedList<>();
    private final List<Event> lockingEvents = new LinkedList<>();

    private AtomicBoolean locked = new AtomicBoolean();

    List<Integer> playerHand;

    public StandardGameController() throws Exception {
        normalCardsMap = new HashMap<>();

        locked.set(true);

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
        synchronized (cardsUpdateEvents) {
            for (Event up : cardsUpdateEvents) {
                up.call();
            }
        }
    }

    @Override
    public void updateBoards() {
        synchronized (boardUpdateEvents) {
            for (Event up : boardUpdateEvents) {
                up.call();
            }
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
        synchronized (boardUpdateEvents) {
            assert DebugWriter.write("Registering new Update Board Event");
            boardUpdateEvents.add(updateBoardEvent);
        }
    }

    @Override
    public void registerUpdateCardsEvent(Event updateCardEvent) {
        synchronized (cardsUpdateEvents) {
            assert DebugWriter.write("Registering new Update Cards Event");
            cardsUpdateEvents.add(updateCardEvent);
            updateCardEvent.call();
        }
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



        for(BoardGraph.Field f : myBoardGraph.starts) {
            LinkedList<Integer> A = new LinkedList<>();
            for (Turtle t : f.getTurtles())
                A.add(t.getColor());
            result.add(A);
        }

        BoardGraph.Field temp = myBoardGraph.starts.get(0);

        while(temp.getSuccessors().size() != 0) {
            LinkedList<Integer> A = new LinkedList<>();
            temp = temp.getSuccessors().get(0);
            for(Turtle t : temp.getTurtles())
                A.add(t.getColor());
            result.add(A);
        }

        return result;
    }

    @Override
    public void lock() throws RemoteException {
        locked.set(true);
        System.out.println("Locked");
        for(Event ev : lockingEvents) {
            ev.call();
        }
    }

    @Override
    public void unlock() throws RemoteException  {
        locked.set(false);
        System.out.println("Unlocked");
        for(Event ev : unlockingEvents) {
            ev.call();
        }
    }

    @Override
    public void registerLockingEvent(Event lockingEvent) {
        synchronized (lockingEvents) {
            lockingEvents.add(lockingEvent);
        }
    }

    @Override
    public void registerUnlockingEvent(Event unlockingEvent) {
        synchronized (unlockingEvents) {
            unlockingEvents.add(unlockingEvent);
        }
    }

    @Override
    public void cardsPlayed() throws RemoteException {

    }

    @Override
    public boolean isLocked() {
        return locked.get();
    }

    /*@Override
    public void cardsPlayed() throws RemoteException {
    }*/
}
