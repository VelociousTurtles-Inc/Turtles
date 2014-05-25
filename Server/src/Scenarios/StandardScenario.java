package Scenarios;

import GameWatchdogs.StandardGameWatchdog;
import Server.Interfaces.ServerGameDispenser;

/**
 * Created by larhard on 25.05.14.
 */
public class StandardScenario extends Scenario {
    public StandardScenario() {
        add(ServerGameDispenser.class, StandardGameWatchdog.class);
    }
}
