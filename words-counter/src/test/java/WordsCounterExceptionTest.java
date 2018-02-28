import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import counter.WordsCounter;

/**
 * @author Nikolay Ponomarev
 */
@RunWith(Parameterized.class)
public class WordsCounterExceptionTest {

    private final WordsCounter wordsCounter = new WordsCounter();
    private final String sentence;
    private static final int LENGTH_RESTRICTION = 3000;

    public WordsCounterExceptionTest(String sentence) {
        this.sentence = sentence;
    }

    @Parameterized.Parameters
    public static Object[] parameters() {

        StringBuilder longSentence = new StringBuilder();
        for (int i = 0 ; i < LENGTH_RESTRICTION; i++) {
            longSentence.append('a'); // checking spaces
        }

        return new Object[] {

                 null, // null sentence
                 "", // empty sentence
                 "Try this..." , // Plural dots in the end -> not valid
                 "Try this;" , // Dot and comma in the end
                 "Не английское словечко." , // Not english sentence
                 "Try this???" , // Plural '?' characters
                 "Ho891723, 7kjh*&@!%#b" , // What is that?
                 "." , // Just comma
                 "1This is test.", // Number in the beginning
                 "This is test1.", // Number in the end
                 longSentence.toString() + "." // 3001 character
        };
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongSentence() {

        wordsCounter.getLongestWords(sentence);
    }
}
