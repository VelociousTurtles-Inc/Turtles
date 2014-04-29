package Models.Game;

public interface GameInfo {
    /**
     * @return current player id
     */
    public int getCurrentPlayer();
    /**
     * @return winner id
     */
    public int getWinner();
}
