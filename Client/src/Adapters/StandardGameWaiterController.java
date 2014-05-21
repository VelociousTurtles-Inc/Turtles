package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameWaiterController;
import Client.Interfaces.GameWaiterClient;
import Main.Client;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.PlayerService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameWaiterController implements GameWaiterController, GameWaiterClient {
    private int gameID;
    private int myNumberOfPlayers;
    private GameDispenser myGameDispenser;
    private final List<Event> updateEvents = new LinkedList<>();
    private String gameName;
    private final List<Event> cancelEvents = new LinkedList<>();
    private final List<Event> closingEvents = new LinkedList<>();

    public StandardGameWaiterController(int myID, GameDispenser myGameDispenser) throws Exception {
        gameID = myID;
        this.myGameDispenser = myGameDispenser;
        gameName = myGameDispenser.getGameName(gameID);
        myGameDispenser.connectToGame(myID, this);
        Client.scenario.invoke(GameWaiterController.class, this);
        update(myNumberOfPlayers);
    }

    @Override
    public void registerClosingEvent(Event closingEvent) {
        synchronized (closingEvents) {
            closingEvents.add(closingEvent);
        }
    }

    @Override
    public void closeMe() {
        synchronized (closingEvents) {
            for (Event ev : closingEvents) {
                ev.call();
            }
        }
    }

    @Override
    public void update(int newNumberOfPlayers) {
        myNumberOfPlayers = newNumberOfPlayers;
        synchronized (updateEvents) {
            for (Event e : updateEvents) {
                e.call();
            }
        }
    }

    @Override
    public void cancel() throws Exception {
        synchronized (cancelEvents) {
            for (Event e : cancelEvents) {
                e.call();
            }
        }
    }

    @Override
    public void start(PlayerService player) throws Exception {
        StandardGameController myAdapter = new StandardGameController();
        myAdapter.start(player);
    }

    @Override
    public void registerCancelEvent(Event cancelEvent) {
        synchronized (cancelEvents) {
            cancelEvents.add(cancelEvent);
        }
    }

    @Override
    public void registerUpdate(Event update) {
        synchronized (updateEvents) {
            updateEvents.add(update);
        }
    }

    @Override
    public int getNumberOfPlayers() {
        return myNumberOfPlayers;
    }

    @Override
    public void leave() throws Exception {
        myGameDispenser.leaveGame(gameID,this);
    }

    @Override
    public String getGameName() throws Exception {
        return gameName;
    }
}
