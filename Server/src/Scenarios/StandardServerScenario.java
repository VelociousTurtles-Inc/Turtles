package Scenarios;

import GameWatchdogs.StandardGameWatchdog;
import Server.Interfaces.ServerGameDispenser;
import Utility.Scenario;

public class StandardServerScenario extends Scenario {
    public StandardServerScenario() {
        add(ServerGameDispenser.class, StandardGameWatchdog.class);
    }
}
