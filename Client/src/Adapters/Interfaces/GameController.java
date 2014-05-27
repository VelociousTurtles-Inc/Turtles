package Adapters.Interfaces;


import Common.Interfaces.Event;
import Enums.Colors;
import Model.Cards.Card;
import Server.Interfaces.PlayerService;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by mz18 on 7/05/14.
 */
public interface GameController {
    public void start(PlayerService myService) throws RemoteException;

    void playCard(int card) throws RemoteException;
    void surrender();

    void registerUpdateBoardEvent(Event updateBoardEvent);
    void registerUpdateCardsEvent(Event updateCardEvent);

    void leave() throws RemoteException;

    List<Card> getCards() throws RemoteException;
    List<List<Integer>> getBoard() throws RemoteException;

    void registerLockingEvent(Event lockingEvent);

    boolean isLocked();

    void registerCloseEvent(Event closeEvent);

    void registerChangeMovingPlayerEvent(Event changeEvent);
    void registerWinnerUpdateEvent(Event winnerUpdateEvent);

    List<String> getPlayers() throws RemoteException;
    Colors getWinner();

    int getLastMoving();
}
