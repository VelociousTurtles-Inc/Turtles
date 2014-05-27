package Client.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameSelectClient extends Remote {
    public void update(ThreeStringsGet updateGameInfo) throws RemoteException;
}
