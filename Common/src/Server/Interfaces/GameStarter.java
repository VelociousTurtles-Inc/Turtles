package Server.Interfaces;

import Client.Interfaces.GameWaiter;
import Client.Interfaces.SimpliestGameInfo;

import java.rmi.Remote;


/**
 * Created by larhard on 15.05.14.
 */
public interface GameStarter extends Remote {
    public void startGame();
    public boolean isStarted();
    public boolean isFull();
    public SimpliestGameInfo getGameInfo();
    void addPlayer(GameWaiter newWaiter);

    void removePlayer(GameWaiter oldWaiter);
    void setId(int id);
    public void update() throws Exception;
}
