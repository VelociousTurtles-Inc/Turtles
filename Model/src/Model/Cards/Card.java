package Model.Cards;

import Model.Board.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for Cards. Card should be registered in CardType enum before implementing this interface.
 * This is NOT an interface because WebMethod result must be instantiable in some way.
 * Static method populate should be overridden. Cards are currently immutable.
 * It's though highly possible that above disclaimer will be a subject to many changes due to WebResult/Parameter character od this class.
 **/

public abstract class Card {

    final int color;
    final int id;
    static int counter;

    public Card()
    {
        color = 0;
        id = counter++;
    }

    protected Card(int color) {
        id = counter++;
        this.color = color;
    }
    public int getID()
    {
        return id;
    }
    public int getColor()
    {
        return color;
    }
    /*
        Gets all possible Deck variants of the Card.
        Method is here just to show Card classes structure. It should not be invoked from this class directly.
     */
    public static List<Card> populate()
    {
        assert false;
        return new ArrayList<Card>();
    }

    /*@Override
    public String toString() {
        return "Card : " + getType() + " of color " + getColor();
    }*/

    public abstract void play(Board board);

    @Override
    public String toString() {
        return "Card : " + getClass().toString() + " of color " + getColor();
    }
}
