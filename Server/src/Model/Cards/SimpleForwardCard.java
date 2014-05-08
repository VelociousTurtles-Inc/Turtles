package Model.Cards;

import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Turtle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleForwardCard extends Card {


    public SimpleForwardCard(int color) {
        super(color);
    }

    public static List<Card> populate()
    {
        ArrayList<Card> result = new ArrayList<>();
        for (int j = 0; j < 3; j++)
        for (int i = 0; i < 4; i++)
        result.add(new SimpleForwardCard(i));
        return result;
    }

    @Override
    public void play(Board board)
    {
        outer: for (BoardGraph.Field field : board.graph)
        {
            for (Iterator<Turtle> it = field.turtles.iterator(); it.hasNext();)
            {
                Turtle turtle = it.next();
                if (field.successors.size() != 1)throw new IllegalArgumentException();
                if (turtle.color == this.getColor())
                {
                    do {
                        field.successors.get(0).turtles.add(turtle);
                        if (it.hasNext())turtle = it.next();
                        else turtle = null;
                    }while(turtle != null);
                    break outer;
                }

            }
        }
    }

    public String toString()
    {
        return "Idz do przodu";
    }
    public String getDescription()
    {
        return "Porusza żolwia we wskazanymk kolorze o jedno pole do przodu";
    }
}
