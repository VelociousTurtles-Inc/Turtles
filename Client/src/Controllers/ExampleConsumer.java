package Controllers;

import Services.GameInfo;
import Services.GameServiceService;

import java.util.List;

/**
 * Created by Maciej on 2014-04-24.
 */
public class ExampleConsumer {
    public static void main(String[] args) {
        Services.ExampleService service = new Services.ExampleServiceService().getExampleServicePort();
        //invoke business method
        System.out.println(service.hello());

        Services.GameService Game = new GameServiceService().getGameServicePort();
        List<GameInfo> tmp  = Game.gameList();
        System.out.println(tmp);
        for (GameInfo inf : tmp) System.out.println(inf.getId());

    }
}
