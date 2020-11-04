import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * PlayerTest class to test all of the functionality and logicality of Player.java. Testing includes playerNumber,
 * hasWonTrue and hasWonFalse.
 *
 * @author 690024916 & 690023094
 * @version 1.0
 */

public class PlayerTest {

    @Test
    public void getPlayerNumber() throws Exception {
        // Generate player.
        short playerNumber = (short) Math.round((Math.random() * 100));
        Player player = new Player(playerNumber);
        assertEquals(player.getPlayerNumber(), playerNumber);
    }

    @Test
    public void testWriteToPlayerFile() {
        try {
            Player player = new Player((short) 1);
            Method writeToPlayerFile = Player.class.getDeclaredMethod("writeToPlayerFile", String.class);
            writeToPlayerFile.setAccessible(true);
            String testOutput = "test output writing to player file";
            writeToPlayerFile.invoke(player, testOutput);
            File playerFile = new File("gameOutput/player1.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(playerFile));
            assertEquals(testOutput, bufferedReader.readLine());
        } catch (Exception e) {
            System.out.printf("Could not run test due to exception: %s", e);
        }
    }

    @Test
    public void testAddCard() {
        Random random = new Random();
        Player player = new Player((short) random.nextInt(1000));

        Card[] expectedHand = new Card[5];
        short cardNumber;
        Card card;
        for (int i = 0; i < 4; i++) {
            cardNumber = (short) random.nextInt(1000);
            card = new Card(cardNumber);
            expectedHand[i] = card;
            player.addCard(card);
        }

        try {
            Field getPlayersHand = Player.class.getDeclaredField("hand");
            getPlayersHand.setAccessible(true);
            Card[] actualHand = (Card[]) getPlayersHand.get(player);
            assertArrayEquals(expectedHand, actualHand);

            // TODO: Test if the player writes initial hand to output

        } catch (Exception e) {
            fail(String.format("Threw an exception: %s", e));
        }
    }

    @Test
    public void testHandStringRepr() {
        Random random = new Random();
        Player player = new Player((short) random.nextInt(1000));

        StringBuilder expectedHandRepr = new StringBuilder();
        short cardNumber;
        Card card;
        for (int i = 0; i < 4; i++) {
            cardNumber = (short) random.nextInt(1000);
            card = new Card(cardNumber);
            expectedHandRepr.append(cardNumber).append(" ");
            player.addCard(card);
        }
        try {
            Method handStringRepr = Player.class.getDeclaredMethod("handStringRepr", (Class<?>[]) null);
            handStringRepr.setAccessible(true);
            String actualHandRepr = (String) handStringRepr.invoke(player);
            assertEquals(expectedHandRepr.toString().trim(), actualHandRepr);
        } catch (Exception e) {
            fail(String.format("Threw an exception: %s", e));
        }
    }

    @Test
    public void testTakeTurn() throws IOException {
        Random random = new Random();
        short preferredNumber = (short) random.nextInt(1000);
        Player player = new Player(preferredNumber);
        Card pickUpCard = new Card((short) random.nextInt(1000)), secondCardInHand, fourthCardInHand;
        do { //ensures that the two cards are different
            secondCardInHand = new Card((short) random.nextInt(1000));
            fourthCardInHand = new Card((short) random.nextInt(1000));
        } while (secondCardInHand == fourthCardInHand);

        player.addCard(new Card(preferredNumber));
        player.addCard(secondCardInHand);
        player.addCard(new Card(preferredNumber));
        player.addCard(fourthCardInHand);

        int discardDeckNumber = random.nextInt(1000);
        int pickUpDeckNumber = random.nextInt(1000);
        Card discardedCard = player.takeTurn(pickUpCard, discardDeckNumber, pickUpDeckNumber);

        // test that the discard card is not the players preferred card
        assertNotEquals(preferredNumber, discardedCard.getValue());

        try {
            Field getPlayersHand = Player.class.getDeclaredField("hand");
            getPlayersHand.setAccessible(true);
            Card[] actualHand = (Card[]) getPlayersHand.get(player);

            // test that the picked up card has been added to their hand
            assertTrue(actualHand[1] == pickUpCard || actualHand[3] == pickUpCard);

            // test that the discarded card was actually from the players hand
            assertTrue(discardedCard == secondCardInHand || discardedCard == fourthCardInHand);

            // test that the discard card has been removed from their hand
            assertFalse(actualHand[1] == discardedCard || actualHand[3] == discardedCard);

            // test that the player printed the correct output to their file
            File playerFile = new File(String.format("gameOutput/player%d.txt", preferredNumber));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(playerFile));
            bufferedReader.readLine();
            assertEquals(String.format("Player %d draws %d from deck %d",
                    preferredNumber, pickUpCard.getValue(), pickUpDeckNumber + 1),
                    bufferedReader.readLine());
            assertEquals(String.format("Player %d discards %d to deck %d",
                    preferredNumber, discardedCard.getValue(), discardDeckNumber + 1),
                    bufferedReader.readLine());
            Method handStringRepr = Player.class.getDeclaredMethod("handStringRepr", (Class<?>[]) null);
            handStringRepr.setAccessible(true);
            assertEquals(String.format("Player %d current hand %s",
                    preferredNumber, handStringRepr.invoke(player)),
                    bufferedReader.readLine());
        } catch (Exception e) {
            fail(String.format("Threw an exception: %s", e));
        }
    }

    @Test
    public void hasWonTrue() {
        Player player = new Player((short) 1);
        for (int i = 0; i < 4; i++) {
            player.addCard(new Card((short) 2));
        }
        assertTrue(player.hasWon());
    }

    @Test
    public void hasWonFalse() {
        Player player = new Player((short) 1);
        for (int i = 0; i < 4; i++) {
            player.addCard(new Card((short) i));
        }
        assertFalse(player.hasWon());
    }

    @Test
    public void informPlayerHasWon() {
        // test that it writes to file correctly
    }
}
