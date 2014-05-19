package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameCreatorWaiterController;
import Client.Interfaces.GameWaiterClient;
import Main.Client;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.PlayerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorWaiterController implements GameCreatorWaiterController, GameWaiterClient {
    GameDispenser myDispenser;
    private  int numberOfPlayers;
    private List<Event> myUpdateEvents = new ArrayList<>();
    private List<Event> cancelEvents = new ArrayList<>();
    private String gameName;
    private int gameID;

    public StandardGameCreatorWaiterController(String s, GameDispenser standardGameCreatorController) throws Exception {
        Client.scenario.invoke(GameCreatorWaiterController.class, this);

        this.myDispenser = standardGameCreatorController;
        int id = myDispenser.createNewGame(s, this);
        gameName =  myDispenser.getGameName(id);
        gameID = id;
        update(numberOfPlayers);
    }

    @Override
    public void update(int newNumberOfPlayers) {
        this.numberOfPlayers = newNumberOfPlayers;
        for (Event e : myUpdateEvents) {
            e.call();
        }
    }

    @Override
    public void cancel() throws Exception {
        for (Event e : cancelEvents) {
            e.call();
        }
    }

    @Override
    public void registerUpdateEvent(Event updateEvent) {
        myUpdateEvents.add(updateEvent);
    }

    public void start(PlayerService player) throws Exception {
        SimpleGameAdapter myAdapter = new SimpleGameAdapter();
        myAdapter.start(player);
    }

    @Override
    public void startAll() throws Exception {
        myDispenser.startGame(gameID);
    }

    @Override
    public void cancelAll() throws Exception {
        myDispenser.cancelGame(gameID);
    }

    @Override
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public void registerCancelEvent(Event cancelEvent) {
        cancelEvents.add(cancelEvent);
    }

    @Override
    public String getGameName() {
        return gameName;
    }
}
