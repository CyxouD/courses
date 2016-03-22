package com.courses.spalah;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Ievgen Tararaka
 */
public class FileReader {
    public String pathToFile;

    public FileReader(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    /**
     * Should return content of file by path
     * @return content of file
     */
    public String readFile() {
        StringBuilder fileInfo = null;
        try(BufferedReader bufReader = new BufferedReader(new java.io.FileReader(new File(pathToFile)))) {
            fileInfo = new StringBuilder();
            String line = null;
            while ((line = bufReader.readLine()) != null){
                fileInfo.append(line+"\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileInfo.toString();
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}
