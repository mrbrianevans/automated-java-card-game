import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static org.junit.Assert.assertArrayEquals;

/**
 * Testing of Pack class using jUnit 4 testing conventions. In this test a pack is created in setUp().
 * Once a pack is generated a test to see if cards can be retrieved from the pack is run.
 *
 * @author 041595 & 050744
 * @version 1.0
 */

public class PackTest {
    Pack pack;
    Card[] cardArray;

    @Before
    public void setUp() throws Exception {
        // Create a file with cards.
        int n = (int) Math.round(Math.random() * 100);
        String path = "testPack.txt";
        File f = new File(path);
        BufferedWriter writer;
        if (!f.createNewFile()) {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write("");
        }
        cardArray = new Card[n * 8];
        writer = new BufferedWriter(new FileWriter(path, true));
        int nextCard = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 8; j++) {
                cardArray[nextCard++] = new Card((short) i);
                writer.write(i + "");
                writer.newLine();
            }
        }
        writer.close();
        this.pack = new Pack(path, n);
    }

    @Test
    public void testGetCards() {
        assertArrayEquals(this.pack.getCards(), cardArray);
    }
}
