package Model.Cards;


import Colors.Colors;
import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Turtles.Turtle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SingleBackwardCard extends Card {
    private static final long serialVersionUID = -2301673680355571904L;

    public SingleBackwardCard(int color) {
        super(color);
    }

    public String getType() {
        return "SingleBackwardCard";
    }

    public static List<Card> populate() {
        List<Card> result = new ArrayList<>();
        for (int j = 0; j < 2; j++) {
            for (int i : Colors.getAllIntegers()) {
                result.add(new SingleBackwardCard(i));
            }
        }
        return result;
    }

    @Override
    public void play(Board board)
    {
        int color = getColor();
        if (color == Colors.asInteger(Colors.NULL)) {
            color = Colors.asInteger(Colors.getRandomColorFromReal());
        }
        outer: for (BoardGraph.Field field : board.graph)
        {
            for (Iterator<Turtle> it = field.turtles.iterator(); it.hasNext();)
            {
                Turtle turtle = it.next();
                assert turtle.color != Colors.asInteger(Colors.NULL) : "Turtle colors are shifted";
                if (turtle.color == color) {
                    Random random = new Random();
                    if (field.getPredecessors().size() < 1)throw new IllegalArgumentException("<"+(String.valueOf(field.getPredecessors().size()))+">");
                    BoardGraph.Field target = field.getPredecessors().get(random.nextInt(field.getPredecessors().size()));
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
        return new CardInfo("SingleBackwardCard",this.id,this.color);
    }

    @Override
    public String getShortDecription() {
        return "Idź do tyłu";
    }

    @Override
    public String getDescription() {
        return "Porusza żolwia we wskazanym kolorze o jedno pole do tyłu";
    }
}
