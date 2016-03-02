package com.rxn1d.courses;


import java.io.*;

/**
 * Created by Dima on 01.03.2016.
 */
public class CommandsFromFileLoader {
    private static String[] commandsSequence = new String[0];
    private static int commandPointer;

    public static void create(String filePath){
       loadFile(filePath);
    }

    public static String[] getCommand() throws LoaderNotReadyException{
        if (isCommandsEmpty()) {//exception may be
            throw new LoaderNotReadyException();
        }
        if (commandPointer != commandsSequence.length) {
            return commandsSequence[commandPointer++].split(" ");
        }
        else {
            return null;
        }
    }

    private CommandsFromFileLoader(){}

    private static void loadFile(String path){
        BufferedReader bufReader = null;
        try {
            checkFileExtension(path);
            bufReader = new BufferedReader(new FileReader(new File(path)));
            saveCommandsFromFile(bufReader);
        }catch(FileNotFoundException e){
            System.out.println("CAN NOT READ FILE: "+path);
            e.printStackTrace();
        }catch(IllegalFileExtentionException e){
            System.out.println("FILE IS NOT \"TXT\" EXTENSION");
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try {
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void saveCommandsFromFile(BufferedReader bufReader) throws  IOException{
        int nLines = 0;
        StringBuilder commands = new StringBuilder();
        String line = null;
        try {
            while ((line = bufReader.readLine()) != null){
                nLines++;
                if (line.length() != 0)
                    commands.append(line+"\n");

            }
            checkFileSize(nLines);
        }catch (FileIsEmptyException e){
            System.out.println("File is empty");
            e.printStackTrace();
        }
        commandsSequence = commands.toString().split("\\n");
    }

    private static void checkFileSize(int nLines) throws FileIsEmptyException{
        if (nLines == 0) throw new FileIsEmptyException();
    }

    private static void checkFileExtension(String filePath) throws IllegalFileExtentionException{
        String extension = "";

        int i = filePath.lastIndexOf('.');
        if (i > 0) {
            extension = filePath.substring(i+1);
        }
        if (!extension.equals("txt")) throw new IllegalFileExtentionException();

    }

    private static boolean isCommandsEmpty(){
            return commandsSequence.length == 0;
    }

    public static void main(String[] args){
        CommandsFromFileLoader.create("inputOK.txt");
        try {
            String[] line = null;
            while ((line = CommandsFromFileLoader.getCommand()) != null){
                System.out.println(line);
            }

        } catch (LoaderNotReadyException e) {
            System.out.println("METHOD CREATE() SHOULD BE USED FIRST");
            e.printStackTrace();
        }
    }

    static class FileIsEmptyException extends FileReadingLogicException {}

    static class FileReadingLogicException extends Exception{}

    static class IllegalFileExtentionException extends Exception{}

    static class LoaderNotReadyException extends Exception{}

}
