package Utility;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RangeTest extends TestCase {
    public void testRange() {
        Set<Integer> result = new HashSet<>();
        for (int i : new Range(6)) {
            result.add(i);
        }
        assertEquals(new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5)), result);

        result = new HashSet<>();
        for (int i : new Range(1, 6)) {
            result.add(i);
        }
        assertEquals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)), result);

        result = new HashSet<>();
        for (int i : new Range(1, 7, 2)) {
            result.add(i);
        }
        assertEquals(new HashSet<>(Arrays.asList(1, 3, 5)), result);
    }
}