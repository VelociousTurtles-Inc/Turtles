package Client.Adapters.Common;

/**
 * Utrzymuje połączenie z serwerem (jeśli takowy oczywiście istnieje, inaczej symuluje jego egzystencję).
 */

public interface MainAdapter {
    public boolean isConnected();

    /**
     * exit
     */
    public void disconnect();

    /**
     * connect to server
     */
    public void connect(); // throws SomeCustomExceptions
}
