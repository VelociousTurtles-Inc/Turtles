package Model;

import Model.Board.BoardGraph;

/**
 * Created by Maciej on 2014-05-05.
 */
public class Turtle {
    public int color;
    // Player ref goes here
    /*
        Backward Board reference
     */
    BoardGraph.Field position;

    public Turtle(int color) {
        this.color = color;
    }
}
