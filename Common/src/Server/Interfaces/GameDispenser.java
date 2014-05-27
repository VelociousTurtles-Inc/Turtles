package Server.Interfaces;

import java.rmi.RemoteException;

public interface GameDispenser {
    // TODO authenticate

    void leaveGame(int gameID, WaiterService mySel) throws RemoteException;
    void cancelGame(int gameID);

    GameManager connectToGame(int id, WaiterService mySel) throws RemoteException;

    Integer createNewGame(String name, WaiterService mySel) throws RemoteException;

    public String getGameName(int gameID) throws RemoteException;

    void updateMe() throws RemoteException;

    void startGame(int gameID) throws RemoteException;
}
