package Colors;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by larhard on 17.05.14.
 */
public enum Colors {
    NULL, RED, GREEN, YELLOW, BLUE, ORANGE;

    private static Map<Object, Colors> objectColorsMap = new HashMap<>();
    private static Map<Colors, Integer> colorsIntegerMap = new HashMap<>();
    private static Map<Colors, String> colorsStringMap = new HashMap<>();

    static {
        colorsIntegerMap.put(NULL, 0);
        colorsIntegerMap.put(BLUE, 1);
        colorsIntegerMap.put(GREEN, 2);
        colorsIntegerMap.put(ORANGE, 3);
        colorsIntegerMap.put(YELLOW, 4);
        colorsIntegerMap.put(RED, 5);

        colorsStringMap.put(NULL, "Null");
        colorsStringMap.put(BLUE, "Blue");
        colorsStringMap.put(GREEN, "Green");
        colorsStringMap.put(ORANGE, "Orange");
        colorsStringMap.put(YELLOW, "Yellow");
        colorsStringMap.put(RED, "Red");

        for (Colors i : colorsIntegerMap.keySet()) {
            objectColorsMap.put(i, i);
        }
        for (Colors i : colorsStringMap.keySet()) {
            objectColorsMap.put(i, i);
        }

        for (Colors i : colorsIntegerMap.keySet()) {
            objectColorsMap.put(colorsIntegerMap.get(i), i);
        }
        for (Colors i : colorsStringMap.keySet()) {
            objectColorsMap.put(colorsStringMap.get(i), i);
        }
    }

    public static Integer asInteger(Object color) {
        return colorsIntegerMap.get(objectColorsMap.get(color));
    }

    public static String asString(Object color) {
        return colorsStringMap.get(objectColorsMap.get(color));
    }

    public static Colors asColor(Object color) {
        return objectColorsMap.get(color);
    }

    public static Map<Object, Colors> getObjectColorsMap() {
        return objectColorsMap;
    }
    public static Map<Colors, Integer> getColorsIntegerMap() {
        return colorsIntegerMap;
    }
    public static Map<Colors, String> getColorsStringMap() {
        return colorsStringMap;
    }
}
