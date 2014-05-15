package Model.Board;

import java.io.Serializable;

/**
 * Created by Maciej on 2014-05-05.
 */
public class Board implements Serializable {
    private static final long serialVersionUID = 1445857174659600676L;

    public BoardGraph graph;

    public Board() {
        graph= new BoardGraph();
    }
}
