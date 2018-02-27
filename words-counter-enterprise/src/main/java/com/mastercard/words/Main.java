package com.mastercard.words;


import com.mastercard.words.exceptions.NotValidSentenceException;
import com.mastercard.words.executors.WordsCounterExecutor;
import com.mastercard.words.model.Sentence;
import com.mastercard.words.request.WordsCounterRequest;
import com.mastercard.words.result.WordsCounterResult;

/**
 * Main class
 *
 * @author Nikolay Ponomarev
 */
public class Main {

    public static void main(String[] args) throws NotValidSentenceException {

        WordsCounterExecutor executor = new WordsCounterExecutor();
        String sentence = "You take the red pill - you stay in Wonderland and I show you how deep the rabbit hole goes.";
        WordsCounterRequest request = new WordsCounterRequest(new Sentence(sentence));

        WordsCounterResult result = executor.execute(request);

        System.out.println("Given sentence: " + sentence);
        System.out.println("Words count: " + result.getWordsCount());
        System.out.println("Longest words: " + result.getLongestWords());
    }
}
