package Scenarios;

import Adapters.Interfaces.GameController;
import Adapters.Interfaces.GameSelectController;
import Adapters.Interfaces.MenuController;
import Views.Standard.Game.StandardGameView;
import Views.Standard.GameSelect.GameSelectView;
import Views.Standard.Menu.StandardMenuView;

/**
 * Created by larhard on 19.05.14.
 */
public class StandardScenario extends GUIScenario {
    public StandardScenario() {
        add(MenuController.class, StandardMenuView.class);
        add(GameSelectController.class, GameSelectView.class);
        add(GameController.class, StandardGameView.class);
    }
}
