import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CardGame - initialises threads for all players. Also controls the logic of the game
 * and specifies the setting up of all the cards (through distributing of cards to players).
 * This class also will inform the relevant methods/threads when a player has won.
 *
 * @author 690024916 & 690023094
 * @version 1.0
 */
public class CardGame {
    public static void main(String[] args) {
        try {
            // Request input from command line.
            System.out.println("Enter the number of players in the game: ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(in.readLine());
            System.out.println("Enter the filename for a pack of cards: ");
            String filename = in.readLine();

            playGame(n, filename);

        } catch (IOException e) {
            System.out.println("exception: " + e);
        }
    }

    /**
     * This is the main game logic code
     *
     * @param n        number of players in game
     * @param filename name of file containing the cards to play the game with
     * @throws IOException if there is an error reading or writing to file
     */
    public static void playGame(int n, String filename) throws IOException {
        Pack pack = new Pack(filename, n);
        Card[] cardsInPack = pack.getCards();
        Player[] players = new Player[n];
        CardDeck[] decks = new CardDeck[n];

        for (short i = 1; i <= n; i++) {
            players[i - 1] = new Player(i);
            // Creating thread because Player extends Thread.
            players[i - 1].start(); // start each player thread
            decks[i - 1] = new CardDeck(i);
        }

        // Distribute the cards to players.
        for (int i = 0; i < 4 * n; i++) {
            players[i % n].addCard(cardsInPack[i]);
        }
        // Distribute the cards to decks.
        for (int i = 4 * n; i < 8 * n; i++) {
            decks[i % n].addCard(cardsInPack[i]);
        }

        boolean isWinner = false;

        // this checks if any player has won immediately before the game starts
        for (Player player :
                players) {
            if (player.hasWon()) {
                System.out.println("Player " + player.getPlayerNumber() + " has won the game");
                isWinner = true;
            }
        }
        short winner = -1;
        int turns = 0;
        while (!isWinner) {
            int playersTurn = turns++ % n;
            int discardToDeck = (playersTurn + 1) % n;
            int pickUpFromDeck = playersTurn;

            // synchronized block used for thread safety
            synchronized (players[playersTurn]) {
                /* The below print statement shows which thread each player runs in */
//                System.out.printf("player %d is running on %s%n", playersTurn + 1, players[playersTurn].getName());

                decks[discardToDeck].discardCard(
                        players[playersTurn].takeTurn(
                                decks[pickUpFromDeck].pickUpCard(), discardToDeck, pickUpFromDeck)
                );
            }

            // checks if player has won at the end of every turn
            if (players[playersTurn].hasWon()) {
                isWinner = true;
                winner = players[playersTurn].getPlayerNumber();
            }
        }

        // Procedure for when there is a winner.
        // Inform all players of winner.
        // Inform decks of end of game.
        for (short i = 0; i < n; i++) {
            synchronized (players[i]) {
                players[i].informPlayerHasWon(winner);
            }
            decks[i].writeContentsToFile();
        }

    }
}
