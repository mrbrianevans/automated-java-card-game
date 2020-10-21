import java.util.Random;

public class Player {
    private final Card[] hand;
    private final short playerNumber;
    private short cards;

    public Player(int playerNumber) {
        this.hand = new Card[5];
        this.cards = 0;
        this.playerNumber = (short) playerNumber;
    }

    public short getPlayerNumber() {
        return playerNumber;
    }

    private void writeToPlayerFile(String output) {
        // open, write to, and close players file
    }

    private String handStringRepr() {
        return this.hand[0] + " " + this.hand[1] + " " + this.hand[2] + " " + this.hand[3];
    }

    public void addCard(Card card) {
        this.hand[cards++] = card;
        if (cards == 4) {
            writeToPlayerFile("Player " + this.playerNumber + " initial hand " + handStringRepr());
        }
    }

    /**
     * @param pickUp the card picked up from the deck on players left
     * @return card put down (discarded) by player. To be put at bottom of deck on players right.
     */
    public Card takeTurn(Card pickUp, int discardDeckNumber, int pickUpDeckNumber) {
        boolean isPreferred = true;
        Random picker = new Random();
        Card currentCard;
        short swapIndex;
        do {
            swapIndex = (short) picker.nextInt(4);
            currentCard = this.hand[swapIndex];
            if (currentCard.getValue() != playerNumber) isPreferred = false;
        } while (isPreferred);

        this.hand[swapIndex] = pickUp; // add the picked up card to hand

        writeToPlayerFile("Player " + this.playerNumber + " drew " + pickUp.getValue() + " from deck " + pickUpDeckNumber);
        writeToPlayerFile("Player " + this.playerNumber + " discards " + currentCard.getValue() + " to deck " + discardDeckNumber);
        writeToPlayerFile("Player " + this.playerNumber + " current hand " + handStringRepr());

        return currentCard;
    }

    public boolean hasWon() {
        Card firstCard = this.hand[0];
        for (Card card :
                this.hand) {
            if (card.getValue() != firstCard.getValue()) return false;
        }
        return true;
    }
}
