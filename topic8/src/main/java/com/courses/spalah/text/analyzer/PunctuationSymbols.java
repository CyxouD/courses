package com.courses.spalah.text.analyzer;

import java.util.regex.Pattern;

/**
 * Util class for storing delimeters for my program in form of patterns and arrays
 */
public class PunctuationSymbols {

    public static final Pattern PUNCTUATION_MARKS_END_SENTENSE_RE = Pattern.compile("\\.+|!|\\?"); // pattern for working with end of sentense delimeters
    public static final Pattern PUNCTUATION_MARKS_END_WORD_RE = Pattern.compile("(|,|-|:|-|—)\\s+"); // pattern for working with end of word delimeters
    public static final char[] PUNCTUATION_SYMBOLS_ARR = new char[] {'.','!','?',',','-',':','—'};

    private PunctuationSymbols(){}



}
