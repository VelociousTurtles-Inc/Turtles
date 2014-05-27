package Views.FastInit;

import Controllers.Interfaces.LoginController;

import java.util.Random;

public class RandomLoginView {
    public RandomLoginView(LoginController loginController) {
        System.err.println(this.getClass());
        Random random = new Random();
        try {
            loginController.submit("User" + random.nextInt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
