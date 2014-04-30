package Models.Cards;

import Models.Game.GameBoard;
import Models.Game.GameInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * example implementation
 */
public class ExampleCard implements GameCard {
    @Override
    public void use(Integer turtle, GameBoard game_board, GameInfo game_info) {
        throw new NotImplementedException();
    }
}
