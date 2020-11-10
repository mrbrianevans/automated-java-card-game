import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CardGameTest {
    @Test
    public void testPlayGame() throws IOException {
        // this test creates a new pack file of cards
        int n = (int) Math.round(Math.random() * 100);
        String path = "testGamePack.txt";
        File f = new File(path);
        BufferedWriter writer;
        if (!f.createNewFile()) {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write("");
        }
        writer = new BufferedWriter(new FileWriter(path, true));
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 8; j++) {
                writer.write(i + "");
                writer.newLine();
            }
        }
        writer.close();

        // runs the game with the newly created pack file
        CardGame.playGame(n, path);
        // if the game throws any errors while running, this test will fail
    }
}
