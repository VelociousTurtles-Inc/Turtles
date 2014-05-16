package Adapters.Interfaces;

import Views.Standard.GameChoosing.GameChoosingView;
import javafx.event.*;

/**
 * Created by michaziobro on 16.05.2014.
 */
public interface GameChoosingController {
    public void join();
    public void cancel();
    public void create();
    public void setClosingEvent(Event myClosingEvent);
}
