package Scenarios;

import GameWatchdogs.StandardGameWatchdog;
import Server.Interfaces.ServerGameDispenser;
import Utility.Scenario;

/**
 * Created by larhard on 25.05.14.
 */
public class StandardScenario extends Scenario {
    public StandardScenario() {
        add(ServerGameDispenser.class, StandardGameWatchdog.class);
    }
}
