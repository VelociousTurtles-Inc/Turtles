package Model.Board;

import Model.Turtle;

import java.util.*;

/**
 * Created by Maciej on 2014-05-05.
 */
public class BoardGraph implements Iterable<BoardGraph.Field>
{
    static int counter;
    public Field start;

    @Override
    public Iterator<Field> iterator() {
        int position = 0;
        return new Iterator<Field>() {
            Queue<Field> q;
            {
                q = new LinkedList<Field>();
                Queue<Field> tmp = new LinkedList<Field>();
                {
                    Field ref = start;
                    q.add(start);
                    tmp.add(ref);
                    while(!tmp.isEmpty())
                        ref = tmp.remove();
                    for(Field it : ref.successors) {
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

    enum FieldType
    {
        FINAL, NORMAL
    }
    public static class Field {

        public final int id;
        public List<Field> successors = new LinkedList<>();
        public List<Turtle> turtles = new LinkedList<>();
        public final FieldType type;
        public Field ()
        {
            this(FieldType.NORMAL);
        }
        public Field(FieldType type)
        {
            id = counter++;
            this.type = type;
        }
    }

}
