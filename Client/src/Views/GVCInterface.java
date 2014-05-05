package Views;

import java.util.List;

/**
 * Created by michaziobro on 05.05.2014.
 */
public interface GVCInterface {
    public void init(int numberOfBoard, ControllerInterface myCont); // Dobra, to jest dość przyszłościowe, jak już plansz nie będzie wklepana na sztywno
    public void updateBoard(List<List<Integer>> updateForBoard); // Update planszy, każde pole liczone jest jako stos żółwi, kolejność musi się zgadzać z kolejnością współrzędnych pól
    public void updateCard(int numberOfCard, int typeOfCard); // Update wybranej karty, używany po wykonanym ruchu i otrzymaniu od modelu odpowiedzi co do wylosowanej karty
}
