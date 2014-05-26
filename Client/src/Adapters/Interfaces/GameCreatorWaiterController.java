package Adapters.Interfaces;

import Common.Interfaces.Event;

/**
 * Created by larhard on 20.05.14.
 */
public interface GameCreatorWaiterController {
    void registerClosingEvent(Event closingEvent);

    void registerUpdateEvent(Event updateEvent);

    void startAll() throws Exception;

    void cancelAll() throws Exception;

    int getNumberOfPlayers();

    void registerCancelEvent(Event cancelEvent);

    String getGameName();

    int getGameID();

    void initValues() throws Exception;
}
