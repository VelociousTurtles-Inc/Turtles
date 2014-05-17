package Adapters.Interfaces;

import Model.SimpleGameInfo;

import java.util.List;

/**
 * Created by michaziobro on 16.05.2014.
 */
public interface GameSelectController {
    public void join();
    public void cancel();
    public void create();
    public void setClosingEvent(Event myClosingEvent);
    public void setUpdateEvent(Event updateEvent);
    public void update(List<SimpleGameInfo> updateGameInfo);
}
