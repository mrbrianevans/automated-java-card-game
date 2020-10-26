import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    short setCardNumber;
    Card card;

    @Before
    public void setUp() throws Exception {
        this.setCardNumber = (short) Math.round(Math.random() * 100);
        this.card = new Card(this.setCardNumber);
    }

    @Test
    public void getValue() {
        assertEquals(this.card.getValue(), setCardNumber);
    }

    @Test
    public void testToString() {
        assertEquals(this.card.getValue() + "", setCardNumber + "");
    }
}