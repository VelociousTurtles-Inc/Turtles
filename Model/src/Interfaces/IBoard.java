package Interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mz18 on 15/05/14.
 */
public interface IBoard extends Serializable {
    List<List<Integer>> asSimpleList();
}
