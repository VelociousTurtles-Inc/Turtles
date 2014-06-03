package Controllers;

import Chat.Message.Message;
import Chat.Message.NoSuchMessageException;
import Client.Interfaces.GameClient;
import Common.Interfaces.Event;
import Controllers.Interfaces.GameController;
import Enums.Colors;
import Main.Client;
import Model.Board.BoardGraph;
import Model.Cards.Card;
import Model.Turtles.Turtle;
import Server.Interfaces.PlayerService;
import Utility.DebugWriter;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class StandardGameController extends Thread implements GameController, GameClient {

    private Map<Integer, Card> normalCardsMap;
    private PlayerService playerService;

    private final List<Event> boardUpdateEvents = new LinkedList<>();
    private final List<Event> cardsUpdateEvents = new LinkedList<>();
    private final List<Event> winnerUpdateEvents = new LinkedList<>();
    private final List<Event> closeEvents = new LinkedList<>();
    private final List<Event> changeEvents = new LinkedList<>();

    private final List<Event> lockEvents = new LinkedList<>();
    private final List<Event> chatUpdateEvents = new LinkedList<>();

    private final AtomicBoolean locked = new AtomicBoolean();

    List<Integer> playerHand;
    private int playerOnMove;
    private LinkedList<Message> chatMessages = new LinkedList<>();
    private Colors playerColor;
    private Colors winner;

    private void clearEvents() {
        synchronized (boardUpdateEvents) {
            boardUpdateEvents.clear();
        }
        synchronized (cardsUpdateEvents) {
            cardsUpdateEvents.clear();
        }
        synchronized (closeEvents) {
            closeEvents.clear();
        }
        synchronized (lockEvents) {
            lockEvents.clear();
        }
    }

    public StandardGameController() throws RemoteException {
        normalCardsMap = new HashMap<>();

        locked.set(true);
    }

    public void updateChat() {
        synchronized (chatUpdateEvents) {
            for (Event up : chatUpdateEvents) {
                up.call();
            }
        }
    }

    @Override
    public String getChatLog() throws RemoteException {
        return Message.MessagesToText(chatMessages);
    }

    @Override
    public void getChatChanges() throws RemoteException, NoSuchMessageException {
        if(chatMessages.size() == 0)
            chatMessages = playerService.newChatMessages(0);
        else
            chatMessages.addAll(playerService.newChatMessages(chatMessages.get(chatMessages.size()-1).id));
    }

    @Override
    public void updateCards() {
        synchronized (cardsUpdateEvents) {
            for (Event up : cardsUpdateEvents) {
                up.call();
            }
        }
    }

    @Override
    public void updateBoards() {
        synchronized (boardUpdateEvents) {
            for (Event up : boardUpdateEvents) {
                up.call();
            }
        }
    }

//    public boolean checkForWinner() throws RemoteException {
//        BoardGraph myBoardGraph = gameService.getGameBoardGraph();
//
//        if(myBoardGraph.end.getTurtles().size() != 0) {
//            Turtle temp = myBoardGraph.end.getTurtles().get(myBoardGraph.end.getTurtles().size() - 1);
//            System.out.println("The " + Enums.asString(temp.getColor() + 1) + " turtle has won the game.");
//            return true;
//        } else {
//            return false;
//        }
//
//    }

    @Override
    public void postMessage(String text) throws RemoteException {
        playerService.postMessage(text);
    }
    @Override
    public Colors getTurtleColor()
    {
        return playerColor;
    }

    @Override
    public void start(PlayerService myService) throws RemoteException {
        playerService = myService;
        normalCardsMap = myService.getCardsMap();
        myService.setClient(this);
        playerColor = Colors.asColor(playerService.getTurtleColor());
        Client.scenario.invoke(GameController.class, this);
    }

    @Override
    public void playCard(int card) throws RemoteException {
        playerService.playCard(card);
    }

    @Override
    public void surrender() {
        // TODO: implement
        System.out.println("I surrendered!");
        try {
            leave();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerCloseEvent(Event closeEvent) {
        synchronized (closeEvents) {
            assert DebugWriter.write("Registering new Game View Close Event");
            closeEvents.add(closeEvent);
        }
    }

    @Override
    public void registerUpdateBoardEvent(Event updateBoardEvent) {
        synchronized (boardUpdateEvents) {
            assert DebugWriter.write("Registering new Update Board Event");
            boardUpdateEvents.add(updateBoardEvent);
            updateBoardEvent.call();
        }
    }

    @Override
    public void registerUpdateCardsEvent(Event updateCardEvent) {
        synchronized (cardsUpdateEvents) {
            assert DebugWriter.write("Registering new Update Cards Event");
            cardsUpdateEvents.add(updateCardEvent);
            updateCardEvent.call();
        }
    }

    @Override
    public void leave() throws RemoteException {
        // TODO real leave game
        playerService.leave();
    }

    private void closeViews() {
        synchronized (closeEvents) {
            for (Event event : closeEvents) {
                event.call();
            }
        }
    }

    @Override
    public List<Card> getCards() throws RemoteException {
        playerHand = playerService.getPlayerCards();
        List<Card> resultCards = new LinkedList<>();
        for(Integer i : playerHand) {
            resultCards.add(normalCardsMap.get(i));
        }
        return resultCards;
    }

    @Override
    public List<List<Integer>> getBoard() throws RemoteException {

        List<List<Integer>> result = new LinkedList<>();

        BoardGraph myBoardGraph = playerService.getGameBoardGraph();

        for(BoardGraph.Field f : myBoardGraph.starts) {
            LinkedList<Integer> turtlesIDs = new LinkedList<>();
            for (Turtle t : f.getTurtles())
                turtlesIDs.add(t.getColor());
            result.add(turtlesIDs);
        }

        BoardGraph.Field temp = myBoardGraph.starts.get(0);

        while(temp.getSuccessors().size() != 0) {
            LinkedList<Integer> turtlesIDs = new LinkedList<>();
            temp = temp.getSuccessors().get(0);
            for(Turtle t : temp.getTurtles())
                turtlesIDs.add(t.getColor());
            result.add(turtlesIDs);
        }

        return result;
    }

    @Override
    public void updateLock() {
        synchronized (locked) {
            try {
                locked.set(playerService.isLocked());
                for (Event ev : lockEvents) {
                    ev.call();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void registerLockingEvent(Event lockEvent) {
        synchronized (lockEvents) {
            lockEvents.add(lockEvent);
        }
    }

    @Override
    public void cardsPlayed() throws RemoteException {

    }

    @Override
    public void close() throws RemoteException {
        closeViews();
        clearEvents();
    }

    @Override
    public boolean isLocked() {
        return locked.get();
    }

    /*@Override
    public void cardsPlayed() throws RemoteException {
    }*/

    @Override
    public void ping() throws RemoteException {
    }

    @Override
    public void setPlayerOnMove(int playerOnMove) {
        this.playerOnMove = playerOnMove;
        for(Event change : changeEvents) {
            change.call();
        }
    }
    @Override
    public void registerWinnerUpdateEvent(Event winnerUpdateEvent) {
        synchronized (winnerUpdateEvents) {
            assert DebugWriter.write("Registering new Winner Update Event");
            winnerUpdateEvents.add(winnerUpdateEvent);
        }
    }

    @Override
    public void registerChangeMovingPlayerEvent(Event changeEvent) {
        changeEvents.add(changeEvent);
    }

    @Override
    public void registerChatUpdateEvent(Event updateChatEvent) {
        synchronized (chatUpdateEvents) {
            assert DebugWriter.write("Registering new Chat Update Event");
            chatUpdateEvents.add(updateChatEvent);
        }
        updateChatEvent.call();
    }

    @Override
    public List<String> getPlayers() throws RemoteException {
        return playerService.GetListOfPlayers();
    }
    
    @Override
    public void announceWinner(Integer winner) throws RemoteException {
        this.winner = Colors.asColor(winner);
        synchronized (winnerUpdateEvents) {
            for (Event event : winnerUpdateEvents) {
                event.call();
            }
        }
    }

    @Override
    public int getLastMoving() {
        return playerOnMove;
    }
    @Override
    public Colors getWinner() {
        return winner;
    }

}


