package counter;

import com.mastercard.words.exceptions.NotValidSentenceException;
import com.mastercard.words.executors.WordsCounterExecutor;
import com.mastercard.words.model.Sentence;
import com.mastercard.words.request.WordsCounterRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Nikolay Ponomarev
 */
@RunWith(Parameterized.class)
public class WordsCounterExceptionTest {

    @Test(expected = NotValidSentenceException.class)
    public void testNotValidSentence() throws NotValidSentenceException {

        Sentence sentence = new Sentence("test");
        WordsCounterRequest request = new WordsCounterRequest(sentence);
        WordsCounterExecutor executor = new WordsCounterExecutor();

        executor.execute(request);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptySentence() throws NotValidSentenceException {

        Sentence sentence = new Sentence("");
        WordsCounterRequest request = new WordsCounterRequest(sentence);
        WordsCounterExecutor executor = new WordsCounterExecutor();

        executor.execute(request);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullSentence() throws NotValidSentenceException {

        Sentence sentence = new Sentence(null);
        WordsCounterRequest request = new WordsCounterRequest(sentence);
        WordsCounterExecutor executor = new WordsCounterExecutor();

        executor.execute(request);
    }
}
