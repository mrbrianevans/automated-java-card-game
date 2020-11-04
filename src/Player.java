import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Player class extends Thread - this class handles the creation of a player and all of the methods that
 * a player will need and use to ensure the game runs correctly. These include takeTurn, addCard hasWon etc.
 * There is n amount of players - each player holds 4 cards which are distributed from the pack at the start
 * of the game.
 *
 * @author 690024916 & 690023094
 * @version 1.0
 */

public class Player extends Thread {

    private final Card[] hand;
    private final short playerNumber;
    private short cards;
    private final String path;

    /**
     * Creation of a player and all of the attributes the player will store; hand, cards, playerNumber and path.
     * This method also ensure that a file directory to store game information on is created.
     *
     * @param playerNumber unique identifiable number for each player
     * @throws IOException description null, when there is failed or interrupted IO operations.
     */
    public Player(short playerNumber) throws IOException {
        System.out.println("Created Player " + playerNumber + " Thread id=" + currentThread().getId() + currentThread().getName());
        this.hand = new Card[5];
        this.cards = 0;
        this.playerNumber = playerNumber;
        this.path = "gameOutput" + File.separator + "player" + this.playerNumber + ".txt";
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

    /**
     * Open, write to, and close players file
     * @param output message to be written to file
     * @throws IOException description null, when there is failed or interrupted IO operations.
     */
    private void writeToPlayerFile(String output) throws IOException {
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
     * takeTurn method handles all of the player actions when it is there turn in the game.
     *
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

        // Increment deck number for text file.
        pickUpDeckNumber++;
        discardDeckNumber++;

        this.hand[swapIndex] = pickUp; // Add the picked up card to hand.
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

    /**
     * Informs the player that the game has ended because a player has won.
     *
     * @param playerNumber the identifier of the player who has won the game.
     */
    public void informPlayerHasWon(short playerNumber) throws IOException {
        StringBuilder winOutput = new StringBuilder();
        // check if player number is self
        if (playerNumber == this.playerNumber) {
            winOutput.append("player ").append(playerNumber).append(" wins");
        } else {
            winOutput.append("player ").append(playerNumber).append(" has informed player ")
                    .append(this.playerNumber).append(" that player ").append(playerNumber).append(" has won");
        }
        writeToPlayerFile(winOutput.toString());
        writeToPlayerFile("player " + this.playerNumber + " exits");

        StringBuilder handOutput = new StringBuilder("player ").append(this.playerNumber).append(' ');
        if (playerNumber == this.playerNumber) {
            handOutput.append("final ");
        }
        handOutput.append("hand ").append(handStringRepr());
        writeToPlayerFile(handOutput.toString());
    }
}
