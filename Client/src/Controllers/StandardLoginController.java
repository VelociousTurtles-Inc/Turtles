package Controllers;

import Client.Interfaces.LoginClient;
import Common.Interfaces.Event;
import Controllers.Interfaces.LoginController;
import Main.Client;
import Server.Interfaces.GameEntry;
import Server.Interfaces.WaiterService;
import Utility.DebugWriter;
import Views.Standard.Login.LoginView;
import org.cojen.dirmi.Environment;
import org.cojen.dirmi.Session;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StandardLoginController implements LoginClient, LoginController {

    GameEntry gameEntry;

    LoginView loginView;
    StandardLoginController() throws RemoteException {
        Client.scenario.invoke(LoginController.class, this);
    }

    final List<Event> closeEvents = new LinkedList<>();

    @Override
    public void registerCloseEvent(Event event) {
        closeEvents.add(event);
    }
    @Override
    public void close() {
        for(Event ev : closeEvents) {
            ev.call();
        }
    }
    @Override
    public boolean submit(String name) {
        return submit(name, Client.getHost(), Client.getPort());
    }

    @Override
    public boolean submit(String name, String host, int port) {
        assert DebugWriter.write("Submit with name: " + name);
        Environment environment = new Environment();
        Session session = null;
        try {
            session = environment.newSessionConnector(host, port).connect(10, TimeUnit.SECONDS);

            gameEntry = (GameEntry) session.receive();

            gameEntry.newSelector(name, this);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    public void toGameSelect(WaiterService waiter) throws RemoteException {
        new StandardGameSelectController(waiter);
        close();
    }
}
