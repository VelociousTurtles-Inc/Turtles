package Models.Game;

import Models.Cards.GameCard;

/**
 * Model Gry
 */

public interface GameModel {
    public void playCard(GameCard card);
    /**
     * @return old_hash != new_hash ? GameBoard : null
     */
    public GameBoard getGameBoard(int old_hash);
    /**
     * @return old_hash != new_hash ? TurtlesList : null
     */
    public TurtlesList getTurtlesList(int old_hash);
    /**
     * @return game info
     */
    public GameInfo getGameInfo();
}
