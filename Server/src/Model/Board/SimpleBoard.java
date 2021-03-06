package Model.Board;

import Enums.Colors;
import Model.Turtles.Turtle;
import Utility.Utility;

import java.util.ArrayList;
import java.util.logging.Level;

public class SimpleBoard extends Board {
    private static final long serialVersionUID = -3932283805405624796L;
    private final BoardGraph.Field end;
    private final BoardGraph.Field grave;

    public SimpleBoard()
    {
        grave = new BoardGraph.Field(BoardGraph.FieldType.GRAVE);

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

            a.predecessors.add(grave);
        }

        for (int i = 0; i < 5; i++) {
            BoardGraph.Field s = new BoardGraph.Field();
            f.successors.add(s);
            s.predecessors.add(f);
            f=s;
        }

        end = new BoardGraph.Field(BoardGraph.FieldType.FINAL);
        f.successors.add(end);

        end.successors.add(grave);

        for (BoardGraph.Field field : graph) {
            Utility.Debug.log(Level.INFO, "[BOARD CONSTRUCTOR DEBUG]graph>>" + graph.toString());
        }
    }

    @Override
    BoardGraph.Field getEndField() {
        return end;
    }
}
