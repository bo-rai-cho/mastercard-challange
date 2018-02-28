package counter;

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
     * 6. Return result
     *
     * @param sentence String sentence
     * @return Result
     * @see Result
     */
    public Result countWords(String sentence) {

        if (!isValid(sentence)) {
            throw new IllegalArgumentException("Given sentence [" + sentence + "] is not valid");
        }

        // trim, to lower cases and remove last character.
        // We already know that this sentence is 'normal' so we can safely delete last char '.' or '?' or '!'
        String normalizedSentence = normalize(sentence).substring(0, sentence.length() - 1);

        String[] words = toWords(normalizedSentence);

        Set<String> wordsSet = new HashSet<>(); // define new set for words in a given sentence
        int maxLengthWord = 0; // so far max length of a word is zero

        for (String word : words) {

            if (word.length() >= maxLengthWord) {
                maxLengthWord = word.length();
                wordsSet.add(word);
            }
            // else we even don't need to add this word to our set
        }

        removeShortestWords(wordsSet, maxLengthWord); // remove shortest words from set

        return new Result(words.length, wordsSet); // and return result
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
     * Validation criteria: not null, not empty,
     * starts with a capital english letter and ends with an english letter and char '.' or '!' or '?'
     *
     * @param sentence String sentence
     * @return Validation result
     */
    private boolean isValid(String sentence) {
        return !(sentence == null || sentence.isEmpty()) && sentence.matches("^[A-Z].*[a-zA-Z][.!?]$");
    }

    /**
     * Normalize a string sentence.
     * Trim and to lower cases.
     *
     * @param sentence String sentence
     * @return Normalized sentence
     */
    private String normalize(String sentence) {
        return sentence.trim().toLowerCase();
    }

    /**
     * Class includes the result - words count and set of longest words
     */
    public final class Result {

        private int wordsCount = 0; // By default it's zero
        private Set<String> longestWordsSet;

        Result(int wordsCount, Set<String> longestWordsSet) {
            this.wordsCount = wordsCount;
            this.longestWordsSet = longestWordsSet;
        }

        public int getWordsCount() {
            return wordsCount;
        }

        public Set<String> getLongestWordsSet() {
            return longestWordsSet;
        }
    }
}
