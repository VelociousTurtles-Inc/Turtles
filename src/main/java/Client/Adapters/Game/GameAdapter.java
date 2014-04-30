package Client.Adapters.Game;

import Models.Cards.GameCard;
import Models.Game.GameBoard;
import Models.Game.TurtlesList;

public interface GameAdapter {
    /**
     * @return old_hash != new_hash ? GameBoard : null
     */
    public GameBoard getGameBoard(int old_hash);
    /**
     * @return old_hash != new_hash ? TurtlesList : null
     */
    public TurtlesList getTurtlesList(int old_hash);

    /**
     * @param card karta do zagrania
     * @return czy się powiodło
     */
    public boolean playCard(GameCard card); // do ustalenia
    public void surrender();
}
