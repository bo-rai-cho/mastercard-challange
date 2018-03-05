package counter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class represents solution for count words and get longest words from a given sentence.
 *
 * @author Nikolay Ponomarev
 */
public final class WordsCounter {

    /**
     * Function returns result for a given string.
     *
     * 1. Check sentence - if it's not valid raise IllegalArgumentException.
     * Valid sentence starts from capital letter, ends with any letter and character '.' or '!' or '?'
     * Not null and not empty
     *
     * 2. Normalize the sentence - all character to lower cases and remove last character ('.' or '!' or '?')
     *
     * 3. Get words as an array of string
     *
     * 4. Forming set of words
     *
     * 5. Remove smallest words from the set
     *
     * 6. Return result (result included longest word(s) and length)
     *
     * @param sentence String sentence
     * @return Result
     * @see Result
     */
    public Result getLongestWords(String sentence) {

        if (sentence == null || sentence.isEmpty()) {
            throw new IllegalArgumentException("Given sentence [" + sentence + "] is not valid. It is null or empty.");
        }

        // Normalize this sentence, remove spaces and make lower cases
        String normalizedSentence = sentence.trim().toLowerCase();

        if (!isValid(normalizedSentence)) {
            throw new IllegalArgumentException("Given sentence [" + sentence + "] is not valid");
        }

        // remove last character.
        // We already know that this sentence is 'normal' and passed validation so we can safely delete last char '.' or '?' or '!'
        normalizedSentence = normalizedSentence.substring(0, normalizedSentence.length() - 1);

        String[] words = toWords(normalizedSentence);

        Set<String> wordsSet = new HashSet<>(); // define new set for words in a given sentence
        int maxLengthWord = 0; // so far max length is zero

        for (String word : words) { // finding maximum length of a word(s)

            if (word.length() >= maxLengthWord) {
                maxLengthWord = word.length();
                wordsSet.add(word);
            }
            // else we even don't need to add this word to our set
        }

        removeShortestWords(wordsSet, maxLengthWord); // remove shortest words from set

        return new Result(maxLengthWord, wordsSet); // and return result
    }

    /**
     * Remove words from set by criteria (word length < given length)
     *
     * @param wordsSet Set of words
     * @param longestLength Longest word length (threshold for removing)
     */
    private void removeShortestWords(Set<String> wordsSet, final int longestLength) {
        wordsSet.removeIf(word -> word.length() < longestLength);
    }

    /**
     * Split the sentence by space
     *
     * @param sentence String sentence
     * @return String array of words
     */
    private String[] toWords(String sentence) {
        return sentence.split("\\s+");
    }

    /**
     * Check if the sentence is valid.
     * Starts with a capital english letter and ends with an english letter and char '.' or '!' or '?'
     *
     * @param sentence String sentence
     * @return Validation result
     */
    private boolean isValid(String sentence) {
        return sentence.matches("^[a-zA-Z].*[a-zA-Z][.!?]$")
               && sentence.length() <= 3000;
    }

    /**
     * Class includes the result - longest word(s) length and set of longest words
     */
    public final class Result {

        private final int longestLength;
        private final Set<String> longestWordsSet;

        Result(int longestLength, Set<String> longestWordsSet) {
            this.longestLength = longestLength;
            this.longestWordsSet = longestWordsSet;
        }

        public int getLongestLength() {
            return longestLength;
        }

        public Set<String> getLongestWordsSet() {
            return longestWordsSet;
        }
    }
}
