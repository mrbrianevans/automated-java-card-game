import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
public class CardGame {
    public static void main(String[] args) throws IOException {
        // request from command line
        System.out.println("Enter the number of players in the game: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        System.out.println("Enter the filename for a pack of cards: ");
        String filename = in.readLine();
        try {
            Pack pack = new Pack(filename, n);
            Card[] cardsInPack = pack.getCards();
            Player[] players = new Player[n];
            CardDeck[] decks = new CardDeck[n];

            for (short i = 1; i <= n; i++) {
                players[i - 1] = new Player(i); // creating thread because Player extends Thread
                players[i - 1].start();
                decks[i - 1] = new CardDeck(i);
            }

            // distribute the cards to players
            for (int i = 0; i < 4 * n; i++) {
                players[i % n].addCard(cardsInPack[i]);
            }
            // distribute the cards to decks
            for (int i = 4 * n; i < 8 * n; i++) {
                decks[i % n].addCard(cardsInPack[i]);
            }

            boolean isWinner = false;

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

                synchronized (players[playersTurn]) {
                    System.out.println("players thread = " + players[playersTurn].getName());
                    decks[discardToDeck].discardCard(
                            players[playersTurn].takeTurn(
                                    decks[pickUpFromDeck].pickUpCard(), discardToDeck, pickUpFromDeck)
                    );
                }

                if (players[playersTurn].hasWon()) {
                    System.out.println("Player " + players[playersTurn].getPlayerNumber() + " has won the game");
                    isWinner = true;
                    winner = players[playersTurn].getPlayerNumber();
                }
            }
            // procedure for when there is a winner

            // inform all players of winner
            // inform decks of end of game
            for (short i = 0; i < n; i++) {
                synchronized (players[i]) {
                    players[i].informPlayerHasWon(winner);
                }
                decks[i].writeContentsToFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ioException) {
            System.out.println("Could not create new pack from file " + filename);
            System.out.println("Try a different file");
        }
    }
}
