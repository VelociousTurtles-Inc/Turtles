package Model.Board;

import Model.Utility.Utility;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by Maciej on 2014-05-05.
 */
public class SimpleBoard extends Board {

    public SimpleBoard()
    {

        BoardGraph.Field F = new BoardGraph.Field();
        graph.starts = new ArrayList<>();

        for(int i = 0; i < 5; i++)
            graph.starts.add(new BoardGraph.Field());

        for(BoardGraph.Field a : graph.starts)
            a.successors.add(F);

        for (int i = 0; i < 6; i++)
        {
            BoardGraph.Field S = new BoardGraph.Field();
            F.successors.add(S);
            F=S;
        }
        BoardGraph.Field End = new BoardGraph.Field(BoardGraph.FieldType.FINAL);
        F.successors.add(End);

//        for(int i = 0; i<5; i++) graph.start.turtles.add(new Turtle(i));

        for (BoardGraph.Field field : graph)
            Utility.Debug.log(Level.INFO,"[BOARD CONSTRUCTOR DEBUG]graph>>"+graph.toString());

    }


}
