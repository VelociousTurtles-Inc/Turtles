package Models.Login;

public interface LoginData {
    public String getLogin();

    /**
     * @return najlepiej zahashowane hasło
     */
    public String getPassword();
}
