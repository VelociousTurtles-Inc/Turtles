package Adapters;

<<<<<<< HEAD
import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameController;
import Model.Board.BoardGraph;
import Model.Cards.Card;
import Model.Turtle;
import Services.GameService;
import Views.Standard.Game.StandardGameView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
=======
import Model.Cards.Card;
import Handlers.UpdateHandler;
import ServicesTypes.BoardGraph;
import ServicesTypes.GameService;
import Views.GameView;

import java.util.ArrayList;
import java.util.Collection;
>>>>>>> origin/DangerouslyManyChangesToCheck
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by larhard on 05.05.14.
 */
<<<<<<< HEAD
public class SimpleGameAdapter extends Thread implements GameController {

    private final GameService gameService;
    //private Collection<? extends GameView> gameViews;
    //private final String[] args;

    public SimpleGameAdapter(GameService gameService/*, Collection<? extends GameView> gameViews, GameService gameService, String[] args*/) {
        this.gameService = gameService;
        /*this.gameViews = gameViews;
=======
public class SimpleGameAdapter extends Thread implements GameAdapter {

    private final GameService gameService;
    private Collection<? extends GameView> gameViews;
    private final String[] args;

    public SimpleGameAdapter(Collection<? extends GameView> gameViews, GameService gameService, String[] args) {
        this.gameService = gameService;
        this.gameViews = gameViews;
>>>>>>> origin/DangerouslyManyChangesToCheck
        this.args = args;

        if (gameViews != null) {
            for (GameView view : gameViews) {
                view.init(this, args);
            }
<<<<<<< HEAD
        }*/

        StandardGameView myGameView = new StandardGameView(this);
=======
        }
>>>>>>> origin/DangerouslyManyChangesToCheck
    }

    // TODO online view connecting

<<<<<<< HEAD
    /*@Override
=======
    @Override
>>>>>>> origin/DangerouslyManyChangesToCheck
    public void close() {
        for (GameView view : gameViews) {
            view.close();
        }
        gameViews.clear();
<<<<<<< HEAD
    }*/

    private Collection<Event> updateBoardEvents = new ArrayList<Event>();
    private Collection<Event> updateCardEvents = new ArrayList<Event>();

    /*@Override
    public void addUpdateBoardHandler(Event handler) {
        synchronized (updateBoardEvents) {
            updateBoardEvents.add(handler);
        }
        handler.call();
    }*/

    public void updateBoard() {
        //BoardGraph boardGraph = gameService.getGameBoardGraph();
        for (Event ev : updateBoardEvents) {
            ev.call();
        }
    }

    /*@Override
    public void addUpdatePlayerCardHandler(UpdateHandler<List<? extends Card>> handler) {
        synchronized (updateCardEvents) {
            updateCardEvents.add(handler);
        }
        handler.update(gameService.getPlayerCards());
    }*/

    public void updatePlayerCards() {
        for (Event handler : updateCardEvents) {
            handler.call();
=======
    }

    private Collection<UpdateHandler<BoardGraph>> updateBoardHandlers = new ArrayList<>();
    private Collection<UpdateHandler<List<Integer>>> updateCardHandlers = new ArrayList<>();

    @Override
    public void addUpdateBoardHandler(UpdateHandler<BoardGraph> handler) {
        synchronized (updateBoardHandlers) {
            updateBoardHandlers.add(handler);
        }
        handler.update(gameService.getGameBoardGraph());
    }

    public void updateBoard() {
        ServicesTypes.BoardGraph boardGraph = gameService.getGameBoardGraph();
        for (UpdateHandler<BoardGraph> handler : updateBoardHandlers) {
            handler.update(boardGraph);
        }
    }

    @Override
    public void addUpdatePlayerCardHandler(UpdateHandler<List<Integer>> handler) {
        synchronized (updateCardHandlers) {
            updateCardHandlers.add(handler);
        }
        handler.update(gameService.getPlayerCards());
    }

    public void updatePlayerCards() {
        List<Integer> cards = gameService.getPlayerCards();
        for (UpdateHandler<List<Integer>> handler : updateCardHandlers) {
            handler.update(cards);
>>>>>>> origin/DangerouslyManyChangesToCheck
        }
    }

    private Lock playCardLock = new ReentrantLock();

    @Override
<<<<<<< HEAD
    public void playCard(int card) {
        playCardLock.lock();
        List<Card> myCards = gameService.getPlayerCards();
        gameService.playCard(myCards.get(card));
        System.out.println("Card " + card + " played");

        this.updateBoard();
        this.updatePlayerCards();

        playCardLock.unlock();
    }

    @Override
    public void surrender() {
        System.out.println("I surrended, it does nothing.");
    }

    @Override
    public void registerUpdateBoardEvent(Event updateBoardEvent) {
        synchronized (updateBoardEvents) {
            updateBoardEvents.add(updateBoardEvent);
        }
    }

    @Override
    public void registerUpdateCardsEvent(Event updateCardEvent) {
        synchronized (updateCardEvents) {
            updateCardEvents.add(updateCardEvent);
        }
    }

    @Override
    public List<Card> getCards() {
        return gameService.getPlayerCards();
    }

    @Override
    public List<List<Integer>> getBoard() {
        BoardGraph myBoard = gameService.getGameBoardGraph();

        List<List<Integer>> resultList = new LinkedList<List<Integer>>();

        for(BoardGraph.Field actualField : myBoard) {
            resultList.add(new LinkedList<Integer>());
            List<Turtle> trt = actualField.turtles;
            for(Turtle actTurtle : trt) {
                resultList.get(resultList.size()-1).add(actTurtle.getColor());
            }
        }

        return resultList;
    }
=======
    public void playCard(Integer cardID) {
        playCardLock.lock();
        gameService.playCard(cardID);
        System.out.println("Card " + cardID + " played");
        playCardLock.unlock();
    }
>>>>>>> origin/DangerouslyManyChangesToCheck
}
