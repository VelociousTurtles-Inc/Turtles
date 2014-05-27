package Adapters;

import Adapters.Interfaces.MenuController;
import Main.Client;
import Utility.DebugWriter;

import java.rmi.RemoteException;

public class StandardMenuController implements MenuController {

    public StandardMenuController() {
        assert DebugWriter.write("Create new StandarMenuController");
        Client.scenario.invoke(MenuController.class, this);
    }

    @Override
    public void startGame() throws RemoteException {
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
