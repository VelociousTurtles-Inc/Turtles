package Model.Board;

import Enums.Colors;
import Utility.Utility;

import java.io.Serializable;

public abstract class Board implements Serializable {
    private static final long serialVersionUID = 1445857174659600676L;

    public final BoardGraph graph;

    public Board() {
        graph = new BoardGraph();
    }

    /**
     * @return null or Color of winning turtle
     */
    public Colors checkWins() {
        System.out.println(getEndField());
        BoardGraph.Field endField = getEndField();
        Utility.logInfo("Last field = " + endField);
        if (endField.turtles.size() > 0) {
            return Colors.asColor(endField.turtles.get(endField.turtles.size() - 1).getColor());
        }
        return null;
    }

    abstract BoardGraph.Field getEndField();
}
