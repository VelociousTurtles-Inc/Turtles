package Scenarios;

import Controllers.Interfaces.*;
import Views.Standard.FinalLogin.FinalLoginView;
import Views.Standard.Game.StandardGameView;
import Views.Standard.GameCreation.GameCreator.GameCreatorView;
import Views.Standard.GameCreation.GameCreatorsWaiting.GameCreatorWaiterView;
import Views.Standard.GameCreation.GameWaiting.GameWaiterView;
import Views.Standard.GameSelect.GameSelectView;
import Views.Standard.Login.LoginView;
import Views.Standard.Menu.StandardMenuView;

/**
 * standard scenario for local tests
 */
public class StandardScenario extends GUIScenario {
    public StandardScenario() {
        add(MenuController.class, StandardMenuView.class);
        add(GameSelectController.class, GameSelectView.class);
        add(GameController.class, StandardGameView.class);
        add(GameCreatorController.class, GameCreatorView.class);
        add(GameCreatorWaiterController.class, GameCreatorWaiterView.class);
        add(GameWaiterController.class, GameWaiterView.class);
        add(LoginController.class, FinalLoginView.class);
    }
}
