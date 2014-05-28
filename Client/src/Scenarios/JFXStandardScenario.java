package Scenarios;

import Controllers.Interfaces.*;
import Utility.Scenario;
import Views.Standard.Game.StandardGameView;
import Views.Standard.GameCreation.GameCreator.GameCreatorView;
import Views.Standard.GameCreation.GameCreatorsWaiting.GameCreatorWaiterView;
import Views.Standard.GameCreation.GameWaiting.GameWaiterView;
import Views.Standard.GameSelect.GameSelectView;
import Views.Standard.Login.LoginView;
import Views.Standard.Menu.StandardMenuView;

public class JFXStandardScenario extends Scenario {
    public JFXStandardScenario() {
        add(MenuController.class, StandardMenuView.class);
        add(GameSelectController.class, GameSelectView.class);
        add(GameController.class, StandardGameView.class);
        add(GameCreatorController.class, GameCreatorView.class);
        add(GameCreatorWaiterController.class, GameCreatorWaiterView.class);
        add(GameWaiterController.class, GameWaiterView.class);
        add(LoginController.class, LoginView.class);
    }
}
