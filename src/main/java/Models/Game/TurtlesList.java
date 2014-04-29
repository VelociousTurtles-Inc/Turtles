package Models.Game;

import java.util.List;

/**
 * Lista list żółwi na planszy
 */

public interface TurtlesList {
    /**
     * @return lista żółwi stojących na danym polu
     */
    abstract public List<? extends Turtle> getField(int field_id);
}
