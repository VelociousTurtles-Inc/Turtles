package Adapters.Interfaces;


import Common.Interfaces.Event;

/**
 * Created by larhard on 27.05.14.
 */
public interface LoginController {
    void registerCloseEvent(Event event);

    void close();

    void submit(String name) throws Exception;
}
