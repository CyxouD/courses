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
            String replacingString = formStringFromPersonCorrectForDao(p);
            outputFileInfo.replace(sBuilderSegment.getStartPoint(), sBuilderSegment.getEndPoint(), replacingString);
            return true;
        }
    }

    public static boolean insertStr(StringBuilder outputFileInfo, Person p){
        int indexToInsert = findIndexToInsert(outputFileInfo.toString(), p.getId());
        if (indexToInsert == -1){
            return false;
        }
        else {
            outputFileInfo.insert(indexToInsert, formStringFromPersonCorrectForDao(p)+"\n");
            return true;
        }
    }

    public static Person deleteStr(StringBuilder outputFileInfo, long id){
        StringBuilderSegment sBuilderSegment = findStringSegmentInFileCorrespondToId(outputFileInfo.toString(), id);
        if (!isIdWasFound(sBuilderSegment)){
            return null;
        }
        else {
            Person p = makePersonObjectUsingString(outputFileInfo, sBuilderSegment);
            outputFileInfo.delete(sBuilderSegment.getStartPoint(), sBuilderSegment.getEndPoint());
            outputFileInfo.deleteCharAt(0); // to not leave an empty line
            return p;
        }
    }


    private static int findIndexToInsert(String outputFileInfo, long id){
        try(Scanner scanner = new Scanner(outputFileInfo);) {
            scanner.useDelimiter(PersonDaoHelper.patternDelimiter);

            long personId;
            int indexToInsert;
            while (scanner.hasNext()) {
                personId = Long.parseLong(scanner.next());
                if (isIdWasSkipped(id, personId)) {
                    indexToInsert = outputFileInfo.indexOf(Long.toString(personId));
                    return indexToInsert;
                }
                if (personId == id) {
                    return -1;
                } else {
                    scanner.nextLine();
                }
            }
            return outputFileInfo.length();
        }
    }

    private static StringBuilderSegment findStringSegmentInFileCorrespondToId(String outputFileInfo, long id){
        try(Scanner scanner = new Scanner(outputFileInfo)) {
            scanner.useDelimiter(PersonDaoHelper.patternDelimiter);

            long personId;
            while (scanner.hasNext()) {
                personId = Long.parseLong(scanner.next());
                if (isIdWasSkipped(id, personId)) {
                    return null;
                }
                if (personId == id) {
                    return createSegmentForString(scanner, outputFileInfo, personId);
                } else {
                    scanner.nextLine();
                }
            }
            return null;
        }
    }

    private static boolean isIdWasSkipped(long idToSearch, long curIdInFile){
        return idToSearch < curIdInFile;
    }

    private static boolean isIdWasFound(StringBuilderSegment segment){
        return segment != null;
    }

    private static StringBuilderSegment createSegmentForString(Scanner s, String outputFileInfo, long id){
        int start = outputFileInfo.indexOf(Long.toString(id));
        s.next();
        s.next();
        String last = s.next();
        int end = outputFileInfo.indexOf(last)+last.length()+1; // +1 because of ';' at the end
        return new StringBuilderSegment(start,end);
    }

    private static String formStringFromPersonCorrectForDao(Person p){
        return p.getId()+"; "+p.getFirstName()+"; "+p.getLastName()+";  "+p.getAddress()+";";

    }

    private static Person makePersonObjectUsingString(StringBuilder sBuilder, StringBuilderSegment segment){
        try(Scanner s = new Scanner(segment.takeStringCorrespondToSegment(sBuilder))) {
            s.useDelimiter(patternDelimiter);
            return new Person(Long.parseLong(s.next()), s.next(), s.next(), s.next());
        }
    }

    private static class StringBuilderSegment{
        int startPoint;
        int endPoint;

        StringBuilderSegment(int startP, int endP){
            startPoint = startP;
            endPoint = endP;
        }

        public String takeStringCorrespondToSegment(StringBuilder sBuilder){
            return sBuilder.substring(startPoint, endPoint);
        }

        public int getEndPoint() {
            return endPoint;
        }

        public int getStartPoint() {
            return startPoint;
        }
    }
}
