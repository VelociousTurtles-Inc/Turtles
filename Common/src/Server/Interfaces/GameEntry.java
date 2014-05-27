package Server.Interfaces;

import Client.Interfaces.LoginClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameEntry extends Remote {
    public void newSelector(String name, LoginClient login) throws RemoteException;
}
