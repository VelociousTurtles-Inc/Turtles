package Adapters;

import Client.Interfaces.ClientLogin;
import Common.Interfaces.Event;
import Main.Client;
import Server.Interfaces.GameEntry;
import Server.Interfaces.WaiterService;
import Views.Standard.Login.LoginView;
import org.cojen.dirmi.Environment;
import org.cojen.dirmi.Session;

import java.util.LinkedList;
import java.util.List;

public class LoginController implements ClientLogin {

    GameEntry gameEntry;

    LoginView loginView;

    LoginController() throws Exception {
        loginView = new LoginView(this);
        loginView.start();
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
