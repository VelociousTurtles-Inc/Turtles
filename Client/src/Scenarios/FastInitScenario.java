package Scenarios;

import Adapters.Interfaces.*;
import Views.Standard.Game.StandardGameView;
import Views.Standard.Menu.StandardMenuView;

/**
 * Created by larhard on 20.05.14.
 */
public class FastInitScenario extends GUIScenario {
    public FastInitScenario() {
        add(MenuController.class, Views.FastInit.MenuView.class);
        add(GameSelectController.class, Views.FastInit.GameSelectView.class);
        add(GameCreatorController.class, Views.FastInit.GameCreatorView.class);
        add(GameCreatorWaiterController.class, Views.FastInit.GameCreatorWaiterView.class);


        add(MenuController.class, StandardMenuView.class);
        add(GameController.class, StandardGameView.class);
    }
}
