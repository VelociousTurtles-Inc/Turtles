package Adapters;

import Adapters.Interfaces.Event;
import Adapters.Interfaces.GameChoosingController;
import Views.Standard.GameChoosing.GameChoosingView;

/**
 * Created by michaziobro on 16.05.2014.
 */
public class StandardGameChoosingController implements GameChoosingController {
    public StandardGameChoosingController() {
        new GameChoosingView(this);
    }

    Event endIt;

    @Override
    public void join() {
        //tu odpalimy controllera od dochodzenia do gry stworzonej przez kogoś tam
    }

    @Override
    public void cancel() {
        endIt.call();
    }

    @Override
    public void create() {
        //tu odpalamy controllera od tworzenia gier
    }

    @Override
    public void setClosingEvent(Event myClosingEvent) {
        this.endIt = myClosingEvent;
    }
}
