package Server.Interfaces;

import Model.SimpleGameInfo;

import java.rmi.Remote;


/**
 * Created by larhard on 15.05.14.
 */
public interface GameStarter extends Remote {
    public void addPlayer();
    public void removePlayer(int playerID);
    public void startGame();
    public boolean isStarted();
    public boolean isFull();
    public SimpleGameInfo getGameInfo();
}
