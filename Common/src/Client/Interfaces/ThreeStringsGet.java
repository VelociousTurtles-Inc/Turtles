package Client.Interfaces;

import Model.GameInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ThreeStringsGet extends Remote {

    public void setList(List<GameInfo> list) throws RemoteException;

    public List<GameInfo> getList() throws RemoteException;
}
