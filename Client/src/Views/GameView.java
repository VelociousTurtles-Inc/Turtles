package Views;

import Adapters.GameAdapter;

/**
 * Created by larhard on 05.05.14.
 */
public interface GameView {
    public void init(GameAdapter adapter, String[] args);
    public void close();
}
