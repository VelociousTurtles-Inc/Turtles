package Model.Cards;


import Enums.Colors;
import Model.Board.Board;
import Model.Board.BoardGraph;
import Model.Turtles.Turtle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DoubleForwardCard extends Card {
    private static final long serialVersionUID = 5936392580097739232L;

    public DoubleForwardCard(int color) {
        super(color);
    }

    public String getType() {
        return "DoubleForwardCard";
    }

    public static List<Card> populate() {
        List<Card> result = new ArrayList<>();
        for (int j = 0; j < 2; j++) {
            for (int i : Colors.getAllIntegers()) {
                result.add(new DoubleForwardCard(i));
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
                    if (field.getSuccessors().size() < 1)throw new IllegalArgumentException("<"+(String.valueOf(field.getSuccessors().size()))+">");
                    BoardGraph.Field target = field.getSuccessors().get(random.nextInt(field.getSuccessors().size()));
                    if (target.getSuccessors().size() < 1)throw new IllegalArgumentException("<"+(String.valueOf(target.getSuccessors().size()))+">");
                    target = target.getSuccessors().get(random.nextInt(target.getSuccessors().size()));
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
        return new CardInfo("DoubleForwardCard",this.id,this.color);
    }

    @Override
    public String getShortDecription() {
        return "Idź do przodu o dwa";
    }

    @Override
    public String getDescription() {
        return "Porusza żolwia we wskazanym kolorze o dwa pola do przodu";
    }
}
