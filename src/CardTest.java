import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing main functionality of the Card. Including a method to test the value of the card, and the toString
 * value.
 *
 * @author 041595 & 050744
 * @version 1.0
 */

public class CardTest {
    short setCardNumber;
    Card card;

    @Before
    public void setUp() {
        this.setCardNumber = (short) Math.round(Math.random() * 100);
        this.card = new Card(this.setCardNumber);
    }

    @Test
    public void getValue() {
        assertEquals(this.card.getValue(), setCardNumber);
    }

    @Test
    public void testToString() {
        assertEquals(this.card.toString(), setCardNumber + "");
    }

    @Test
    public void testEquals() {
        //tests that the equals method compares the value of the card
        assertEquals(this.card, new Card(this.setCardNumber));
        assertNotEquals(this.card, new Card((short) (this.setCardNumber + 1)));
    }
}
