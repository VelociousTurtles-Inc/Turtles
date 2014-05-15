package Adapters.Interfaces;


import Model.Cards.CardInfo;

import java.util.List;

/**
 * Created by mz18 on 7/05/14.
 */
public interface GameController {
    void playCard(int card) throws Exception;
    void surrender();

    void registerUpdateBoardEvent(Event updateBoardEvent);
    void registerUpdateCardsEvent(Event updateCardEvent);

    List<CardInfo> getCards() throws Exception;
    List<List<Integer>> getBoard() throws Exception;
}
