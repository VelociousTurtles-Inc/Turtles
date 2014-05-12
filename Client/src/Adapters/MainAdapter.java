package Adapters;

import ServicesTypes.GameService;


/**
 * Created by larhard on 05.05.14.
 */

/**
 * Created by larhard on 05.05.14.
 */
public class MainAdapter {
    public static void main(String[] args) {

        final GameService gameService = new ServicesTypes.GameServiceService().getGameServicePort();
    }
}

