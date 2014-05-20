package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameWaiterController;
import Client.Interfaces.GameWaiterClient;
import Main.Client;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.PlayerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameWaiterController implements GameWaiterController, GameWaiterClient {
    private int gameID;
    private int myNumberOfPlayers;
    private GameDispenser myGameDispenser;
    private List<Event> updateEvents = new ArrayList<>();
    private String gameName;
    private List<Event> cancelEvents = new ArrayList<>();

    public StandardGameWaiterController(int myID, GameDispenser myGameDispenser) throws Exception {
        gameID = myID;
        this.myGameDispenser = myGameDispenser;
        gameName = myGameDispenser.getGameName(gameID);
        myGameDispenser.connectToGame(myID, this);
        Client.scenario.invoke(GameWaiterController.class, this);
        update(myNumberOfPlayers);
    }

    @Override
    public void update(int newNumberOfPlayers) {
        myNumberOfPlayers = newNumberOfPlayers;
        for (Event e : updateEvents) {
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
    public void start(PlayerService player) throws Exception {
        SimpleGameAdapter myAdapter = new SimpleGameAdapter();
        myAdapter.start(player);
    }

    @Override
    public void registerCancelEvent(Event cancelEvent) {
        cancelEvents.add(cancelEvent);
    }

    @Override
    public void registerUpdate(Event update) {
        updateEvents.add(update);
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
