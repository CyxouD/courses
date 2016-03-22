package com.courses.spalah.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.courses.spalah.FileReader;
import com.courses.spalah.domain.Person;

/**
 * Implement this file. Work with DB simulation
 *
 * @author Ievgen Tararaka
 */
public class PersonDao implements Dao<Person> {
    FileReader fileReader;

    public PersonDao(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public List<Person> findAll() {
        List<Person> listPerson = new ArrayList<>();
        try(Scanner scanner = new Scanner(fileReader.readFile())) {
            scanner.useDelimiter(PersonDaoHelper.patternDelimiter);

            long personId;
            String personFirstName;
            String personLastName;
            String personAddress;
            while (scanner.hasNext()) {
                personId = Long.parseLong(scanner.next());
                personFirstName = scanner.next();
                personLastName = scanner.next();
                personAddress = scanner.next();
                listPerson.add(new Person(personId, personFirstName, personLastName, personAddress));
            }

            return listPerson;
        }
    }

    @Override
    public Person findById(Long id) {
        try(Scanner scanner = new Scanner(fileReader.readFile())) {
            scanner.useDelimiter(PersonDaoHelper.patternDelimiter);

            long personId;
            String personFirstName;
            String personLastName;
            String personAddress;
            while (scanner.hasNext()) {
                personId = Long.parseLong(scanner.next());
                if (personId == id) {
                    personFirstName = scanner.next();
                    personLastName = scanner.next();
                    personAddress = scanner.next();
                    return new Person(personId, personFirstName, personLastName, personAddress);
                } else {
                    scanner.nextLine();
                }
            }
            return null;
        }
    }

    @Override
    public boolean update(Person entity) {
        StringBuilder outputFileInfo = new StringBuilder().append(fileReader.readFile());

        boolean isUpdated = PersonDaoHelper.replaceStrCorrespondToIdWithNewData(outputFileInfo, entity);
        if (!isUpdated){
            return false;
        }
        else {
            fileReader.writeToThisFile(outputFileInfo,fileReader.getPathToFile());
            return true;
        }
    }

    @Override
    public boolean insert(Person entity) {
        StringBuilder outputFileInfo = new StringBuilder().append(fileReader.readFile());
        boolean isInserted= PersonDaoHelper.insertStr(outputFileInfo, entity);
        if (!isInserted){
            return false;
        }
        else {
            fileReader.writeToThisFile(outputFileInfo,fileReader.getPathToFile());
            return true;
        }
    }

    @Override
    public Person remove(Long id) {
        StringBuilder outputFileInfo = new StringBuilder().append(fileReader.readFile());

        Person deletedPerson = PersonDaoHelper.deleteStr(outputFileInfo, id);
        if (deletedPerson == null){
            return null;
        }
        else {
            fileReader.writeToThisFile(outputFileInfo,fileReader.getPathToFile());
            return deletedPerson;
        }
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }
}
