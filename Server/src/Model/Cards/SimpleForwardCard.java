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
        ArrayList<Card> result = new ArrayList<Card>();
        for (int j = 0; j < 3; j++)
        for (int i = 0; i < 4; i++)
        result.add(new SimpleForwardCard(i));
        return result;
    }
}
