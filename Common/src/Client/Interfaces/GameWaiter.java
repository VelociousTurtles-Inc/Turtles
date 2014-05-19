package Client.Interfaces;

import Server.Interfaces.PlayerService;

import java.rmi.Remote;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface GameWaiter extends Remote {
    public void update(int newNumberOfPlayers) throws Exception;
    public void cancel() throws Exception;
    public void start(PlayerService player) throws Exception;
}
