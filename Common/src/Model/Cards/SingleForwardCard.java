package Model.Cards;


import Colors.Colors;
import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Turtles.Turtle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SingleForwardCard extends Card {
    private static final long serialVersionUID = 4739049860217674267L;

    public SingleForwardCard(int color) {
        super(color);
    }

    public String getType() {
        return "SingleForwardCard";
    }

    public static List<Card> populate() {
        List<Card> result = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            for (int i : Colors.getRealIntegers()) {
                result.add(new SingleForwardCard(i));
            }
        }
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
                Random random = new Random();
                assert turtle.color != Colors.asInteger(Colors.NULL) : "Turtle colors are shifted";
                if (turtle.color == this.getColor()) {
                    if (field.getSuccessors().size() < 1)throw new IllegalArgumentException("<"+(String.valueOf(field.getSuccessors().size()))+">");
                    BoardGraph.Field target = field.getSuccessors().get(random.nextInt(field.getSuccessors().size()));
                    do {
                        target.turtles.add(turtle);
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
        return new CardInfo("SingleForwardCard",this.id,this.color);
    }

    @Override
    public String getShortDecription() {
        return "Idź do przodu";
    }

    @Override
    public String getDescription() {
        return "Porusza żolwia we wskazanym kolorze o jedno pole do przodu";
    }
}
