package Model.Cards;

import Enums.Colors;
import Model.Board.Board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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
        return new ArrayList<>();
    }

    public abstract void play(Board board);
    public abstract String getType();

    public String getImageName() {
        return getType() + Colors.asString(color) + ".png";
    }

    @Override
    public String toString() {
        return "Card : " + getShortDescription() + " [ " + Colors.asString(getColor()) + " ]";
    }

    public String getShortDescription() {
        return getClass().toString();
    }

    public String getDescription() {
        return getClass().toString() + " [ " + Colors.asString(getColor()) + " ]";
    }

    public CardInfo getCardInfo() {
        return new CardInfo(this.getType(), this.id, this.color);
    }
}
