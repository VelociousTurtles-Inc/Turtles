package Scenarios;

import Adapters.Interfaces.*;
import Views.Standard.Game.StandardGameView;
import Views.Standard.GameCreation.GameCreator.GameCreatorView;
import Views.Standard.GameCreation.GameCreatorsWaiting.GameCreatorsWaiterView;
import Views.Standard.GameCreation.GameWaiting.GameWaiterView;
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
        add(GameCreatorController.class, GameCreatorView.class);
        add(GameCreatorWaiterController.class, GameCreatorsWaiterView.class);
        add(GameSelectController.class, GameSelectView.class);
        add(GameWaiterController.class, GameWaiterView.class);
    }
}
