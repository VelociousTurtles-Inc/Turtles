package Views;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by main on 5/10/14.
 */
public class Board implements Serializable {

    public List<Point> startPositions = new ArrayList<Point>();
    public List<Point> positions = new ArrayList<Point>();
    public int size, maxTurtles;
    public String name;
    //path to image


    public Board() {
    }


    public static Board readBoard(String nname) {
        Board temp = new Board();
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir")+"/Client/src/Views/Boards/"+nname);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp = (Board) in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException i) {
            assert false : "Board not serialized correctly";
            i.printStackTrace();
        } catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return temp;
    }


    public void saveBoard() {
        System.out.println(System.getProperty("user.dir"));

        try {
            FileOutputStream fileOut =
                    new FileOutputStream(System.getProperty("user.dir")+"/Client/src/Views/Boards/"+name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch(IOException i) {
            assert false : "Board not serialized correctly";
        }
    }

}
