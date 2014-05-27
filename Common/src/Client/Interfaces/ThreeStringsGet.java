package Client.Interfaces;

import Model.GameInfo;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface ThreeStringsGet extends Remote {

    public void setList(List<GameInfo> list) throws Exception;

    public List<GameInfo> getList() throws Exception;
}
