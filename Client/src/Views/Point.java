package Views;

import java.io.Serializable;

/**
 * Created mz
 */
public class Point implements Serializable {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
}