package Model;

import Model.Turtles.Turtle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2014-04-24.
 */
public class GameInfo implements Serializable {
    private static final long serialVersionUID = -5362696273324117682L;

    static int counter;
    public int id;

    public List<Turtle> turtles = new ArrayList<>();

    public GameInfo() {
        id = counter++;
    }
}
