package Adapters;

import Adapters.Interfaces.MenuController;
import Main.Client;
import Utility.DebugWriter;

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
        new Thread() {
            @Override
            public void run() {
                try {
                    //StandardGameSelectController myGCC = new StandardGameSelectController();
                    StandardLoginController myController = new StandardLoginController();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void exitProgram() {
        assert DebugWriter.write(this + ".exitProgram()");
        System.exit(0);
    }
}
