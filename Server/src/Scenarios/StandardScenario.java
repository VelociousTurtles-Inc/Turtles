package Scenarios;

import GameWatchdogs.StandardGameWatchdog;
import Server.Interfaces.ServerGameDispenser;
import Utility.Scenario;

public class StandardScenario extends Scenario {
    public StandardScenario() {
        add(ServerGameDispenser.class, StandardGameWatchdog.class);
    }
}
