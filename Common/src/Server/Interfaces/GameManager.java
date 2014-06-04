package Server.Interfaces;

import Chat.Message.Message;
import Model.Board.BoardGraph;
import Model.Cards.Card;
import Model.GameInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface GameManager extends Remote {
    // TODO server side GameManager interface

    public void playCard(int cardID, ServerPlayerService player) throws RemoteException;

    int getNextCard() throws RemoteException;

    void nextTurn() throws RemoteException;

    public Map<Integer, Card> getInGameCards() throws RemoteException;

    void removePlayer(WaiterService oldWaiter) throws RemoteException;

    void addPlayer(WaiterService newWaiter) throws RemoteException;

    public void startGame() throws RemoteException;
    public boolean isStarted() throws RemoteException;
    public boolean isFull() throws RemoteException;
    public GameInfo getGameInfo() throws RemoteException;
    void setId(int id) throws RemoteException;
    public void update() throws RemoteException;

    void cancel() throws RemoteException;

    List<Integer> getHand() throws RemoteException;

    void leave() throws RemoteException;

    void checkForZombies() throws RemoteException;

    void addZombie() throws RemoteException;

    List<Message> getChat() throws RemoteException;

    void addMessage(Message message) throws RemoteException;

    BoardGraph getBoardGraph() throws RemoteException;

    void updateBoard() throws RemoteException;

    List<String> GetListOfPlayers() throws RemoteException;

    void announceTurtleColors();
}
