package Views;

import java.io.Serializable;

/**
 * Created mz
 */
public class Point implements Serializable {
    private static final long serialVersionUID = 2101447666592493394L;
    public final int x;
    public final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}