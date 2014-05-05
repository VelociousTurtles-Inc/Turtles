package Model.Cards;

/*
    An enum listing possible card types. To add another card type, modify this enum to register it
 */
public enum CardType {

    SimpleForward
    {
        @Override
        public String toString()
        {
            return "Idz do przodu";
        }
        public String getDescription()
        {
            return "Porusza żolwia we wskazanymk kolorze o jedno pole do przodu";
        }
    };
    abstract String getDescription();
}
