package Utils;

import java.util.Iterator;

/**
 * Created by larhard on 19.05.14.
 */
public class NullIterable <T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }

            @Override
            public void remove() {

            }
        };
    }
}
