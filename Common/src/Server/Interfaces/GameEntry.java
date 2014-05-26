package Server.Interfaces;

import Client.Interfaces.ClientLogin;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by michaziobro on 26.05.2014.
 */
public interface GameEntry extends Remote {
    public void newSelector(String name, ClientLogin login) throws Exception;
}
