import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CardDeck {
    private final short deckNumber;
    private short cards;
    private Card[] hand;

    public CardDeck(short deckNumber) {
        this.deckNumber = deckNumber;
        this.hand = new Card[5];
        this.cards = 0;
    }

    public void addCard(Card card) {
        this.hand[cards++] = card;
    }

    public void discardCard(Card card) {
        // add card to bottom of deck
        // check for the last empty position in the deck, to assign discarded card to
        if (this.hand[3] == null) this.hand[3] = card;
        else this.hand[4] = card;
    }

    public Card pickUpCard() {
        // return the card on the top of the deck
        Card onTopOfDeck = this.hand[0];
        Card[] newHand = new Card[5];
        System.arraycopy(this.hand, 1, newHand, 0, 4);
        this.hand = newHand;
        return onTopOfDeck;
    }

    public void writeContentsToFile() throws IOException {
        String path = "gameOutput" + File.separator + "deck" + deckNumber + "_output.txt";
        StringBuilder output = new StringBuilder("deck" + deckNumber + " contents: ");
        for (Card card : hand) {
            if (card != null) output.append(" ").append(card.getValue());
        }
        File f = new File(path);
        f.getParentFile().mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        if (!f.createNewFile()) {
            writer.write("");
        }
        writer = new BufferedWriter(new FileWriter(path, true));
        writer.write(output.toString());
        writer.newLine();
        writer.close();
    }

}
