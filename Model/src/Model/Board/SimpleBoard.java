package Model.Board;

import Colors.Colors;
import Model.Turtles.Turtle;
import Model.Utility.Utility;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by Maciej on 2014-05-05.
 */
public class SimpleBoard extends Board {
    private static final long serialVersionUID = -3932283805405624796L;

    public SimpleBoard()
    {

        BoardGraph.Field f = new BoardGraph.Field();
        graph.starts = new ArrayList<>();

        for (int i : Colors.getRealIntegers()) {
            graph.starts.add(new BoardGraph.Field());
        }

        for (int i : Colors.getRealIntegers()) {
            graph.starts.get(i-1).getTurtles().add(new Turtle(i));
        }

        for(BoardGraph.Field a : graph.starts) {
            a.successors.add(f);
            f.predecessors.add(a);
        }

        for (int i = 0; i < 5; i++) {
            BoardGraph.Field s = new BoardGraph.Field();
            f.successors.add(s);
            s.predecessors.add(f);
            f=s;
        }

        BoardGraph.Field End = new BoardGraph.Field(BoardGraph.FieldType.FINAL);
        f.successors.add(End);

        for (BoardGraph.Field field : graph) {
            Utility.Debug.log(Level.INFO, "[BOARD CONSTRUCTOR DEBUG]graph>>" + graph.toString());
        }
    }


}
