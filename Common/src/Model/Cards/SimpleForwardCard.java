package Model.Cards;


import Colors.Colors;
import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Turtles.Turtle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleForwardCard extends Card {
    private static final long serialVersionUID = 4739049860217674267L;

    public SimpleForwardCard(int color) {
        super(color);
    }

    public String getType() {
        return "SimpleForwardCard";
    }

    public static List<Card> populate()
    {
        ArrayList<Card> result = new ArrayList<>();
        for (int j = 0; j < 3; j++)
            for (int i : Colors.getRealIntegers())
                result.add(new SimpleForwardCard(i-1));
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
                if (field.successors.size() != 1)throw new IllegalArgumentException("<"+(String.valueOf(field.successors.size()))+">");
                if (turtle.color == this.getColor())
                {
                    do {
                        field.successors.get(0).turtles.add(turtle);
                        it.remove();
                        if (it.hasNext())turtle = it.next();
                        else turtle = null;
                    } while(turtle != null);
                    break outer;
                }

            }
        }
    }

    @Override
    public CardInfo getCardInfo() {
        return new CardInfo("SimpleForwardCard",this.id,this.color);
    }

    public String toString()
    {
        return "Idz do przodu";
    }
    public String getDescription()
    {
        return "Porusza żolwia we wskazanym kolorze o jedno pole do przodu";
    }
}
