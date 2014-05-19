package Adapters;

import Adapters.Interfaces.MenuController;
import Main.Client;
import ModelHelpers.DebugWriter;

/**
 * Created by mz18 on 8/05/14.
 */
public class StandardMenuController implements MenuController {

    public StandardMenuController() {
        assert DebugWriter.write("Create new StandarMenuController");
        Client.scenario.invoke(MenuController.class, this);
    }

    @Override
    public void startGame() throws Exception {
        /*assert DebugWriter.write(this + ".startGame()");
        GameController myGame = new SimpleGameAdapter();*/
        StandardGameSelectController myGCC = new StandardGameSelectController();
    }

    @Override
    public void exitProgram() {
        assert DebugWriter.write(this + ".exitProgram()");
        System.exit(0);
    }
}
