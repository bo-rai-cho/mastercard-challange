package performance;

import com.mastercard.words.exceptions.NotValidSentenceException;
import com.mastercard.words.executors.WordsCounterExecutor;
import com.mastercard.words.model.Sentence;
import com.mastercard.words.request.WordsCounterRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Nikolay Ponomarev
 */
public class WordsCounterPerfTest {

    private final ArrayList<Sentence> sentences = new ArrayList<>();
    private static final int COUNT = 1000; // sentences count
    private static final int SLA = 300; // millis for count

    @Before
    public void loadList() {

        for (int i = 0; i < COUNT; i++) {
            Sentence sentence = new Sentence("Test_" + UUID.randomUUID().toString() + "t.");
            sentences.add(sentence);
        }
    }

    @Test (timeout = 5 * SLA) // set timeout as current SLA multiple by 5
    public void perfTest() throws NotValidSentenceException {

        long start = System.currentTimeMillis();

        for (Sentence sentence: sentences) {
            WordsCounterRequest request = new WordsCounterRequest(sentence);
            WordsCounterExecutor executor = new WordsCounterExecutor();
            executor.execute(request);
        }

        long end = System.currentTimeMillis();

        long executedTime = end - start; // millis
        Assert.assertTrue("Executed time [" + executedTime +"] more than current SLA [" + SLA + "]", executedTime < SLA);
    }
}
