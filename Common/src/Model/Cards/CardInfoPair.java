package Model.Cards;

public class CardInfoPair {
    public final Integer key;
    public final CardInfo value;

    public CardInfoPair(Integer key, CardInfo value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public CardInfo getValue() {
        return value;
    }
}
