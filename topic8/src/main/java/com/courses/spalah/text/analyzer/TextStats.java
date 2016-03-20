package com.courses.spalah.text.analyzer;

import javax.xml.soap.Text;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class saves TextSplitter's text in current session
 */
class TextStats {

    private int numberOfSentenses;
    private int numberOfWords;
    private int numberOfSymbols;
    private int numberOfUniqueWords;
    private int minLengthOfWord;
    private int maxLengthOfWord;
    private String theMostPopularWords;
    private String wordOccuranceTable;
    private String symbolOccuranceTable;



    //format String and take it to courses\topic8\src\main\resources\output.txt
    public void loadTextStatsToFile(){
        try(FileWriter writer = new FileWriter(new File("topic8\\src\\main\\resources\\output.txt"))) {
            writer.write("Number of sentenses = " + numberOfSentenses
                    +   "\nNumber of words = " + numberOfWords
                    +   "\nNumber of symbols = " + numberOfSymbols
                    +   "\nMinimum length of word = " + minLengthOfWord
                    +   "\nMaximum length of word = " + maxLengthOfWord
                    +   "\nNumber of unique words = " + numberOfUniqueWords
                    +   "\nThe most popular words = " + theMostPopularWords
                    +   "\nTable word - number of occurrences\n" + wordOccuranceTable
                    +   "\nTable symbol - number of occurrences\n" + symbolOccuranceTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void incNumberOfSentenses() {
        numberOfSentenses++;
    }

    public void incNumberOfWords() {
        numberOfWords++;
    }

    public void addNumberOfSymbols(int nSymbols) {
        numberOfSymbols += nSymbols;
    }

    public void setNumberOfUniqueWords(int numberOfUniqueWords) {
        this.numberOfUniqueWords = numberOfUniqueWords;
    }

    public void setMinLengthOfWord(int minLengthOfWord) {
        this.minLengthOfWord = minLengthOfWord;
    }

    public void setMaxLengthOfWord(int maxLengthOfWord) {
        this.maxLengthOfWord = maxLengthOfWord;
    }

    public void setTheMostPopularWords(String theMostPopularWords) {
        this.theMostPopularWords = theMostPopularWords;
    }

    public void setWordOccuranceTable(String wordOccuranceTable) {
        this.wordOccuranceTable = wordOccuranceTable;
    }

    public void setSymbolOccuranceTable(String symbolOccuranceTable) {
        this.symbolOccuranceTable = symbolOccuranceTable;
    }
}
