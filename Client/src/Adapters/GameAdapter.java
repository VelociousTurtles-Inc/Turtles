package Adapters;

// For now instantiating directly
import Services.GameService;
import Views.ControllerInterface;

/**
 * Created by Maciej on 2014-05-05.
 */
public class GameAdapter implements ControllerInterface {
    GameService game;
    public GameAdapter() {
       GameService game = new GameService();
    }

    @Override
    public void playCard(int numberOfCard) {
        game.playCard(game.getPlayerCards().get(numberOfCard));
    }
}
