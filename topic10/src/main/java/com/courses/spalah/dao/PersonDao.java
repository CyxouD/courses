package com.courses.spalah.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        List<Person> listPerson = new ArrayList<Person>();

        Scanner scanner = new Scanner(fileReader.readFile());
        scanner.useDelimiter(PersonDaoHelper.patternDelimiter);

        long personId;
        String personFirstName;
        String personLastName;
        String personAddress;
        while (scanner.hasNext()){
            personId = Long.parseLong(scanner.next());
            personFirstName = scanner.next();
            personLastName = scanner.next();
            personAddress = scanner.next();
            listPerson.add(new Person(personId, personFirstName, personLastName, personAddress));
        }

        return listPerson;
    }

    @Override
    public Person findById(Long id) {
        Scanner scanner = new Scanner(fileReader.readFile());
        scanner.useDelimiter(PersonDaoHelper.patternDelimiter);

        long personId;
        String personFirstName;
        String personLastName;
        String personAddress;
        while (scanner.hasNext()){
            personId = Long.parseLong(scanner.next());
            if (personId == id){
                personFirstName = scanner.next();
                personLastName = scanner.next();
                personAddress = scanner.next();
                scanner.close();
                return new Person(personId,personFirstName,personLastName,personAddress);
            }
            else{
                scanner.nextLine();
            }
        }
        scanner.close();
        return null;
    }

    @Override
    public boolean update(Person entity) {
        StringBuilder outputFileInfo = new StringBuilder().append(fileReader.readFile());

        boolean isUpdated = PersonDaoHelper.replaceStrCorrespondToIdWithNewData(outputFileInfo, entity);
        if (!isUpdated){
            return false;
        }
        else {
            PersonDaoHelper.writeToFile(outputFileInfo,fileReader.getPathToFile());
            return true;
        }
    }

    @Override
    public boolean insert(Person entity) {
        StringBuilder outputFileInfo = new StringBuilder().append(fileReader.readFile());

        boolean isInserted= PersonDaoHelper.insertNewStr(outputFileInfo, entity);
        if (!isInserted){
            return false;
        }
        else {
            PersonDaoHelper.writeToFile(outputFileInfo,fileReader.getPathToFile());
            return true;
        }
    }

    @Override
    public Person remove(Long id) {
        return null;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }
}
