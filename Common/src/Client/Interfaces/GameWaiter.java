package Client.Interfaces;

import Model.SimpleGameInfo;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface GameWaiter extends Remote {
    public void update(int newNumberOfPlayers) throws Exception;
}
