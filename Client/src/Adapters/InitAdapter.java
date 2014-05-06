package Adapters;

import Views.SimpleGameView;

import java.util.Arrays;

/**
 * Created by larhard on 05.05.14.
 */
public class InitAdapter {
    public static void main(String[] args) {
        GameAdapter gameAdapter = new SimpleGameAdapter(Arrays.asList(new SimpleGameView()), args);
    }
}
