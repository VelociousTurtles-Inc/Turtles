package Adapters.Interfaces;


import Model.Cards.Card;
import Server.Interfaces.PlayerService;

import java.util.List;

/**
 * Created by mz18 on 7/05/14.
 */
public interface GameController {
    public void start(PlayerService myService) throws Exception;

    void playCard(int card) throws Exception;
    void surrender();

    void registerUpdateBoardEvent(Event updateBoardEvent);
    void registerUpdateCardsEvent(Event updateCardEvent);

    List<Card> getCards() throws Exception;
    List<List<Integer>> getBoard() throws Exception;

    void registerLockingEvent(Event lockingEvent);

    void registerUnlockingEvent(Event unlockingEvent);

    boolean isLocked();
}
