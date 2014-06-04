package Model;

public class Player{
    final String passHash;
    final String name;
    final PlayerInfo info;

    private Player(String passHash, String name) {
        this.passHash = hash(passHash);
        this.name = name;
        this.info = new PlayerInfo();
    }
    private String hash(String pass)
    {
        return pass;
    }
    public static Player Create(String name, String password) {
        Player result =  new Player(name,password);
        // Here goes call to DataSourceProvider, Also here goes updating PlayerInfo
        result.info.name = name;
        return result;
    }
}
