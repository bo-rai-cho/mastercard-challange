package counter;

import com.mastercard.words.exceptions.NotValidSentenceException;
import com.mastercard.words.executors.WordsCounterExecutor;
import com.mastercard.words.model.Sentence;
import com.mastercard.words.request.WordsCounterRequest;
import com.mastercard.words.result.WordsCounterResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Nikolay Ponomarev
 */
public class WordsCounterTest {

    private static final String STRING_VALID_SENTENCE = "You take the red pill - you stay in Wonderland and I show you how deep the rabbit hole goes.";
    private static final String LONGEST_WORD = "wonderland";
    private static final int WORDS_COUNT = 20;

    @Test
    public void testNormalExecution() throws NotValidSentenceException {

        Sentence sentence = new Sentence(STRING_VALID_SENTENCE);
        WordsCounterRequest request = new WordsCounterRequest(sentence);
        WordsCounterExecutor executor = new WordsCounterExecutor();

        WordsCounterResult result = executor.execute(request);

        Assert.assertEquals(WORDS_COUNT, result.getWordsCount()); // Check words count
        Assert.assertEquals(1, result.getLongestWords().size()); // One longest word here
        Assert.assertEquals(LONGEST_WORD, result.getLongestWords().iterator().next()); // Check this words as expected
    }

    @Test
    public void testOneWord() throws NotValidSentenceException {

        Sentence sentence = new Sentence("Test.");
        WordsCounterRequest request = new WordsCounterRequest(sentence);
        WordsCounterExecutor executor = new WordsCounterExecutor();

        WordsCounterResult result = executor.execute(request);

        Assert.assertEquals(1, result.getWordsCount()); // Check words count
        Assert.assertEquals(1, result.getLongestWords().size()); // One longest word here
        Assert.assertEquals("test", result.getLongestWords().iterator().next()); // Check this words as expected without dot
    }

    @Test
    public void testRepeatingWord() throws NotValidSentenceException {

        Sentence sentence = new Sentence("This is this Sentence sentence this.");
        WordsCounterRequest request = new WordsCounterRequest(sentence);
        WordsCounterExecutor executor = new WordsCounterExecutor();

        WordsCounterResult result = executor.execute(request);

        Assert.assertEquals(6, result.getWordsCount());
        Assert.assertEquals(1, result.getLongestWords().size()); // Only one word here is longest - sentence
    }

    @Test
    public void testNotOnlyOneLongestWord() throws NotValidSentenceException {

        String sentence = "This is that five same word s.";

        Set<String> expectedSet = new TreeSet<>();
        expectedSet.add("this");
        expectedSet.add("that");
        expectedSet.add("five");
        expectedSet.add("same");
        expectedSet.add("word");

        Sentence s = new Sentence(sentence);
        WordsCounterRequest request = new WordsCounterRequest(s);
        WordsCounterExecutor executor = new WordsCounterExecutor();
        WordsCounterResult result = executor.execute(request);

        Assert.assertEquals(expectedSet, result.getLongestWords()); // 5 longest words in this sentence
    }
}
