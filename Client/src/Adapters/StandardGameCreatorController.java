package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameCreatorController;
import Main.Client;
import Server.Interfaces.GameDispenser;
import Views.Standard.GameCreation.GameCreator.GameCreatorView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public class StandardGameCreatorController implements GameCreatorController {
    GameDispenser myDispenser;

    private final List<Event> closingEvents = new LinkedList<>();

    public StandardGameCreatorController(GameDispenser myDispenser) {

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
    public void create(String name) throws Exception {
        new StandardGameCreatorWaiterController(name, myDispenser);
        closeIt();
    }


}
