package Adapters.Interfaces;

import Events.Event;
import Model.SimplestGameInfo;

import java.util.List;

/**
 * Created by michaziobro on 16.05.2014.
 */
public interface GameSelectController {
    public void join(int myID) throws Exception;
    public void cancel();
    public void create();
    public void registerClosingEvent(Event myClosingEvent);
    public void registerUpdateEvent(Event updateEvent);

    List<SimplestGameInfo> getUpdateList();

    void initValues() throws Exception;
}
