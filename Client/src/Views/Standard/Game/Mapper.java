package Views.Standard.Game;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaziobro on 13.05.2014.
 */
public class Mapper {
    static public Map<Integer, String> getColorMap() {
        Map<Integer, String> result = new HashMap<Integer, String>();

        result.put(1, "Red");
        result.put(1, "Green");
        result.put(1, "Yellow");
        result.put(1, "Blue");
        result.put(1, "Orange");

        return result;
    }
}
