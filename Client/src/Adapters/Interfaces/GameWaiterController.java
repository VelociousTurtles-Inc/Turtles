package Adapters.Interfaces;

import Common.Interfaces.Event;

/**
 * Created by larhard on 20.05.14.
 */
public interface GameWaiterController {
    void registerClosingEvent(Event closingEvent);

    void registerCancelEvent(Event cancelEvent);

    void registerUpdate(Event update);

    int getNumberOfPlayers();

    void leave() throws Exception;

    String getGameName() throws Exception;
}
