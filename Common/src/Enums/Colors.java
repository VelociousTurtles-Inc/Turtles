package Enums;

import java.util.*;

/**
 * Created by larhard on 17.05.14.
 */
public enum Colors {
    NULL, RED, GREEN, YELLOW, BLUE, ORANGE;

    private static Map<Object, Colors> objectColorsMap = new HashMap<>();
    private static Map<Colors, Integer> colorsIntegerMap = new HashMap<>();
    private static Map<Colors, String> colorsStringMap = new HashMap<>();
    private static List<Integer> realIntegers = new ArrayList<>();
    private static List<Integer> allIntegers = new ArrayList<>();
    private static List<String> realStrings = new ArrayList<>();
    private static List<String> allStrings = new ArrayList<>();
    private static List<Colors> realColors = new ArrayList<>();
    private static List<Colors> allColors = new ArrayList<>();
    private final static Random random = new Random();

    static {
        colorsIntegerMap.put(NULL, 0);
        colorsIntegerMap.put(BLUE, 1);
        colorsIntegerMap.put(GREEN, 2);
        colorsIntegerMap.put(YELLOW, 3);
        colorsIntegerMap.put(ORANGE, 4);
        colorsIntegerMap.put(RED, 5);

        colorsStringMap.put(NULL, "Null");
        colorsStringMap.put(BLUE, "Blue");
        colorsStringMap.put(GREEN, "Green");
        colorsStringMap.put(ORANGE, "Orange");
        colorsStringMap.put(YELLOW, "Yellow");
        colorsStringMap.put(RED, "Red");

        for (Colors i : colorsIntegerMap.keySet()) {
            objectColorsMap.put(i, i);
            objectColorsMap.put(colorsIntegerMap.get(i), i);
            if (NULL != i) {
                realIntegers.add(colorsIntegerMap.get(i));
                realColors.add(i);
            }
            allIntegers.add(colorsIntegerMap.get(i));
            allColors.add(i);
        }

        for (Colors i : colorsStringMap.keySet()) {
            objectColorsMap.put(colorsStringMap.get(i), i);
            if (NULL != i) {
                realStrings.add(colorsStringMap.get(i));
            }
            allStrings.add(colorsStringMap.get(i));
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

    public static List<Integer> getRealIntegers() {
        return realIntegers;
    }

    public static List<Colors> getRealColors() {
        return realColors;
    }

    public static List<Colors> getAllColors() {
        return allColors;
    }

    public static List<String> getRealStrings() {
        return realStrings;
    }

    public static List<Integer> getAllIntegers() {
        return allIntegers;
    }

    public static List<String> getAllStrings() {
        return allStrings;
    }

    public static Colors getRandomColorFromReal() {
        synchronized (random) {
            return getRealColors().get(random.nextInt(getRealIntegers().size()));
        }
    }

    public static Colors getRandomColorFromAll() {
        synchronized (random) {
            return getAllColors().get(random.nextInt(getAllColors().size()));
        }
    }

    @Override
    public String toString() {
        return asString(this);
    }

    public Integer toInteger() {
        return asInteger(this);
    }
}
