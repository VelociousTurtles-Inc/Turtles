package Model.Turtles;

import Enums.Colors;
import Model.Board.BoardGraph;

import java.io.Serializable;

public class Turtle implements Serializable {
    private static final long serialVersionUID = 2763735491352166723L;

    public int color;
    // Player ref goes here
    /*
        Backward Board reference
     */
    BoardGraph.Field position;

    public Turtle(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Turtle " + Colors.asString(getColor());
    }
}
