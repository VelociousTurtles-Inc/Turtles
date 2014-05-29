package Utility;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Map from Controller class to list of it Views
 */
public class Scenario {
    // TODO removing
    private final Map<Class<?>, Entry> scenarioMap = new HashMap<>();

    private class Entry {
        final Set<Class<?>> views = new HashSet<>();

        void add(Class<?> view) {
            views.add(view);
        }
    }

    /**
     * append View to the list for specific adapter
     */
    public void add(Class<?> adapter, Class<?> view) {
        synchronized (scenarioMap) {
            if (!scenarioMap.containsKey(adapter)) {
                scenarioMap.put(adapter, new Entry());
            }
            scenarioMap.get(adapter).add(view);
        }
    }

    /**
     * @return list of Views for given adapter
     */
    public Iterable<Class<?>> get(Class<?> adapter) {
        synchronized (scenarioMap) {
            if (scenarioMap.containsKey(adapter)) {
                return scenarioMap.get(adapter).views;
            } else {
                return new NullIterable<Class<?>>();
            }
        }
    }

    /**
     * invokes all views for given adapterClass with one argument type adapterClass : adapter
     */
    public void invoke(Class<?> adapterClass, Object adapter) {
        for (Class<?> view : get(adapterClass)) {
            try {
                view.getDeclaredConstructor(adapterClass).newInstance(adapter);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
