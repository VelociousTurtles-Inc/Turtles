package Model.Cards;

import Enums.Colors;
import Model.Board.Board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface for Cards. Card should be registered in CardType enum before implementing this interface.
 * This is NOT an interface because WebMethod result must be instantiable in some way.
 * Static method populate should be overridden. Cards are currently immutable.
 * It's though highly possible that above disclaimer will be a subject to many changes due to WebResult/Parameter character od this class.
 */

public abstract class Card implements Serializable {
    private static final long serialVersionUID = 4650075470587092743L;

    final protected int color;
    final protected int id;
    static int counter;

    public Card()
    {
        color = Colors.asInteger(Colors.NULL);
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

    /**
     * Gets all possible Deck variants of the Card.
     * Method is here just to show Card classes structure. It should not be invoked from this class directly.
     */
    public static List<Card> populate()
    {
        assert false;
        return new ArrayList<Card>();
    }

    public abstract void play(Board board);
    public abstract String getType();

    public String getImageName() {
        return getType() + Colors.asString(color) + ".png";
    }

    @Override
    public String toString() {
        return "Card : " + getShortDecription() + " [ " + Colors.asString(getColor()) + " ]";
    }

    public String getShortDecription() {
        return getClass().toString();
    }

    public String getDescription() {
        return getClass().toString() + " [ " + Colors.asString(getColor()) + " ]";
    }

    public CardInfo getCardInfo() {
        return new CardInfo(this.getType(), this.id, this.color);
    }
}
