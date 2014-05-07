package Adapters;

import Handlers.UpdateHandler;
import Model.Board.BoardGraph;
import Model.Cards.Card;
import Services.GameService;
import Views.GameView;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * sorry for JUnit3, but JUnit4 is spawning problems
 */

public class GameAdapterTest extends TestCase {

    class GoodException extends RuntimeException {
    }

    public void testUpdateEventTest() throws Exception {
        GameAdapter gameAdapter = new SimpleGameAdapter(Arrays.asList(new GameView() {
            private GameAdapter adapter;
            private String[] args;

            private BoardGraph boardGraph;
            private List<? extends Card> cards;

            @Override
            public void init(GameAdapter adapter, String[] args) {

                this.adapter = adapter;
                this.args = args;

                this.adapter.addUpdateBoardHandler(new UpdateHandler<BoardGraph>() {
                    @Override
                    public synchronized void update(BoardGraph data) {
                        boardGraph = data;
                    }
                });
                assertNotNull(boardGraph);

                this.adapter.addUpdatePlayerCardHandler(new UpdateHandler<List<? extends Card>>() {
                    @Override
                    public synchronized void update(List<? extends Card> data) {
                        cards = data;
                    }
                });
                assertNotNull(cards);

            }

            @Override
            public void close() {

            }
        }), new GameService(), new String[] {});
    }
}