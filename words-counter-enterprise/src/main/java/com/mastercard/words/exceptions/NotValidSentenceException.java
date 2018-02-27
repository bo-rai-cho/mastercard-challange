package com.mastercard.words.exceptions;

/**
 * @author Nikolay Ponomarev
 */
public class NotValidSentenceException extends Exception {

    public NotValidSentenceException(String sentence) {
        super("Sentence [" + sentence + "] is not valid");
    }
}
