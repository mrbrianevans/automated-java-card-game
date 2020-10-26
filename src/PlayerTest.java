import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void getPlayerNumber() throws Exception {
        int playerNumber = Math.round((int) (Math.random() * 100));
        Player player = new Player(playerNumber);
        assertEquals(player.getPlayerNumber(), playerNumber + 1);
    }


    @Test
    public void hasWonTrue() throws IOException {
        // this also tests addCard()
        Player player = new Player(1);
        for (int i = 0; i < 4; i++) {
            player.addCard(new Card((short) 2));
        }
        assertTrue(player.hasWon());
    }

    @Test
    public void hasWonFalse() throws IOException {
        // this also tests addCard()
        Player player = new Player(1);
        for (int i = 0; i < 4; i++) {
            player.addCard(new Card((short) i));
        }
        assertFalse(player.hasWon());
    }
}