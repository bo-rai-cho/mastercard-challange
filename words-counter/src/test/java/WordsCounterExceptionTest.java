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

    public WordsCounterExceptionTest(String sentence) {
        this.sentence = sentence;
    }

    @Parameterized.Parameters
    public static Object[] parameters() {

        return new Object[] {

                 null, // null sentence
                 "", // empty sentence
                 "try this?" , // starts with small letter
                 " Try this." , // space in the beginning
                 " Try this. " , // space in the end
                 "Try this..." , // Plural dots in the end -> not valid
                 "       What       about        this?          " , // Spaces in the beginning and in the end
                 "Try this;" , // Dot and comma in the end
                "Не английское словечко." , // Not english sentence
                 "Try this???" , // Plural '?' characters
                 "Ho891723, 7kjh*&@!%#b" , // What is that?
                 "." , // Just comma
                 "1This is test.", // Number in the beginning
                "This is test1." // Number in the end
        };
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongSentenceOnlyOneCharacter() {

        wordsCounter.countWords(sentence);
    }
}
