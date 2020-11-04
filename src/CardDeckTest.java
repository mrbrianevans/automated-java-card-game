import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * CardDeckTest will test all the main methods in CardDeck to ensure functionality of the program.
 * Methods include testAddClass(), testFirstDeck() and testOtherDecks() as well as a setUp() method
 * to initialise to cards.
 *
 *
 * @author 690024916 & 690023094
 * @version 1.0
 */

public class CardDeckTest {
    private CardDeck cardDeck;

    @Before
    public void setUp() throws Exception {
        cardDeck = new CardDeck((short) 1);
        cardDeck.addCard(new Card((short) 1));
        cardDeck.addCard(new Card((short) 2));
        cardDeck.addCard(new Card((short) 3));
        cardDeck.addCard(new Card((short) 4));
    }

    @Test
    public void testAddCard() {
        // Create one CardDeck and 5 Card's
        CardDeck addCardDeck = new CardDeck((short) 1);
        addCardDeck.addCard(new Card((short) 5));
    }

    /**
     * Tests the scenario of deck 1, which will have 3 or 4 cards at various points.
     */
    @Test
    public void testFirstDeck() {
        assertEquals(1, cardDeck.pickUpCard().getValue());
        cardDeck.discardCard(new Card((short) 5));
        assertEquals(2, cardDeck.pickUpCard().getValue());
        assertEquals(3, cardDeck.pickUpCard().getValue());
        assertEquals(4, cardDeck.pickUpCard().getValue());
        assertEquals(5, cardDeck.pickUpCard().getValue());
    }

    /**
     * Tests the scenario of decks other than deck 1, which will have 4 or 5 cards at various points.
     */
    @Test
    public void testOtherDecks() {
        cardDeck.discardCard(new Card((short) 5));
        assertEquals(1, cardDeck.pickUpCard().getValue());
        assertEquals(2, cardDeck.pickUpCard().getValue());
        assertEquals(3, cardDeck.pickUpCard().getValue());
        assertEquals(4, cardDeck.pickUpCard().getValue());
        assertEquals(5, cardDeck.pickUpCard().getValue());
    }
}
