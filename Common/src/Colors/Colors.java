package Colors;

/**
 * Created by larhard on 17.05.14.
 */
public enum Colors {
    NULL, RED, GREEN, YELLOW, BLUE, ORANGE;

    public static Integer asInteger(Object color) {
        switch (asColor(color)) {
            case BLUE:
                return 1;
            case GREEN:
                return 2;
            case ORANGE:
                return 3;
            case YELLOW:
                return 4;
            case RED:
                return 5;
            case NULL:
                return null;
        }
        assert false;
        return null;
    }

    public static String asString(Colors color) {
        switch (asColor(color)) {
            case RED:
                return "Red";
            case GREEN:
                return "Green";
            case YELLOW:
                return "Yellow";
            case BLUE:
                return "Blue";
            case ORANGE:
                return "Orange";
            case NULL:
                return "Null";
        }
        assert false;
        return null;
    }

    public static Colors asColor(Object color) {
        if (color instanceof Integer) {
            return asColor((Integer) color);
        } else if (color instanceof String) {
            return asColor((String) color);
        } else if (color instanceof Colors) {
            return (Colors) color;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Colors asColor(String color) {
        switch (color) {
            case "Red":
                return RED;
            case "Green":
                return GREEN;
            case "Yellow":
                return YELLOW;
            case "Blue":
                return BLUE;
            case "Orange":
                return ORANGE;
            case "Null":
                return NULL;
        }
        assert false : "Unsupported color String: " + color;
        return null;
    }

    public static Colors asColor(Integer color) {
        if (color == null) {
            return NULL;
        }
        switch (color) {
            case 1:
                return BLUE;
            case 2:
                return GREEN;
            case 3:
                return ORANGE;
            case 4:
                return YELLOW;
            case 5:
                return RED;
        }
        assert false : "Unsupported color Integer: " + color;
        return null;
    }
}
