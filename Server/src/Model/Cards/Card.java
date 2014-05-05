package Model.Cards;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface for Cards. Card should be registered in CardType enum before implementing this interface.
 * This is NOT an interface because WebMethod result must be instantiable in some way.
 * Static method populate should be overridden. Cards are currently immutable.
 * It's though highly possible that above disclaimer will be a subject to many changes due to WebResult/Parameter character od this class.
 **/

public class Card {

    final int color;
    final CardType type;

    public Card()
    {
        color = 0;
        type = CardType.SimpleForward;
    }

    protected Card(int color, CardType type) {
        this.color = color;
        this.type = type;
    }

    public int getColor()
    {
        return color;
    }
    public CardType getType()
    {
        return type;
    }
    /*
        Gets all possible Deck variants of the Card.
        Method is here just to show Card classes structure. It should not be invoked from this class directly.
     */
    public static List<Card> populate()
    {
        assert false;
        return new ArrayList<>();
    }
}
