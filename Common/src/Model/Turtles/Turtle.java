package Model.Turtles;

import Enums.Colors;
import Model.Board.BoardGraph;

import java.io.Serializable;

public class Turtle implements Serializable {
    private static final long serialVersionUID = 2763735491352166723L;

    private final int color;
    private boolean moved = false;
    // Player ref goes here
    /*
        Backward Board reference
     */
    private BoardGraph.Field position;

    public Turtle(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void move(BoardGraph.Field position) {
        moved = true;
        this.position = position;
    }

    public BoardGraph.Field getPosition() {
        return position;
    }

    public boolean isMoved() {
        return moved;
    }

    @Override
    public String toString() {
        return "Turtle " + Colors.asString(getColor());
    }
}
