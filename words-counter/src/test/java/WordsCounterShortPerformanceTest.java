import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import counter.WordsCounter;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Nikolay Ponomarev
 */
public class WordsCounterShortPerformanceTest {

    private final ArrayList<String> sentences = new ArrayList<>();
    private static final int COUNT = 1000; // sentences count
    private static final int SLA = 200; // millis for count

    @Before
    public void loadList() {
        for (int i = 0; i < COUNT; i++) {
            sentences.add("Test_" + UUID.randomUUID().toString() + "t.");
        }
    }

    @Test(timeout = 5 * SLA) // set timeout as current SLA multiple by 5
    public void performanceTest() {

        long start = System.currentTimeMillis();
        WordsCounter wordsCounter = new WordsCounter();

        // DO NOT replace foreach or lambda - we are trying to check performance here of our method
        for (int i = 0; i < sentences.size(); i++) {
            wordsCounter.getLongestWords(sentences.get(i));
        }

        long end = System.currentTimeMillis();

        long executedTime = end - start; // millis
        Assert.assertTrue("Executed time [" + executedTime + "] ms more than current SLA [" + SLA + "] ms", executedTime < SLA);
    }
}
