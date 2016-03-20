package com.courses.spalah.text.analyzer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class WordAnalyzer {

    private final int UNIQUE_COUNT = 1;

    private Map<String, Integer> wordCount; // map for storing words and number of their occurrences in the text
    private Map<String, Integer> symbolCount; // map for storing symbols and number of their occurrences int the text

    private TextStats textStats = null;

    public WordAnalyzer(TextStats textStats){
        wordCount = new HashMap<>();
        symbolCount = new HashMap<>();
        this.textStats = textStats;
        TextUtils.loadOutputData(wordCount,symbolCount,this.textStats);
    }

    //connects WordAnalyzer with textStats
    public void loadTextStats(TextStats textStatsRef) {
        textStats = textStatsRef;
    }

    //after all the processing of the text it find's it's max/min length word,the most popular word and number of unique words
    public void summarizeResults() {
        workWithWordCountKeys();
        workWithWordCountValues();
        TextUtils.prepareMapsToPrintByOrder();
    }

    //all the work that concern words' map keys
    private void workWithWordCountKeys(){
        Set<String> keys = wordCount.keySet();
        findMinMaxLengthWord(keys);
        findTheMostPopularWords(keys);
    }

    private void findMinMaxLengthWord(Set<String> keys){
        int minLengthOfWord = (int) Double.POSITIVE_INFINITY;
        int maxLengthOfWord = (int) Double.NEGATIVE_INFINITY;
        int keyLength;
        for (String word : keys){
            keyLength = word.length();
            if (keyLength > maxLengthOfWord){
                maxLengthOfWord = keyLength;
            }
            else if (keyLength < minLengthOfWord){
                minLengthOfWord = keyLength;
            }
        }

        textStats.setMinLengthOfWord(minLengthOfWord);
        textStats.setMaxLengthOfWord(maxLengthOfWord);
    }

    private void findTheMostPopularWords(Set<String> keys){
        int theMostPopularCount = findTheHighestPopularity(keys);

        StringBuilder theMostPopularWords = new StringBuilder();
        for (String word : keys){
            if (wordCount.get(word) == theMostPopularCount){
                theMostPopularWords.append(word + " " + theMostPopularCount + "\n");
            }
        }

        textStats.setTheMostPopularWords(theMostPopularWords.toString());
    }

    //find the highest number of occurances in the text
    private int findTheHighestPopularity(Set<String> keys){
        int theMostPopularCount = -1;
        for (String word : keys){
            int wordPopularity = wordCount.get(word);
            if (wordPopularity > theMostPopularCount) theMostPopularCount = wordPopularity;
        }

        return theMostPopularCount;
    }

    //all the work that concern words' map values
    private void workWithWordCountValues(){
        Collection<Integer> values = wordCount.values();
        findNumberOfUniqueWords(values);
    }


    private void findNumberOfUniqueWords(Collection<Integer> values) {
        int nUniqWords = 0;
        for (Integer count : values) {
            if (isWordUnique(count)) {
                nUniqWords++;
            }
        }
        textStats.setNumberOfUniqueWords(nUniqWords);
    }


    private boolean isWordUnique(int count){
        return count == UNIQUE_COUNT;
    }

}
