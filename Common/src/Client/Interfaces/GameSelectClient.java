package Client.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface GameSelectClient extends Remote {
    public void update(ThreeStringsGet updateGameInfo) throws RemoteException;
}
