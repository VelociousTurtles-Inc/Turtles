package Adapters.Interfaces;

/**
 * Created by larhard on 20.05.14.
 */
public interface GameCreatorWaiterController {
    void registerUpdateEvent(Event updateEvent);

    void startAll() throws Exception;

    void cancelAll() throws Exception;

    int getNumberOfPlayers();

    void registerCancelEvent(Event cancelEvent);

    String getGameName();
}
