package Models.GameSelect;

import Models.Common.Player;

import java.util.List;

public interface GameData {
    public int getId();
    public String getTitle();
    public int getCapacity();
    public int getPlayersCount();
    public List<? extends Player> getPlayers();
}
