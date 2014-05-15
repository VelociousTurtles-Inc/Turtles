package Model.Cards;

import java.io.Serializable;

public class CardInfo implements Serializable {
    private static final long serialVersionUID = 7942909486684041875L;

    final public String type;
    public String getType() {
        return type;
    }

    final public Integer ID;
    final public Integer color;
    public Integer getColor() {
        return color;
    }

    CardInfo(String type, int id, int color)
    {
        this.type = type;
        this.ID = id;
        this.color = color;
    }

}
