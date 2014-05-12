package Adapters.Interfaces;

import ServicesTypes.CardInfo;

import java.util.List;

/**
 * Created by mz18 on 7/05/14.
 */
public interface GameController {
    void playCard(int card);
    void surrender();

    void registerUpdateBoardEvent(Event updateBoardEvent);
    void registerUpdateCardsEvent(Event updateCardEvent);

    List<CardInfo> getCards();
    List<List<Integer>> getBoard();
}
