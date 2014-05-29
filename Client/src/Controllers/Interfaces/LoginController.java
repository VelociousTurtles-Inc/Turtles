package Controllers.Interfaces;

import Common.Interfaces.Event;

import java.io.IOException;

public interface LoginController {
    void registerCloseEvent(Event event);

    void close();

    boolean submit(String name);
}
