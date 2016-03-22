package com.courses.spalah.dao;

import com.courses.spalah.domain.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Dima on 21.03.2016.
 */
class PersonDaoHelper {

    public static Pattern patternDelimiter = Pattern.compile(";\\s*");

    public static boolean replaceStrCorrespondToIdWithNewData(StringBuilder outputFileInfo,Person p){
        StringBuilderSegment sBuilderSegment = findStringSegmentInFileCorrespondToId(outputFileInfo.toString(), p.getId());
        if (!isIdWasFound(sBuilderSegment)){
            return false;
        }
        else {
            String replacingString = formReplacingStringFromPersonCorrectForDao(p);
            outputFileInfo.replace(sBuilderSegment.getStartPoint(), sBuilderSegment.getEndPoint(), replacingString);
            return true;
        }
    }

    public static boolean insertNewStr(StringBuilder outputFileInfo, Person p){
        int indexToInsert = findIndexToInsert(outputFileInfo.toString(), p.getId());
        if (indexToInsert == -1){
            return false;
        }
        else {
            outputFileInfo.insert(indexToInsert, p.getId() + "; " + formReplacingStringFromPersonCorrectForDao(p)+";\n");
            return true;
        }
    }

    public static void writeToFile(StringBuilder outputFileInfo, String path){
        try(FileWriter fileWriter = new FileWriter(new File(path))) {
            fileWriter.write(outputFileInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findIndexToInsert(String outputFileInfo, long id){
        Scanner scanner = new Scanner(outputFileInfo);
        scanner.useDelimiter(PersonDaoHelper.patternDelimiter);

        long personId;
        int indexToInsert;
        while (scanner.hasNext()){
            personId = Long.parseLong(scanner.next());
            if (isIdWasSkipped(id, personId)){
                scanner.close();
                indexToInsert = outputFileInfo.indexOf(Long.toString(personId));
                return indexToInsert;
            }
            if (personId == id){
                return -1;
            }
            else{
                scanner.nextLine();
            }
        }
        return outputFileInfo.length();
    }

    private static StringBuilderSegment findStringSegmentInFileCorrespondToId(String outputFileInfo, long id){
        Scanner scanner = new Scanner(outputFileInfo);
        scanner.useDelimiter(PersonDaoHelper.patternDelimiter);

        long personId;
        while (scanner.hasNext()){
            personId = Long.parseLong(scanner.next());
            if (isIdWasSkipped(id, personId)){
                scanner.close();
                return null;
            }
            if (personId == id){
                return createSegmentForString(scanner, outputFileInfo);
            }
            else{
                scanner.nextLine();
            }
        }
        scanner.close();
        return null;
    }

    private static boolean isIdWasSkipped(long idToSearch, long curIdInFile){
        return idToSearch < curIdInFile;
    }

    private static boolean isIdWasFound(StringBuilderSegment segment){
        return segment != null;
    }

    private static StringBuilderSegment createSegmentForString(Scanner s, String outputFileInfo){
        int start = outputFileInfo.indexOf(s.next());
        s.next();
        String last = s.next();
        int end = outputFileInfo.indexOf(last)+last.length();
        s.close();
        return new StringBuilderSegment(start,end);
    }

    private static String formReplacingStringFromPersonCorrectForDao(Person p){
        return p.getFirstName()+"; "+p.getLastName()+";  "+p.getAddress();

    }

    private static class StringBuilderSegment{
        int startPoint;
        int endPoint;

        StringBuilderSegment(int startP, int endP){
            startPoint = startP;
            endPoint = endP;
        }

        public int getEndPoint() {
            return endPoint;
        }

        public int getStartPoint() {
            return startPoint;
        }
    }
}
