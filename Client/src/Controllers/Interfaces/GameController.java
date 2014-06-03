package Controllers.Interfaces;

import Common.Interfaces.Event;
import Enums.Colors;
import Model.Cards.Card;
import Server.Interfaces.PlayerService;

import java.rmi.RemoteException;
import java.util.List;

public interface GameController {
    Colors getTurtleColor();

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
    void registerChatUpdateEvent(Event chatUpdateEvent);

    List<String> getPlayers() throws RemoteException;
    Colors getWinner();

    String getChatLog() throws RemoteException;
    void postMessage(String a) throws RemoteException;

    int getLastMoving();

    String getLastCard();
}
