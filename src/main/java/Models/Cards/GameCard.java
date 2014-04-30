package Models.Cards;

import Models.Game.GameBoard;
import Models.Game.GameInfo;

public interface GameCard {
    /**
     * Edytuje planszę i ustawienie żółwi według uznania
     * @param turtle id żółwia / null jeśli karta niezależna od wyboru
     * @param game_board graf pól
     * @param game_info ustawienie żółwi
     */
    public void use(Integer turtle, GameBoard game_board, GameInfo game_info);
}
