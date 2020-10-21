import java.io.FileNotFoundException;
import java.io.IOException;

public class CardGame {
    public static void main(String[] args) {
        // request from command line
        System.out.println("Enter the number of players in the game: ");
        final int n = Integer.parseInt(System.console().readLine());
        System.out.println("Enter the filename for a pack of cards: ");
        String filename = System.console().readLine();
        try {
            Pack pack = new Pack(filename, n);
            Card[] cardsInPack = pack.getCards();
            Player[] players = new Player[n];
            CardDeck[] decks = new CardDeck[n];

            for (int i = 0; i < n; i++) {
                players[i] = new Player(i);
                decks[i] = new CardDeck();
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

            int turns = 0;
            while (!isWinner) {
                for (Player player :
                        players) {
                    if (player.hasWon()) {
                        System.out.println("Player " + player.getPlayerNumber() + " has won the game");
                        isWinner = true;
                    }
                }

                int playersTurn = turns++ % n;
                int discardToDeck = (playersTurn + 1) % n;
                int pickUpFromDeck = playersTurn;
                decks[discardToDeck].discardCard(
                        players[playersTurn].takeTurn(
                                decks[pickUpFromDeck].pickUpCard(), discardToDeck, pickUpFromDeck)
                );
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ioException) {
            System.out.println("Could not create new pack from file " + filename);
            System.out.println("Try a different file");
        }


    }
}
