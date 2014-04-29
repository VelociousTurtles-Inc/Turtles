package Client.Adapters.GameCreator;

import Models.GameSelect.GameData;

public interface GameCreator {
    public void setTitle(String title);
    public void setMaxPlayers(int max_players);

    /**
     * zarejestruj grę w systemie
     */
    public void register();

    /**
     * @return registered ? game_id : null
     */
    public Integer getId();
    public GameData getGameData();
}
