package Views.Standard.Game;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaziobro on 13.05.2014.
 */
public class Mapper {
    static public Map<Integer, String> getColorMap() {
        Map<Integer, String> result = new HashMap<Integer, String>();

        result.put(1, "Blue");
        result.put(2, "Green");
        result.put(3, "Yellow");
        result.put(4, "Orange");
        result.put(5, "Red");

        return result;
    }
}
