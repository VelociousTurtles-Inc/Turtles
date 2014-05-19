package Interfaces;

import java.util.List;

/**
 * Created by mz18 on 15/05/14.
 */
public interface IPlayer {
    public List<Integer> getCards();
    public int changeCard(int oldCardID);
    public void drawCards();
}
