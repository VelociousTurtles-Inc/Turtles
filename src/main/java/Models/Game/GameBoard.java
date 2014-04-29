package Models.Game;

import java.util.List;

/**
 * Graf pól mapy
 */

public interface GameBoard {
    /**
     * @return lista pól za [id]
     */
    public List<Integer> nextOf(int id);
    /**
     * @return lista pól przed [id]
     */
    public List<Integer> prevOf(int id);
}
