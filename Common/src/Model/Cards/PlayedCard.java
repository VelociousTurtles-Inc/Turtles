package Model.Cards;

import java.io.Serializable;

public class PlayedCard implements Serializable {
    private static final long serialVersionUID = -2731730149930907988L;

    private final Integer id;
    private final Integer color;

    public PlayedCard(Integer id, Integer color) {
        this.id = id;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public Integer getColor() {
        return color;
    }
}
