package Client.Interfaces;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by michaziobro on 17.05.2014.
 */
public interface GameSelecter extends Remote {
    public void update(ThreeStringsGet updateGameInfo) throws Exception;
}
