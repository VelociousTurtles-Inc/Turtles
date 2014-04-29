package Client.Adapters.Login;

import Models.Login.LoginData;

/**
 * po udanym zalogowaniu adapter trzyma jakiś token uwierzytelniający
 */

public interface LoginAdapter {
    public boolean submitLoginData(LoginData loginData);
}
