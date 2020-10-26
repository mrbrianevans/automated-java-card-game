
public class CardDeck {
    private final Card[] hand;
    private short cards;

    public CardDeck() {
        this.hand = new Card[5];
        this.cards = 0;
    }

    public void addCard(Card card) {
        this.hand[cards++] = card;
    }

    public void discardCard(Card card) {
        // add card to bottom of deck
        this.hand[3] = card;
    }

    public Card pickUpCard() {
        // return the card on the top of the deck
        Card onTopOfDeck = this.hand[0];
        System.arraycopy(this.hand, 1, this.hand, 0, 4);
        return onTopOfDeck;
    }

}
