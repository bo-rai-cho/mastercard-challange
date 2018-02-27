package com.mastercard.words.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * @author Nikolay Ponomarev
 */
@AllArgsConstructor
@Getter
public final class WordsCounterResult {

    private final int wordsCount;
    private final Set<String> longestWords;
}
