package com.mastercard.words.executors;

import com.mastercard.words.exceptions.NotValidSentenceException;
import com.mastercard.words.model.Sentence;
import com.mastercard.words.request.WordsCounterRequest;
import com.mastercard.words.result.WordsCounterResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Nikolay Ponomarev
 */
@Slf4j
public final class WordsCounterExecutor {

    public WordsCounterResult execute(final WordsCounterRequest request) throws NotValidSentenceException {

        Sentence sentence = request.getSentence();

        // Check the original sentence is valid first. Here is a good point for data validation BEFORE execution.
        if (Sentence.isValid(sentence)) {

            String[] words = sentence.toLowerCaseWords();

            return new WordsCounterResult(words.length, sentence.longestWordsSet());
        }

        // If we didn't pass the validation it's time to raise an exception!
        throw new NotValidSentenceException(sentence.getOriginalSentence());
    }
}
