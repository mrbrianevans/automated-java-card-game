/**
 * Card constructors. This class's sole job is to initialise
 * the newly created object and to have an instance method to
 * return objects.
 *
 * @author 041595 & 050744
 * @version 1.0
 */
public class Card {
    // Each card has a face value of a non-negative integer
    private final short value;

    public Card(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return value == card.value;
    }

}
