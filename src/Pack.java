import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Pack {

    private final Card[] cards;

    public Pack(String filename, int n) throws IOException {
        this.cards = new Card[8 * n];
        File packFile = new File(filename);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(packFile));
        String newline;
        int arrayIndex = 0;
        while ((newline = bufferedReader.readLine()) != null) {
            if (arrayIndex > 8 * n) System.out.println("Too many cards in pack"); // throw an exception
            this.cards[arrayIndex++] = new Card((short) Integer.parseInt(newline));
        }
        if (arrayIndex < 8 * n) System.out.println("Too few cards in pack"); // throw an exception
    }

    public Card[] getCards() {
        return cards;
    }
}
