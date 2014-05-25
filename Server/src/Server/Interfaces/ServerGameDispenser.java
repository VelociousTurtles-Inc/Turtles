package Server.Interfaces;

import Events.Event;

import java.util.Collection;

/**
 * Created by larhard on 25.05.14.
 */
public interface ServerGameDispenser {
    void registerCloseEvent(Event event);
    void close();

    void update() throws Exception;

    Collection<GameManager> getGameManagers();
    void cancelGame(int gameID);
}
