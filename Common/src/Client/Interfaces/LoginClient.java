package Client.Interfaces;

import Server.Interfaces.WaiterService;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginClient extends Remote {
    //public void toGameSelect() throws RemoteException;

    void toGameSelect(WaiterService waiter) throws RemoteException;
}
