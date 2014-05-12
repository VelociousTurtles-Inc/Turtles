package Adapters;

import Adapters.Interfaces.GameController;
import Adapters.Interfaces.MenuController;
import Views.Standard.Menu.StandardMenuView;

/**
 * Created by mz18 on 8/05/14.
 */
public class StandardMenuController implements MenuController {

    public StandardMenuController() {
        new StandardMenuView(this);
    }

    @Override
    public void startGame() {
        GameController myGame = new SimpleGameAdapter();
    }

    @Override
    public void exitProgram() {
        System.exit(0);
    }
}
