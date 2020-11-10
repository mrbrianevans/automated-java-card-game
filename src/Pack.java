import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Pack class handles the creation of the packs of cards within the game. The pack is provided by the user of
 * this application and must contain 8n cards where n is the number of players. Each player is distributed 4 cards
 * in a round robin fashion from the pack at the start of the game.
 * <p>
 * A valid input pack is a pain text file, each row contains a single non-negative integer value, has 8n rows.
 *
 * @author 041595 & 050744
 * @version 1.0
 */

public class Pack {

    private final Card[] cards;

    public Pack(String filename, int n) throws IOException {
        this.cards = new Card[8 * n];
        try {
            File packFile = new File(filename);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(packFile));
            String newline;
            int arrayIndex = 0;
            int expectedNumberOfCards = 8 * n;
            while ((newline = bufferedReader.readLine()) != null) {
                if (arrayIndex == 8 * n) {
                    System.out.println("Too many cards in pack. \n" +
                            "Expected " + expectedNumberOfCards + " cards for " + n + " players");
                    System.exit(1);
                } else {
                    this.cards[arrayIndex++] = new Card((short) Integer.parseInt(newline));
                }
            }
            int numberOfCards = arrayIndex;
            if (numberOfCards < expectedNumberOfCards) {
                // Error handling for pack size.
                System.out.println("Too few cards in pack\n" +
                        "Found " + numberOfCards + " cards for " + n + " players. Expected " + expectedNumberOfCards);
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            // if Integer.parseInt fails
            System.out.println("The pack file of cards does not contain only integers");
        }

    }

    public Card[] getCards() {
        return cards;
    }
}
