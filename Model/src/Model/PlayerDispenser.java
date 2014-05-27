package Model;

import java.util.Collection;
import java.util.HashSet;

public class PlayerDispenser {
    private final Collection<Player> container;

    public PlayerDispenser() {
        container = new HashSet<>();
        loadInitialState();
    }

    private void loadInitialState() {
        // here goes database connection/deserialzation/whatever
        container.add(Player.Create("Example","password"));
    }

}
