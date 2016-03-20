package com.courses.spalah.text.analyzer;

import java.io.*;

    /*
    *   Class that reads whole text
    */


public class TextReader {

    private TextReader(){

    }

    public static void readTextFromFile(String path){
        StringBuilder text = null;
        try( BufferedReader in = new BufferedReader(new FileReader(new File(path)));) {
            String line = null;
            text = new StringBuilder();
            while ((line = in.readLine()) != null)
                text.append(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        transferTextToTextSplitter(text.toString());
    }

    private static void transferTextToTextSplitter(String text){
        new TextSplitter().splitTextAtSentenses(text);
    }
}
