package Views;

import Utility.DebugWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    private static final long serialVersionUID = 7015181934501525828L;
    public final List<Point> positions = new ArrayList<>();
    public int size, maxTurtles;
    public String name;
    //path to image should be here

    public Board() {
    }

    public static Board readBoard(String boardName) {
        assert DebugWriter.write("Reading Board \"" + boardName + "\"");
        Board temp = new Board();
        try {
            //TODO, if anyone wants to change anything here, please do it on working code and
            //make sure it still works after the change.
            //InputStream fileIn = Board.class.getResourceAsStream("Resources/Boards/"+name);
            InputStream fileIn = new FileInputStream(System.getProperty("user.dir")+"/Client/src/Resources/Boards/"+boardName);
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
            System.out.println(name);
            //TODO, if anyone wants to change anything here, please do it on working code and
            //make sure it still works after the change.
            //OutputStream fileOut = new FileOutputStream(Board.class.getResource("Resources/Boards/"+name).getFile());
            OutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"/Client/src/Resources/Boards/"+name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch(IOException i) {
            assert false : "Board not serialized correctly";
        }
    }

}
