package Model.Cards;

import java.io.Serializable;

public class CardInfoPair implements Serializable {
    private static final long serialVersionUID = 1417800739846920137L;

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
