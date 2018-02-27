package sentences;

import com.mastercard.words.model.Sentence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Nikolay Ponomarev
 */
@RunWith(Parameterized.class)
public class SpacesSentenceTest {

    private int expectedWordsCount;
    private String sentence;

    public SpacesSentenceTest(int expectedWordsCount, String sentence) {
        this.expectedWordsCount = expectedWordsCount;
        this.sentence = sentence;
    }

    @Parameterized.Parameters
    public static Object[] parameters() {
        return new Object[][] {
                { 2, "Try this " },
                { 2, " Try this" },
                { 2, " Try this " },
                { 3, "Try maybe this   " },
                { 3, "       What       about        this?          " }
        };
    }

    @Test
    public void testSpaces() {
        Sentence sentence = new Sentence(this.sentence);
        Assert.assertEquals(expectedWordsCount, sentence.toLowerCaseWords().length);
    }
}
