package Client.Interfaces;

import Server.Interfaces.PlayerService;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameWaiterClient extends Remote {
    void closeMe() throws RemoteException;

    public void update(int newNumberOfPlayers) throws RemoteException;
    public void cancel() throws RemoteException;
    public void start(PlayerService player) throws RemoteException;

    void ping() throws RemoteException;
}
