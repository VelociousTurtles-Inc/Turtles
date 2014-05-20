package Utility;


/**
 * Created by mz18 on 12/05/14.
 */

/*
    Commented out on 18 may 2014 by Szymon
        boardGraph implements iterable, why should we implement it
        once again here ?
        Leaving in case it would really be needed in the future.
 */

    /*
public class ServicesHelper {
    public static Iterable<BoardGraph.Field> getIterableBoard(final BoardGraph board)
    {

        return new Iterable<BoardGraph.Field>()
        {

            @Override
            public Iterator<BoardGraph.Field> iterator() {
                int position = 0;
                return new Iterator<BoardGraph.Field>() {
                    Queue<BoardGraph.Field> q;

                    {
                        q = new LinkedList<BoardGraph.Field>();
                        Queue<BoardGraph.Field> tmp = new LinkedList<BoardGraph.Field>();

                        BoardGraph.Field ref = board.getStart();
                        q.add(board.getStart());
                        tmp.add(ref);
                        while (!tmp.isEmpty()) {
                            ref = tmp.remove();
                            for (BoardGraph.Field it : ref.getSuccessors()) {
                                q.add(it);
                                tmp.add(it);
                            }
                        }


                    }

                    @Override
                    public boolean hasNext() {
                        return !q.isEmpty();
                    }

                    @Override
                    public BoardGraph.Field next() {
                        return q.remove();
                    }

                    @Override
                    public void remove() {
                        assert false;
                        return;
                    }
                };
            }
        };
    } */
    /*public static Map<Integer, CardInfo> createCardMap() {
        Map<Integer, CardInfo> resultMap = new TreeMap<Integer, CardInfo>();
        for(int i = 1; i<=5; i++) {
            for(int j = 1; j<=3; j++) {
                resultMap.put((3*(i-1)+j), new SimpleForwardCard(i));
            }
        }

        return resultMap;
    }*/

/*
}

*/