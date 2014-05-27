package Adapters;

import Common.Interfaces.Event;
import Adapters.Interfaces.GameCreatorController;
import Main.Client;
import Server.Interfaces.WaiterService;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class StandardGameCreatorController implements GameCreatorController {
    WaiterService myDispenser;

    private final List<Event> closingEvents = new LinkedList<>();

    public StandardGameCreatorController(WaiterService myDispenser) {

        this.myDispenser = myDispenser;
        Client.scenario.invoke(GameCreatorController.class, this);
    }

    @Override
    public void registerClosingEvent(Event closingEvent) {
        synchronized (closingEvents) {
            closingEvents.add(closingEvent);
        }
    }

    private void closeIt() {
        synchronized (closingEvents) {
            for (Event ev : closingEvents) {
                ev.call();
            }
        }
    }

    @Override
    public void create(String name) throws RemoteException {
        new StandardGameCreatorWaiterController(name, myDispenser);
        closeIt();
    }

}
