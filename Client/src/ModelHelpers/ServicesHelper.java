package ModelHelpers;

import Model.Cards.Card;
import Model.Cards.SimpleForwardCard;
import ServicesTypes.BoardGraph;
import ServicesTypes.Field;

import java.util.*;

/**
 * Created by mz18 on 12/05/14.
 */
public class ServicesHelper {
    public static Iterable<Field> getIterableBoard(final BoardGraph board)
    {
        return new Iterable<Field>()
        {
            @Override
            public Iterator<Field> iterator() {
                int position = 0;
                return new Iterator<Field>() {
                    Queue<Field> q;

                    {
                        q = new LinkedList<Field>();
                        Queue<Field> tmp = new LinkedList<Field>();
                        {
                            Field ref = board.getStart();
                            q.add(board.getStart());
                            tmp.add(ref);
                            while (!tmp.isEmpty())
                                ref = tmp.remove();
                            for (Field it : ref.getSuccessors()) {
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
                    public Field next() {
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
    }
    public static Map<Integer, CardInfo> createCardMap() {
        Map<Integer, CardInfo> resultMap = new TreeMap<Integer, CardInfo>();
        for(int i = 1; i<=5; i++) {
            for(int j = 1; j<=3; j++) {
                resultMap.put((3*(i-1)+j), new SimpleForwardCard(i));
            }
        }

        return resultMap;
    }
}