package application;

import counter.WordsCounter;

/**
 * @author Nikolay Ponomarev
 */
public class Main {

    public static void main(String[] args) {

        String sentence = "You take the red pill - you stay in Wonderland and I show you how deep the rabbit hole goes.";

        WordsCounter.Result result = new WordsCounter().countWords(sentence);
        System.out.println(result.getWordsCount());
        System.out.println(result.getLongestWordsSet());
    }
}
