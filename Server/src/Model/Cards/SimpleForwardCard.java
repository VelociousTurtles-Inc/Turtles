package Model.Cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2014-05-05.
 */
public class SimpleForwardCard extends Card {


    public SimpleForwardCard(int color) {
        super(color, CardType.SimpleForward);
    }

    public static List<Card> populate()
    {
        return new ArrayList<>();
    }
}
