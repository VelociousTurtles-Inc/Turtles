package Client.Adapters.GameSelect;

import Models.GameSelect.GameData;

import java.util.List;

public interface GameSelectAdapter {
    public List<? extends GameData> getGames();

    /**
     * @return czy się powiodło
     */
    public boolean chooseGame(int game_id);
}
