package com.mastercard.words.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Represents the immutable result we need.
 * As per requirement the result contains words count and longest words
 *
 * @author Nikolay Ponomarev
 */
@AllArgsConstructor
@Getter
public final class WordsCounterResult {

    private final int wordsCount;
    private final Set<String> longestWords;
}
