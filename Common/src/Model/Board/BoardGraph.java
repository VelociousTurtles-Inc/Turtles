package Model.Board;

import Model.Turtles.Turtle;

import java.io.Serializable;
import java.util.*;

public class BoardGraph implements Iterable<BoardGraph.Field>, Serializable {
    private static final long serialVersionUID = -2995046707046230025L;

    private static int counter;
    public ArrayList<Field> starts;
    public Field end;

    public ArrayList<Field> getStarts() {
        return starts;
    }

    /**
     * Warning : only works for simple linear boards as for now (18 may 2014)
     *  -- Szymon
     */
    @Override
    public Iterator<Field> iterator() {
        //TODO bad BFS
        return new Iterator<Field>() {
            Queue<Field> q;
            {
                q = new LinkedList<>();
                Queue<Field> tmp = new LinkedList<>();
                Field ref;
                for(Field a : starts)
                    q.add(a);
                for(Field a : starts)
                    tmp.add(a);
                while(!tmp.isEmpty()) {
                    ref = tmp.remove();
                    for (Field it : ref.getSuccessors()) {
                        q.add(it);
                        tmp.add(it);
                        end = it;
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
            }
        };
    }

    public enum FieldType
    {
        FINAL, NORMAL, GRAVE
    }

    public static class Field implements Serializable {
        private static final long serialVersionUID = -5406846425764941114L;

        public final int id;

        public final List<Field> successors = new LinkedList<>();
        public final List<Field> predecessors = new LinkedList<>();

        public List<Field> getSuccessors() {
            return successors;
        }
        public List<Field> getPredecessors() {
            return predecessors;
        }

        public final List<Turtle> turtles = new LinkedList<>();

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

        public List<Turtle> getTurtles() {
            return turtles;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("[");
            boolean c = false;
            for (Turtle turtle : turtles) {
                if (c) {
                    result.append(", ");
                }
                c = true;
                result.append(turtle);
            }
            result.append("]");
            return result.toString();
        }

        public FieldType getType() {
            return type;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        boolean c = false;
        for (Field field : this) {
            if (c) {
                result.append(", ");
            }
            c = true;
            result.append(field);
        }
        result.append("]");
        return result.toString();
    }
}
