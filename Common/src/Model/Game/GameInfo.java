package Model.Game;

import Model.Turtles.Turtle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2014-04-24.
 */
public class GameInfo {
    static int counter;
    public int id;

    public List<Turtle> turtles = new ArrayList<>();

    public GameInfo() {
        id = counter++;
    }
}
