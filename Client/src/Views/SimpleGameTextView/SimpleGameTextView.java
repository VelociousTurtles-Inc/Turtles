package Views.SimpleGameTextView;

import Adapters.GameAdapter;
import Handlers.UpdateHandler;
import Views.GameView;
import ServicesTypes.BoardGraph;

import java.util.List;
import java.util.Scanner;

/**
 * Created by larhard on 06.05.14.
 */
public class SimpleGameTextView implements GameView {
    private GameAdapter adapter;
    private String[] args;

    private List<Integer> cards;
    private BoardGraph boardGraph;

    @Override
    public void init(GameAdapter adapter, String[] args) {
        this.adapter = adapter;
        this.args = args;

        this.adapter.addUpdateBoardHandler(new UpdateHandler<BoardGraph>() {
            @Override
            public synchronized void update(BoardGraph data) {
                System.out.println(" ::: Board Graph updated :::\n" + data);
                boardGraph = data;
            }
        });
        assert boardGraph != null;

        this.adapter.addUpdatePlayerCardHandler(new UpdateHandler<List<Integer>>() {
            @Override
            public synchronized void update(List<Integer> data) {
                System.out.println(" ::: Player Hand updated :::\n" + data);
                cards = data;
            }
        });
        assert cards != null;
    }

    @Override
    public void close() {
    }

    class InputThread extends Thread {
        Scanner scanner = new Scanner(System.in);

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                System.out.println("Play Card");
                int card = scanner.nextInt();
                adapter.playCard(cards.get(card));
            }
        }
    }
}
