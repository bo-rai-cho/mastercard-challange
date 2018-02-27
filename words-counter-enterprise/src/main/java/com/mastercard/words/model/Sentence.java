package com.mastercard.words.model;

import com.mastercard.words.exceptions.NotValidSentenceException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Sentence class.
 * Contains transform logic and static validation utility.
 *
 * @author Nikolay Ponomarev
 */
@Getter
@Slf4j
public final class Sentence {

    private final String originalSentence;

    /**
     * During constructor initialisation it checks given sentence for null or empty.
     *
     * @param sentence String sentence
     */
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

    /**
     * Get this sentence as the array of lower case words in case of the given sentence is valid.
     *
     * @return Array of words (string)
     * @throws NotValidSentenceException In case of the given string is not valid
     */
    public static String[] toLowerCaseWords(Sentence sentence) throws NotValidSentenceException {

        if (!isValid(sentence)) {
            throw new NotValidSentenceException(sentence.getOriginalSentence());
        }
        // Trim needed to avoid any spaces. We don't want to get an array like that {"Word"," ","word"," "," "}
        String[] words = sentence.getOriginalSentence().toLowerCase().trim().split("\\s++");

        // If sentence is valid we need to normalize words and remove last character ('.' or '!' or '?') in last word
        // For ex: end. -> end || end? -> end
        // We expect only words here (or comma as well?)
        words[words.length - 1] = removeLastChar(words[words.length - 1]);

        return words;
    }

    /**
     * Just small static util. Removes last character in a given string.
     * @param s Any string
     * @return String without last character
     */
    private static String removeLastChar(String s) {
        return  (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));
    }

    /**
     * Get longest words set from this sentence.
     * 1. Get array of words
     * 2. Find max current length of a word in the array of words
     * 3. Return set of words where word length equals max current length
     *
     * @return Set of longest words
     */
    public Set<String> longestWordsSet() {

        String[] words = new String[0];

        try {
            words = toLowerCaseWords(this);
        } catch (NotValidSentenceException e) {
            log.error(e.getMessage(), e);
        }

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

    /**
     * Check if a given sentence valid.
     * Valid sentences:
     * 1. Not null
     * 2. Start with a capital letter
     * 3. End with a letter and '.' or '!' or '?'
     *
     * Good sentence: "This is sentence."
     * Bad sentence: " tHis is sentence #"
     *
     * @param sentence Sentence
     * @return Validation result
     * @see Sentence
     */
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
