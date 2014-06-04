package Views;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    private static final long serialVersionUID = 7015181934501525828L;
    public final List<Point> positions = new ArrayList<>();
    public int size, maxTurtles;
    public String name;
}
