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
public class SentenceValidationTest {

    private boolean isValid;
    private Sentence sentence;

    public SentenceValidationTest(boolean isValid, String sentence) {
        this.isValid = isValid;
        this.sentence = new Sentence(sentence);
    }

    @Parameterized.Parameters
    public static Object[] parameters() {

        return new Object[][] {

                { true, "Try         this?" },
                { true, "Try maybe this!" },
                { true, "Try this?" },
                { true, "T97o9)(&@*#(*, t)(*&(^!@GKABSD?" }, // Yes, this is a 'normal' sentence... :)
                { false, "try this?" }, // starts with small letter
                { false, " Try this." }, // space in the beginning
                { false, " Try this. " }, // space in the end
                { false, "Try this..." }, // Plural dots in the end -> not valid
                { false, "       What       about        this?          " }, // Spaces in the beginning and in the end
                { false, "Try this;" }, // Dot and comma in the end
                { false, "Try this???" }, // Plural '?' characters
                { false, "Ho891723, 7kjh*&@!%#b" }, // What is that?
                { false, "." }, // Just comma
                { false, "1This is test." } // Number in the beginning
        };
    }

    @Test
    public void testValidation() {
        Assert.assertEquals(this.isValid, Sentence.isValid(sentence));
    }
}
