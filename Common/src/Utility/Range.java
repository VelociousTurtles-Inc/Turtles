package Utility;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

/**
 * Created by larhard on 17.05.14.
 */
public class Range implements Iterable<Integer> {
    private final int min;
    private final int max;
    private final int step;

    public Range(int max) {
        this(0, max);
    }

    public Range(int min, int max) {
        this(min, max, 1);
    }

    public Range(int min, int max, int step) {
        this.min = min - step;
        this.max = max - step;
        this.step = step;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int actual = min;

            @Override
            public boolean hasNext() {
                return actual < max;
            }

            @Override
            public Integer next() {
                return actual += step;
            }

            @Override
            public void remove() {
                throw new NotImplementedException();
            }
        };
    }
}
