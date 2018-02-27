package com.mastercard.words.executors;

import com.mastercard.words.exceptions.NotValidSentenceException;
import com.mastercard.words.model.Sentence;
import com.mastercard.words.request.WordsCounterRequest;
import com.mastercard.words.result.WordsCounterResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Main executor. Performs a given request and returns the result
 *
 * @author Nikolay Ponomarev
 */
@Slf4j
public final class WordsCounterExecutor {

    /**
     * @param request Words counter request
     * @return Words counter result
     * @throws NotValidSentenceException In case of a given string sentence is not valid
     * @see WordsCounterResult
     * @see WordsCounterResult
     */
    public WordsCounterResult execute(final WordsCounterRequest request) throws NotValidSentenceException {

        Sentence sentence = request.getSentence();

        // Check the original sentence is valid first. Here is a good point for data validation BEFORE execution.
        if (Sentence.isValid(sentence)) {

            String[] words = Sentence.toLowerCaseWords(sentence);

            return new WordsCounterResult(words.length, sentence.longestWordsSet());
        }

        // If we didn't pass the validation it's time to raise an exception!
        throw new NotValidSentenceException(sentence.getOriginalSentence());
    }
}
