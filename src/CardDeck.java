import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CardDeck - handling of the initialisation of the cardDeck as well as all of the methods
 * the card will use. These include addCard to deck, discardCard to bottom of deck,
 * pickUpCard from deck and writeContentsToFile.
 *
 * @author 690024916 & 690023094
 * @version 1.0
 */

public class CardDeck {
    private final short deckNumber;
    private short cards;
    private Card[] hand;

    public CardDeck(short deckNumber) {
        this.deckNumber = deckNumber;
        this.hand = new Card[5];
        this.cards = 0;
    }

    /**
     * Add card to current hand. Cards will increment by one.
     * @param card is the object storing the players card.
     */
    public void addCard(Card card) {
        this.hand[cards++] = card;
    }

    /**
     * discardCard - Add card to bottom of deck, check for the last empty position in the
     * deck, to assign discarded card to.
     * @param card is the object representing the card a player is discarding.
     */
    public void discardCard(Card card) {
        // Is the array index empty (null) before adding card
        if (this.hand[3] == null) this.hand[3] = card;
        else this.hand[4] = card;
    }

    /**
     * pickUpCard - Returns the card on the top of the deck when this method is called.
     * @return the card on the top of the deck.
     */
    public Card pickUpCard() {
        // Return the card on the top of the deck
        Card onTopOfDeck = this.hand[0];
        Card[] newHand = new Card[5];
        System.arraycopy(this.hand, 1, newHand, 0, 4);
        this.hand = newHand;
        return onTopOfDeck;
    }

    /**
     * writeContentsToFile() will write actions within games (e.g. pickUp card) to .txt file.
     * Each player has a unique file where all of their relevant gae information is stored.
     *
     * @throws IOException with null as the error string message
     */
    public void writeContentsToFile() throws IOException {
        // File name generated using deckNumber attribute to ensure unique file directory.
        String path = "gameOutput" + File.separator + "deck" + deckNumber + "_output.txt";
        StringBuilder output = new StringBuilder("deck" + deckNumber + " contents: ");
        for (Card card : hand) {
            if (card != null) output.append(" ").append(card.getValue());
        }
        File f = new File(path);
        // mkdirs() method to create new directory
        f.getParentFile().mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        if (!f.createNewFile()) {
            writer.write("");
        }
        // BufferedWriter to write to and edit file
        writer = new BufferedWriter(new FileWriter(path, true));
        writer.write(output.toString());
        writer.newLine();
        writer.close();
    }

}
