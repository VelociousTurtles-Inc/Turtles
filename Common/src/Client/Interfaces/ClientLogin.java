package Client.Interfaces;

import Server.Interfaces.WaiterService;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by michaziobro on 26.05.2014.
 */
public interface ClientLogin extends Remote {
    //public void toGameSelect() throws RemoteException;

    void toGameSelect(WaiterService waiter) throws Exception;
}
