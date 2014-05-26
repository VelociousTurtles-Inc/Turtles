package Adapters;

import Client.Interfaces.ClientLogin;
import Events.Event;
import Main.Client;
import Server.Interfaces.GameDispenser;
import Server.Interfaces.GameEntry;
import Server.Interfaces.WaiterService;
import Views.Standard.Login.LoginView;
import org.cojen.dirmi.Environment;
import org.cojen.dirmi.Session;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michaziobro on 26.05.2014.
 */
public class LoginController implements ClientLogin {

    GameEntry gameEntry;

    LoginView myView;

    LoginController() throws Exception {
        myView = new LoginView(this);
        myView.start();
    }

    List<Event> closeEvents = new LinkedList<>();

    public void registerCloseEvent(Event event) {
        closeEvents.add(event);
    }
    public void close() {
        for(Event ev : closeEvents) {
            ev.call();
        }
    }
    public void submit(String name) throws Exception {
        System.out.println("Submit with name: " + name);
        Environment environment = new Environment();
        Session session = environment.newSessionConnector(Client.getHost(), Client.getPort()).connect();

        gameEntry = (GameEntry) session.receive();

        gameEntry.newSelector(name, this);
    }


    @Override
    public void toGameSelect(WaiterService waiter) throws Exception {
        new StandardGameSelectController(waiter);
        close();
    }
}
