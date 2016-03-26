package com.courses.spalah;

<<<<<<< HEAD
import java.io.*;

=======
>>>>>>> upstream/master
/**
 * @author Ievgen Tararaka
 */
public class FileReader {
    public String pathToFile;

    public FileReader(String pathToFile) {
<<<<<<< HEAD
        this.pathToFile = "D:\\На пути к программисту\\courses\\topic10\\target\\test-classes\\"+pathToFile;
=======
        this.pathToFile = pathToFile;
>>>>>>> upstream/master
    }

    /**
     * Should return content of file by path
     * @return content of file
     */
    public String readFile() {
<<<<<<< HEAD
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

    public static void writeToThisFile(StringBuilder outputFileInfo, String path){
        try(FileWriter fileWriter = new FileWriter(new File(path))) {
            fileWriter.write(outputFileInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
=======
        return "";
>>>>>>> upstream/master
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}
