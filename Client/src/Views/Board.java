package Views;

import Utility.DebugWriter;

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
    //path to image should be here


    public Board() {
    }


    public static Board readBoard(String nname) {
        assert DebugWriter.write("Reading Board \"" + nname + "\"");
        Board temp = new Board();
        try {
            InputStream fileIn = Board.class.getResourceAsStream("Resources/Boards/"+nname);
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
        assert DebugWriter.write("Saving Board \"" + name + "\"");

        try {
            OutputStream fileOut = new FileOutputStream(Board.class.getResource("Resources/Boards/"+name).getFile());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch(IOException i) {
            assert false : "Board not serialized correctly";
        }
    }

}
