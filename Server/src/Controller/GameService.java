package Controller;

import Model.GameInfo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Maciej on 2014-04-24.
 */
@WebService
public class GameService {
    @WebMethod
    public List<GameInfo> GameList()
    {
        GameInfo[] result = new GameInfo[]{new GameInfo(),new GameInfo()};
        result[0].id = 1;
        result[1].id = 7;
        return Arrays.asList(result);
    }
}
