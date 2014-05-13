package Views;

/**
 * Created by main on 5/10/14.
 */
public class BoardBootstrap {

    public static Board createSampleBoard() {
        Board temp = new Board();

        //the first position is not used because the GUI designer counts from 1
        temp.startPositions.add(new Point(42, 42));

        temp.startPositions.add(new Point(60, 120));
        temp.startPositions.add(new Point(45, 170));
        temp.startPositions.add(new Point(60, 220));
        temp.startPositions.add(new Point(45, 270));
        temp.startPositions.add(new Point(60, 320));
        temp.maxTurtles = temp.startPositions.size();

        //the first position is not used because the GUI designer counts from 1
        temp.positions.add(new Point(42, 42));

        temp.positions.add(new Point(170, 340));
        temp.positions.add(new Point(280, 290));
        temp.positions.add(new Point(380, 190));
        temp.positions.add(new Point(500, 130));
        temp.positions.add(new Point(610, 150));
        temp.positions.add(new Point(720, 200));
        temp.positions.add(new Point(840, 220));
        temp.size = temp.positions.size();

        temp.name = "sample board";

        return temp;
    }

}
