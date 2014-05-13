package Model.Board;

/**
 * Created by Maciej on 2014-05-05.
 */
public class SimpleBoard extends Board{

    public SimpleBoard()
    {
        BoardGraph.Field F = graph.start = new BoardGraph.Field();
        for (int i = 0; i < 10; i++)
        {
            BoardGraph.Field S = new BoardGraph.Field();
            F.successors.add(S);
            F=S;
        }
        BoardGraph.Field End = new BoardGraph.Field(BoardGraph.FieldType.FINAL);
        F.successors.add(End);

    }


}
