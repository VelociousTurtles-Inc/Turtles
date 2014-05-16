package Adapters;

import Adapters.Interfaces.GameController;
import Adapters.Interfaces.MenuController;
import ModelHelpers.DebugWriter;
import Views.Standard.Menu.StandardMenuView;

/**
 * Created by mz18 on 8/05/14.
 */
public class StandardMenuController implements MenuController {

    public StandardMenuController() {
        assert DebugWriter.write("Create new StandarMenuController");
        new StandardMenuView(this);
    }

    @Override
    public void startGame() throws Exception {
        /*assert DebugWriter.write(this + ".startGame()");
        GameController myGame = new SimpleGameAdapter();*/
        StandardGameChoosingController myGCC = new StandardGameChoosingController();
    }

    @Override
    public void exitProgram() {
        assert DebugWriter.write(this + ".exitProgram()");
        System.exit(0);
    }
}
