package Adapters.Interfaces;

import Events.Event;

/**
 * Created by larhard on 20.05.14.
 */
public interface GameCreatorController {

    void registerClosingEvent(Event closingEvent);

    void create(String s) throws Exception;
}
