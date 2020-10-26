import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Player {

    private final Card[] hand;
    private final short playerNumber;
    private short cards;
    private final String path;

    public Player(int playerNumber) throws IOException {
        this.hand = new Card[5];
        this.cards = 0;
        this.playerNumber = (short) (playerNumber + 1);
        this.path = "playerOutput" + File.separator + "player" + this.playerNumber + ".txt";
        File f = new File(this.path);
        f.getParentFile().mkdirs();
        if (!f.createNewFile()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("");
        }

    }

    public short getPlayerNumber() {
        return playerNumber;
    }

    private void writeToPlayerFile(String output) throws IOException {
        // open, write to, and close players file
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        writer.write(output);
        writer.newLine();
        writer.close();
    }

    private String handStringRepr() {
        return this.hand[0] + " " + this.hand[1] + " " + this.hand[2] + " " + this.hand[3];
    }

    public void addCard(Card card) throws IOException {
        this.hand[cards++] = card;
        if (cards == 4) {
            writeToPlayerFile("Player " + this.playerNumber + " initial hand " + handStringRepr());
        }
    }

    /**
     * @param pickUp the card picked up from the deck on players left
     * @return card put down (discarded) by player. To be put at bottom of deck on players right.
     */
    public Card takeTurn(Card pickUp, int discardDeckNumber, int pickUpDeckNumber) throws IOException {
        boolean isPreferred = true;
        Random picker = new Random();
        Card currentCard;
        short swapIndex;
        do {
            swapIndex = (short) picker.nextInt(4);
            currentCard = this.hand[swapIndex];
            if (currentCard.getValue() != playerNumber) isPreferred = false;
        } while (isPreferred);

        // Increment deck number for text file
        pickUpDeckNumber++;
        discardDeckNumber++;

        this.hand[swapIndex] = pickUp; // add the picked up card to hand
        if (pickUp == null) throw new AssertionError("Picked up card is null");
        System.out.println("Player " + this.playerNumber + " draws " + pickUp.getValue() + " from deck " + pickUpDeckNumber);
        System.out.println("Player " + this.playerNumber + " discards " + currentCard.getValue() + " to deck " + discardDeckNumber);
        System.out.println("Player " + this.playerNumber + " current hand " + handStringRepr());

        writeToPlayerFile("Player " + this.playerNumber + " draws " + pickUp.getValue() + " from deck " + pickUpDeckNumber);
        writeToPlayerFile("Player " + this.playerNumber + " discards " + currentCard.getValue() + " to deck " + discardDeckNumber);
        writeToPlayerFile("Player " + this.playerNumber + " current hand " + handStringRepr());

        return currentCard;
    }

    public boolean hasWon() throws IOException {
        Card firstCard = this.hand[0];
        int counter = 0;
        for (Card card :
                this.hand) {
            if (counter++ == 4) break;
            if (card == null)
                throw new AssertionError("card in array loop is null. Hand: " + handStringRepr() + "; card: " + counter);
            if (card.getValue() != firstCard.getValue()) return false;
        }
        return true;
    }
}
