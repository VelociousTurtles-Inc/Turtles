package Client.Interfaces;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface ThreeStringsGet extends Remote {

    public void setList(List<SimpliestGameInfo> list) throws Exception;

    public List<SimpliestGameInfo> getList() throws Exception;
}
