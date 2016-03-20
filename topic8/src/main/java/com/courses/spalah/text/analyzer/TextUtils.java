package com.courses.spalah.text.analyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Give us utils to work with program
 */
class TextUtils {

    private static Map<String, Integer> wordCount = new HashMap<>();
    private static Map<String, Integer> symbolCount = new HashMap<>();

    private static TextStats textStats = null;

    private TextUtils(){

    }

    //connect all the next invoked methods with current words' map and symbols' map(see WordAnalyzer.java) and its textstats
    public static void loadOutputData(Map<String, Integer> wordCountRef,Map<String, Integer> symbolCountRef, TextStats textStatsRef){
        wordCount = wordCountRef;
        symbolCount = symbolCountRef;
        textStats = textStatsRef;
    }

    /*
    *   Check after splitter processed sentence delimiter symbols, as they are symbols too
    */
    public static void processDelimitersInText(String text){
        Scanner textScanner = new Scanner(text);
        while (textScanner.hasNext()){
            String textPiece = textScanner.next();
            String delimeter = takeDelimiterInPiece(textPiece);
            if (isDelimiterWasFound(delimeter)){
                processSymbol(delimeter);
                textStats.addNumberOfSymbols(1);
            }
        }
    }

    /*
    * Check if word parameter if one is new in the map and same with one's symbols
    */
    public static void processWord(String word) {
        checkWordExistence(word);
        processWordBySymbols(word);
    }

    /*
    *   Try to take delimiter from one invoke of Scanner's next
    */
    private static String takeDelimiterInPiece(String textPiece){
        for (int i = textPiece.length() - 1; i >= 0; i--){ // Do it from the end of the piece, because of more possibility that delimeter right after the word
            if (isSymbolDelimiter(textPiece.charAt(i))){
                return textPiece.substring(i,i+1);
            }
        }
        return null;
    }

    private static boolean isSymbolDelimiter(Character symbol){
        for (int i = 0; i < PunctuationSymbols.PUNCTUATION_SYMBOLS_ARR.length; i ++){
            if (Character.compare(symbol,PunctuationSymbols.PUNCTUATION_SYMBOLS_ARR[i]) == 0){
                return true;
            }
        }
        return false;
    }

    //check if takeDelimiterInPiece() return right delimiter
    private static boolean isDelimiterWasFound(String symbol){
       return symbol != null;
    }

    private static void processWordBySymbols(String word){
        textStats.addNumberOfSymbols(word.length());

        String symbol = null;
        for (int i = 0; i < word.length(); i++){
            symbol = Character.toString(word.charAt(0));
            processSymbol(symbol);
        }
    }

    private static void checkWordExistence(String word){
        if (isItNewWord(word)){
            addNewWord(word);
        }
        else{
            updateExistingWordCounterByOne(word);
        }
    }

    private static boolean isItNewWord(String word){
        return !wordCount.containsKey(word);
    }

    private static void addNewWord(String word){
        wordCount.put(word,1);
    }

    private static void updateExistingWordCounterByOne(String word){
        wordCount.put(word,getWordCounter(word) + 1);
    }

    //take the word's existing number of occurrences in the text
    private static int getWordCounter(String word){
        return wordCount.get(word);
    }

    private static void processSymbol(String symbol){
        checkSymbolExistence(symbol);
    }

    private static void checkSymbolExistence(String symbol){
        if (isItNewSymbol(symbol)){
            addNewSymbol(symbol);
        }
        else{
            updateExistingSymbolCounterByOne(symbol);
        }
    }

    private static boolean isItNewSymbol(String symbol){
        return !symbolCount.containsKey(symbol);
    }

    private static void addNewSymbol(String symbol){
        symbolCount.put(symbol,1);
    }

    private static void updateExistingSymbolCounterByOne(String symbol){
        symbolCount.put(symbol,getSymbolCounter(symbol) + 1);
    }

    //take the symbol's existing number of occurrences in the text
    private static int getSymbolCounter(String word){
        return symbolCount.get(word);
    }

    public static void prepareMapsToPrintByOrder(){
        sortMapsByOccurrences();

        StringBuilder mapWordCountTable = new StringBuilder();
        for (Map.Entry entry : wordCount.entrySet()) {
            mapWordCountTable.append(entry.getKey() + "  " + entry.getValue()+"\n");
        }

        StringBuilder mapSymbolCountTable = new StringBuilder();
        for (Map.Entry entry : symbolCount.entrySet()) {
            mapSymbolCountTable.append(entry.getKey() + "  " + entry.getValue()+"\n");
        }


        textStats.setWordOccuranceTable(mapWordCountTable.toString());
        textStats.setSymbolOccuranceTable(mapSymbolCountTable.toString());

    }

    private static void sortMapsByOccurrences(){
       wordCount = MapUtils.sortByValue(wordCount);
       symbolCount = MapUtils.sortByValue(symbolCount);
    }
}
