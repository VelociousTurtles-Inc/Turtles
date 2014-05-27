package Controllers;

import Controllers.Interfaces.MenuController;
import Main.Client;
import Utility.DebugWriter;

import java.rmi.RemoteException;

public class StandardMenuController implements MenuController {

    public StandardMenuController() {
        assert DebugWriter.write("Create new StandardMenuController");
        Client.scenario.invoke(MenuController.class, this);
    }

    @Override
    public void startGame() {
        new Thread() {
            @Override
            public void run() {
                try {
                    new StandardLoginController();
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
