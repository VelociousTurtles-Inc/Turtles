package Utility;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by larhard on 19.05.14.
 */
public class Scenario {
    // TODO removing
    private Map<Class<?>, Entry> scenarioMap = new HashMap<>();

    private class Entry {
        Set<Class<?>> views = new HashSet<>();

        void add(Class<?> view) {
            views.add(view);
        }
    }

    public void add(Class<?> adapter, Class<?> view) {
        synchronized (scenarioMap) {
            if (!scenarioMap.containsKey(adapter)) {
                scenarioMap.put(adapter, new Entry());
            }
            scenarioMap.get(adapter).add(view);
        }
    }

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
     * Invokes all registered views for given adapterClass
     * @param adapterClass
     * @param adapter have to implement / extend adapterClass
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
