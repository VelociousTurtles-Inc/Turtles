package Model.Board;

import Colors.Colors;

import java.io.Serializable;

/**
 * Created by Maciej on 2014-05-05.
 */
public abstract class Board implements Serializable {
    private static final long serialVersionUID = 1445857174659600676L;

    public BoardGraph graph;

    public Board() {
        graph = new BoardGraph();
    }

    /**
     * @return null or Color of winning turtle
     */
    public Colors checkWins() {
        System.out.println(getEndField());
        BoardGraph.Field endField = getEndField();
        if (endField.turtles.size() > 0) {
            return Colors.asColor(endField.turtles.get(endField.turtles.size() - 1));
        }
        return null;
    }

    abstract BoardGraph.Field getEndField();
}
