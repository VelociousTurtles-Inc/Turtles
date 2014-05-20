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

    List<Event> closingEvents;

    public  StandardGameCreatorController(GameDispenser myDispenser) {
        closingEvents = new LinkedList<>();

        this.myDispenser = myDispenser;
        Client.scenario.invoke(GameCreatorController.class, this);
    }

    @Override
    public void registerClosingEvent(Event closingEvent) {
       closingEvents.add(closingEvent);
    }

    private void closeIt() {
        for(Event ev : closingEvents) {
            ev.call();
        }
    }

    @Override
    public void create(String name) throws Exception {
        new StandardGameCreatorWaiterController(name, myDispenser);
        closeIt();
    }


}
