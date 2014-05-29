package Controllers.Interfaces;

import Common.Interfaces.Event;

public interface LoginController {
    void registerCloseEvent(Event event);

    void close();

    boolean submit(String name);

    boolean submit(String name, String host, int port);
}
