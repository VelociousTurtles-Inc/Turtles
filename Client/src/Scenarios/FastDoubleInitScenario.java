package Scenarios;

import Controllers.Interfaces.*;
import Views.FastInit.RandomLoginView;
import Views.Standard.Game.StandardGameView;
import Views.Standard.Menu.StandardMenuView;

/**
 * initializes random game with 2 random players
 *
 * bugs : start in MenuView does not work properly
 */
public class FastDoubleInitScenario extends GUIScenario {
    public FastDoubleInitScenario() {
        add(MenuController.class, Views.DoubleFastInit.MenuView.class);
        add(GameSelectController.class, Views.DoubleFastInit.GameSelectView.class);
        add(GameCreatorController.class, Views.DoubleFastInit.GameCreatorView.class);
        add(GameCreatorWaiterController.class, Views.DoubleFastInit.GameCreatorWaiterView.class);
        add(GameWaiterController.class, Views.DoubleFastInit.GameWaiterView.class);

        add(MenuController.class, StandardMenuView.class);
        add(GameController.class, StandardGameView.class);

        add(LoginController.class, RandomLoginView.class);
    }
}
