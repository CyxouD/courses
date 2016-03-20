package com.courses.spalah.text.analyzer;

import java.util.Scanner;

/**
 * Class that split Text using punctuation marks that explained in PunctuationSymbols.java
 */
class TextSplitter {


    private TextStats textStats;
    private WordAnalyzer wordAnalyzer;

    public TextSplitter(){
        textStats = new TextStats();
        wordAnalyzer = new WordAnalyzer(textStats);
    }




    //Take text and split each sentense using delimeters
    public void splitTextAtSentenses(String text){
        TextUtils.processDelimitersInText(text);

        Scanner scan = new Scanner(text);
        scan.useDelimiter(PunctuationSymbols.PUNCTUATION_MARKS_END_SENTENSE_RE);
        while (scan.hasNext()){
            String sentense = scan.next();
            splitSentenseAtWords(sentense);
            textStats.incNumberOfSentenses();
        }

        wordAnalyzer.summarizeResults();
        textStats.loadTextStatsToFile();
    }


    //Take sentense and split sentense into words using delimeters
    private void splitSentenseAtWords(String sentense){
        Scanner scan = new Scanner(sentense);
        scan.useDelimiter(PunctuationSymbols.PUNCTUATION_MARKS_END_WORD_RE);
        while (scan.hasNext()) {
            String word = scan.next().toLowerCase();
            TextUtils.processWord(word);
            textStats.incNumberOfWords();
        }

    }
}
