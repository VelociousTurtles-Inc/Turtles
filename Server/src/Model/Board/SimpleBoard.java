package Model.Board;

/**
 * Created by Maciej on 2014-05-05.
 */
public class SimpleBoard extends Board{

    SimpleBoard()
    {
        BoardGraph.Field F = graph.start;
        for (int i = 0; i < 10; i++)
        {
            BoardGraph.Field S = new BoardGraph.Field();
            F.successors.add(S);
            S=F;
        }
        BoardGraph.Field End = new BoardGraph.Field(BoardGraph.FieldType.FINAL);
        F.successors.add(End);

    }


}
