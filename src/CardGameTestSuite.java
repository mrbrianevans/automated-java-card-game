import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Run all tests for the package
 *
 * @author 041595 & 050744
 * @version 1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({PlayerTest.class,
        CardTest.class, CardDeckTest.class,
        PackTest.class, CardGameTest.class})
public class CardGameTestSuite {
}
