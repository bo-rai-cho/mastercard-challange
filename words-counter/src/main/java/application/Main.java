package application;

import counter.WordsCounter;

import java.util.Set;

/**
 * @author Nikolay Ponomarev
 */
public class Main {

    public static void main(String[] args) {

        String sentence = "You take the red pill - you stay in Wonderland and I show you how deep the rabbit hole goes.";
        WordsCounter wordsCounter = new WordsCounter();
        WordsCounter.Result result = wordsCounter.getLongestWords(sentence);

        int longestLength = result.getLongestLength();
        Set<String> longestWords = result.getLongestWordsSet();

        System.out.println("Sentence: " + sentence);
        System.out.println("Longest word(s) length: " + longestLength);
        System.out.println("Longest word(s): " + longestWords);
    }
}
