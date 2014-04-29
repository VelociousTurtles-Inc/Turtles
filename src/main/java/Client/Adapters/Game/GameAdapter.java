package Client.Adapters.Game;

import Models.Game.GameBoard;
import Models.Game.GameCard;
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
    public void playCard(GameCard card);
    public void surrender();
}
