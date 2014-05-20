package Client.Interfaces;

import Model.SimplestGameInfo;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface ThreeStringsGet extends Remote {

    public void setList(List<SimplestGameInfo> list) throws Exception;

    public List<SimplestGameInfo> getList() throws Exception;
}
