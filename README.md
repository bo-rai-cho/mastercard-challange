# mastercard-challenge

Here is solution for mastercard coding challenge

## Getting Started

Here is two maven modules. The main one called "words-counter" is what you need.
The second one "words-counter-enterprise" represents just an experiment.

### Prerequisites

In the programming language of your choice create a class with a method to return the length and longest words in a sentence.
For example, “The cow jumped over the moon.” should return 6 and “jumped”.

### Restrictions

One of the hardest problem here is how to validate that given sentence is a "normal" sentence.
For example for humans this is not normal "!@#SDV sdfjkh877 ,,.nasd" but for machines is okay - just strings.

Following the definition of "sentence" from the Internet we can assume that:
* Sentence is a something which starts with a capital letter
* Sentence is a something which ends with character '.' or '!' or '?'

In this solution meaning "sentence" is the same but with 3 additional restriction:
* starts with an english letter
* ends with an english letter + character '.' or '!' or '?'
* no longer than 3000 characters after normalization (avoid spaces). For example this " This    is a    sentence " is 18 length long sentence

#### Examples

Good sentences:
* “The cow jumped over the moon.”
* “Who just jumped over the moon?”
* “The cow jumped over the moon!”
* “seriously?”

Bad sentences:
* “The cow jumped over the moon%” // ends with '%' symbol
* “The cow jumped over the moon” // does not have '.' or '?' or '!' character in the end
* "a." // does not start and end with letters

### Solution

Algorithm:

1. Validate given sentence - is this sentence is okay from our vision?

2. Normalize this sentence - for future processing we need to get our sentence in generic way.
For example here "This is this." actually just one longest word - "this".

3. Split this sentence to words array. Separator is space.

4. Find the word(s) with maximum length in all words array.

5. Return set of longest word(s) and maximum length of the word(s)

### Usage

This is an example of usage:

String sentence = “The cow jumped over the moon.”;

WordsCounter wordsCounter = new WordsCounter();

WordsCounter.Result result = wordsCounter.getLongestWords(sentence);

int longestLength = result.getLongestLength();

Set<String> longestWords = result.getLongestWordsSet();

System.out.println("Sentence: " + sentence);

System.out.println("Longest word(s) length: " + longestLength);

System.out.println("Longest word(s): " + longestWords);

## Running the tests

To run all test cases you need to install maven and run command

mvn clean test

### Test groups

* Exception tests
* Regression tests
* Performance test - current SLA is 1000 request per 200 milliseconds

## Future approach

The main problem in this challenge is how to define what is good sentence or what is not.
There is two possible solutions - add as many restrictions as we want or create a lexical analyzer or use an existing lexical analyst API.
