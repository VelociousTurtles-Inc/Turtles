package Colors;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class ColorsTest extends TestCase {
    Set<Colors> allColors;
    Set<Colors> a;

    @Override
    public void setUp() {
        allColors = new HashSet<>();
        for (Colors i : Colors.values()) {
            allColors.add(i);
        }
    }

    public void testMapsFilling() {
        a = new HashSet<>();
        for (Colors i : Colors.getAllColors()) {
            a.add(i);
        }
        assertEquals("All Colors", allColors, a);

        a = new HashSet<>();
        for (String i : Colors.getAllStrings()) {
            a.add(Colors.asColor(i));
        }
        assertEquals("Strings Colors", allColors, a);

        a = new HashSet<>();
        for (Integer i : Colors.getAllIntegers()) {
            a.add(Colors.asColor(i));
        }
        assertEquals("Integer Colors", allColors, a);
    }
}