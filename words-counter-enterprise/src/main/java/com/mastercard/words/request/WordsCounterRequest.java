package com.mastercard.words.request;

import com.mastercard.words.model.Sentence;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Immutable request for executor. Includes given sentence.
 *
 * @author Nikolay Ponomarev
 */
@AllArgsConstructor
@Getter
public final class WordsCounterRequest {

    private final Sentence sentence;
}
