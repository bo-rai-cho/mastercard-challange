package com.mastercard.words.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nikolay Ponomarev
 */
@Getter
@Slf4j
public final class Sentence {

    private final String originalSentence;

    public Sentence(final String sentence) {

        // Okay, here is many opinions about any validation in constructor.
        // Well... it's all about checked vs unchecked exceptions.
        // Unchecked native is okay, checked is not a good idea.
        if (sentence == null) {
            throw new IllegalArgumentException("The sentence is null");
        }
        if (sentence.isEmpty()) {
            throw new IllegalArgumentException("The sentence is empty");
        }

        // set the reference to the original string sentence
        this.originalSentence = sentence;
    }

    public String[] toLowerCaseWords() {

        // Trim needed to avoid any spaces. We don't want to get an array like that {"Word"," ","word"," "," "}
        String[] words = this.originalSentence.toLowerCase().trim().split("\\s++");

        // If sentence is valid we need to normalize words and remove last character in last word
        // For ex: end. -> end
        // We expect only words here (or comma as well?)
        words[words.length - 1] = removeLastChar(words[words.length - 1]);

        return words;
    }

    public Set<String> longestWordsSet() {

        String[] words = this.toLowerCaseWords();
        int max = 0; // So far

        for (String word : words) {
            if (word.length() >= max) {
                max = word.length();
            }
        }

        final int maxCurrent = max; // for lambda passing we need this variable as final

        // Okay, why set? We don't need any repeating words in the end result.
        return Arrays.stream(words)
                .filter(word -> word.length() == maxCurrent)
                .collect(Collectors.toSet());
    }

    private String removeLastChar(String s) {
        return  (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));
    }

    public static boolean isValid(Sentence sentence) {

        // And... this is the hardest question - what is valid sentence?
        if (sentence == null) {
            return false;
        }

        // Check if the given sentence ends with ! or . or ? followed by letter and starts with capital letter
        // This sentence is valid!
        // this sentence is not valid;
        return sentence.getOriginalSentence().matches("^[A-Z].*[a-zA-Z][.!?]$");
    }
}
