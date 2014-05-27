package Controllers.Interfaces;

import Common.Interfaces.Event;

import java.io.IOException;

public interface LoginController {
    void registerCloseEvent(Event event);

    void close();

    void submit(String name) throws IOException;
}
