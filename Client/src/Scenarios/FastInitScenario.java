package Scenarios;

import Adapters.Interfaces.*;
import Views.FastInit.RandomLoginView;
import Views.Standard.Game.StandardGameView;
import Views.Standard.Menu.StandardMenuView;

public class FastInitScenario extends GUIScenario {
    public FastInitScenario() {
        add(MenuController.class, Views.FastInit.MenuView.class);
        add(GameSelectController.class, Views.FastInit.GameSelectView.class);
        add(GameCreatorController.class, Views.FastInit.GameCreatorView.class);
        add(GameCreatorWaiterController.class, Views.FastInit.GameCreatorWaiterView.class);

        add(MenuController.class, StandardMenuView.class);
        add(GameController.class, StandardGameView.class);

        add(LoginController.class, RandomLoginView.class);
    }
}
