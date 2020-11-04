import org.junit.Test;

import java.io.IOException;

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
        assertEquals(player.getPlayerNumber(), playerNumber + 1);
    }


    @Test
    public void hasWonTrue() throws IOException {
        // This also tests addCard().
        Player player = new Player((short) 1);
        for (int i = 0; i < 4; i++) {
            player.addCard(new Card((short) 2));
        }
        assertTrue(player.hasWon());
    }

    @Test
    public void hasWonFalse() throws IOException {
        // This also tests addCard().
        Player player = new Player((short) 1);
        for (int i = 0; i < 4; i++) {
            player.addCard(new Card((short) i));
        }
        assertFalse(player.hasWon());
    }
}
