package Controllers;

import Common.Interfaces.Event;
import Controllers.Interfaces.GameCreatorWaiterController;
import Client.Interfaces.GameWaiterClient;
import Main.Client;
import Server.Interfaces.PlayerService;
import Server.Interfaces.WaiterService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class StandardGameCreatorWaiterController implements GameCreatorWaiterController, GameWaiterClient {
    final WaiterService myDispenser;
    private int numberOfPlayers;
    private final List<Event> updateEvents = new ArrayList<>();
    private final List<Event> cancelEvents = new ArrayList<>();
    private String gameName;
    private int gameID;
    private final List<Event> closingEvents = new ArrayList<>();

    public StandardGameCreatorWaiterController(String name, WaiterService standardGameCreatorController) throws RemoteException {
        this.myDispenser = standardGameCreatorController;
        int id = myDispenser.createNewGame(name, this);
        gameName =  myDispenser.getGameName();
        gameID = id;

        Client.scenario.invoke(GameCreatorWaiterController.class, this);
        update(numberOfPlayers);
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
        this.numberOfPlayers = newNumberOfPlayers;
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
    public void registerUpdateEvent(Event updateEvent) {
        synchronized (updateEvents) {
            updateEvents.add(updateEvent);
        }
    }

    public void start(PlayerService player) throws RemoteException {
        StandardGameController myAdapter = new StandardGameController();
        myAdapter.start(player);
    }

    @Override
    public void ping() {
    }

    @Override
    public void startAll() throws RemoteException {
        myDispenser.startGame();
    }

    @Override
    public void cancelAll() throws RemoteException {
        myDispenser.cancelGame();
    }

    @Override
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public void registerCancelEvent(Event cancelEvent) {
        synchronized (cancelEvents) {
            cancelEvents.add(cancelEvent);
        }
    }

    @Override
    public String getGameName() {
        return gameName;
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public void initValues() throws RemoteException {
        myDispenser.updateMe();
    }
}
