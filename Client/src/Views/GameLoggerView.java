package Views;

import Adapters.GameAdapter;

import java.util.List;

/**
 * Created by main on 5/6/14.
 */
public class GameLoggerView implements GVCInterface {
    int numberOfBoard;
    GameAdapter myCont;


    public void init(int numberOfBoard, GameAdapter myCont) {
        this.numberOfBoard = numberOfBoard;
        this.myCont = myCont;
        System.out.println("New game with board No." + numberOfBoard
            + "and " + myCont + "as a controller.");
    }

    public void updateBoard(List<List<Integer>> updateForBoard) {
        System.out.println("New board state:");
        for(int i = 0; i < updateForBoard.size(); i++) {
            System.out.println("Rock number " + i + ":");
            for(Integer b : updateForBoard.get(i))
                System.out.printf("%d ", b);
            System.out.println();
        }
    }

    public void updateCard(int numberOfCard, int typeOfCard) {
        System.out.println("Card " + numberOfCard + "has changed to"
            + typeOfCard);
    }

}
