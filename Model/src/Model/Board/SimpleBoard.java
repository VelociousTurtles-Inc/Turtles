package Model.Board;

import Interfaces.IBoard;
import Model.Utility.Utility;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Maciej on 2014-05-05.
 */
public class SimpleBoard implements IBoard {
    List<List<Integer>> myFields;
    public SimpleBoard()
    {
        myFields = new LinkedList<>();
        for(int i = 0; i <= 7; i++) {
            myFields.add(new LinkedList<Integer>());
        }
        for(int i = 1; i<=5; i++) {
            myFields.get(0).add(i);
        }
    }
    public List<List<Integer>> asSimpleList() {
        return myFields;
    }
}
