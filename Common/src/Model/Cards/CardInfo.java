package Model.Cards;

public class CardInfo {
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
