package Server.Interfaces;

import Client.Interfaces.LoginClient;

import java.rmi.Remote;

/**
 * Created by michaziobro on 26.05.2014.
 */
public interface GameEntry extends Remote {
    public void newSelector(String name, LoginClient login) throws Exception;
}
