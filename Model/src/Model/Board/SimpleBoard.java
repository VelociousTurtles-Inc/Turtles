package Model.Board;

import Model.Utility.Utility;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Maciej on 2014-05-05.
 */
public class SimpleBoard extends Board{

    public SimpleBoard()
    {
        BoardGraph.Field F = graph.start = new BoardGraph.Field();
        for (int i = 0; i < 6; i++)
        {
            BoardGraph.Field S = new BoardGraph.Field();
            F.successors.add(S);
            F=S;
        }
        BoardGraph.Field End = new BoardGraph.Field(BoardGraph.FieldType.FINAL);
        F.successors.add(End);

        for (BoardGraph.Field field : graph)
            Utility.Debug.log(Level.INFO,"[BOARD CONSTRUCTOR DEBUG]graph>>"+graph.toString());

    }


}
