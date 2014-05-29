package Views;

public class BoardBootstrap {

    public static Board createSampleBoard() {
        Board temp = new Board();

        /**
         * start positions
         */
        temp.positions.add(new Point(60, 120));
        temp.positions.add(new Point(45, 170));
        temp.positions.add(new Point(60, 220));
        temp.positions.add(new Point(45, 270));
        temp.positions.add(new Point(60, 320));
        temp.maxTurtles = temp.positions.size();

        /**
         * other positions
         */
        temp.positions.add(new Point(170, 340));
        temp.positions.add(new Point(280, 290));
        temp.positions.add(new Point(380, 190));
        temp.positions.add(new Point(500, 130));
        temp.positions.add(new Point(610, 150));
        temp.positions.add(new Point(720, 200));
        temp.positions.add(new Point(840, 220));

        /**
         * grave position
         */
        temp.positions.add(new Point(600, 400));

        temp.size = temp.positions.size();

        temp.name = "sampleBoard";

        return temp;
    }

}
