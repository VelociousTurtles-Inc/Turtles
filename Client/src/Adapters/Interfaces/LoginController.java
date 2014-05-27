package Adapters.Interfaces;


import Common.Interfaces.Event;

import java.io.IOException;
import java.rmi.RemoteException;

public interface LoginController {
    void registerCloseEvent(Event event);

    void close();

    void submit(String name) throws IOException;
}
