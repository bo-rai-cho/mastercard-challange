import org.junit.Assert;
import org.junit.Test;
import counter.WordsCounter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikolay Ponomarev
 */
public class WordsCounterRegressionTest {

    // Junit creates the instance of a class for each test execution
    // So we don't need to care about it.
    private final WordsCounter wordsCounter = new WordsCounter();

    @Test
    public void testWordsCounter() {

        String sentence = "You take the red pill - you stay in Wonderland and I show you how deep the rabbit hole goes.";
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("wonderland");

        WordsCounter.Result result = wordsCounter.countWords(sentence);
        Assert.assertEquals(20, result.getWordsCount());
        Assert.assertEquals(expectedSet, result.getLongestWordsSet());
    }

    @Test
    public void testMoreThenOneLongestWord() {

        String sentence = "You can take take this this pill pill!";
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("take");
        expectedSet.add("this");
        expectedSet.add("pill");

        WordsCounter.Result result = wordsCounter.countWords(sentence);
        Assert.assertEquals(8, result.getWordsCount());
        Assert.assertEquals(expectedSet, result.getLongestWordsSet());
    }

    @Test
    public void testLongestWordRepeating() {

        String sentence = "You can take take this this pill pill!";
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("take");
        expectedSet.add("this");
        expectedSet.add("pill");

        WordsCounter.Result result = wordsCounter.countWords(sentence);
        Assert.assertEquals(8, result.getWordsCount());
        Assert.assertEquals(expectedSet, result.getLongestWordsSet());
    }

    @Test
    public void testFirstWordRepeating() {

        String sentence = "Pill or not to pill.";
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("pill"); // Here is just one word - pill.

        WordsCounter.Result result = wordsCounter.countWords(sentence);
        Assert.assertEquals(5, result.getWordsCount());
        Assert.assertEquals(expectedSet, result.getLongestWordsSet());
    }

    @Test
    public void testSpaces() {

        String sentence = "Take       this         pill.";
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("take");
        expectedSet.add("this");
        expectedSet.add("pill");

        WordsCounter.Result result = wordsCounter.countWords(sentence);
        Assert.assertEquals(3, result.getWordsCount());
        Assert.assertEquals(expectedSet, result.getLongestWordsSet());
    }

    @Test
    public void testQuestionSentence() {

        String sentence = "Do you want to take this pill?";
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("take");
        expectedSet.add("this");
        expectedSet.add("pill");
        expectedSet.add("want");

        WordsCounter.Result result = wordsCounter.countWords(sentence);
        Assert.assertEquals(7, result.getWordsCount());
        Assert.assertEquals(expectedSet, result.getLongestWordsSet());
    }

    @Test
    public void testExclamatorySentence() {

        String sentence = "You want to take this pill!";
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("take");
        expectedSet.add("this");
        expectedSet.add("pill");
        expectedSet.add("want");

        WordsCounter.Result result = wordsCounter.countWords(sentence);
        Assert.assertEquals(6, result.getWordsCount());
        Assert.assertEquals(expectedSet, result.getLongestWordsSet());
    }
}
