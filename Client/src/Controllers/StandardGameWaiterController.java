package Controllers;

import Client.Interfaces.GameWaiterClient;
import Common.Interfaces.Event;
import Controllers.Interfaces.GameWaiterController;
import Main.Client;
import Server.Interfaces.PlayerService;
import Server.Interfaces.WaiterService;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class StandardGameWaiterController implements GameWaiterController, GameWaiterClient {
    private final List<Event> updateEvents = new LinkedList<>();
    private final List<Event> cancelEvents = new LinkedList<>();
    private final List<Event> closingEvents = new LinkedList<>();
    private final int gameID;
    private int myNumberOfPlayers;
    private final WaiterService myGameDispenser;
    private String gameName;

    public StandardGameWaiterController(int myID, WaiterService myGameDispenser) throws RemoteException {
        gameID = myID;
        this.myGameDispenser = myGameDispenser;

        myGameDispenser.connectToGame(myID, this);
        Client.scenario.invoke(GameWaiterController.class, this);
        gameName = myGameDispenser.getGameName();
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
    public void cancel() throws RemoteException {
        synchronized (cancelEvents) {
            for (Event e : cancelEvents) {
                e.call();
            }
        }
    }

    @Override
    public void start(PlayerService player) throws RemoteException {
        StandardGameController myAdapter = new StandardGameController();
        myAdapter.start(player);
    }

    @Override
    public void ping() throws RemoteException {

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
    public void leave() throws RemoteException {
        myGameDispenser.leaveGame();
    }

    @Override
    public String getGameName() {
        return gameName;
    }

}
