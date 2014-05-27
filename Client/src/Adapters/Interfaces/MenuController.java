package Adapters.Interfaces;

import java.rmi.RemoteException;

public interface MenuController {
    void startGame() throws RemoteException;
    void exitProgram();
}
