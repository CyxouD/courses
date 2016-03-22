package com.courses.spalah;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import com.courses.spalah.dao.Dao;
import com.courses.spalah.dao.PersonDao;
import com.courses.spalah.domain.Person;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * @author Ievgen Tararaka
 */
@RunWith(JUnit4.class)
public class PersonDaoTest {
    private static final String COLUMN_SEPARATOR = ";";
    private static final String ANY_CHARS = ".*";
    private static final String ANY_WHITE_SPACE = "\\s*";
    private static final String PATH_TO_FILE = "/persons.txt";

    private FileReader fileReader;
    private Dao<Person> personDao;

    Person person1;
    Person person2;
    Person person3;
    Person person4;

    @Before
    public void setUp() {
        fileReader = new FileReader("persons.txt");
        personDao = new PersonDao(fileReader);

        person1 = new Person(1L, "Agnes", "Calhoun", "USA, Cuyahoga Falls OH, 3608 Horner Street");
        person2 = new Person(2L, "Dawn", "Rutherford", "USA, Globe AZ, 206 Clarksburg Park Road");
        person3 = new Person(4L, "Janet", "Gilbert", "Canada, Duson, 4126 Hillside Drive");
        person4 = new Person(5L, "Ada", "Banks", "USA, Manchester NH, 4210 Shearwood Forest Drive");

        personDao.insert(person1);
        personDao.insert(person2);
        personDao.insert(person3);
        personDao.insert(person4);
    }

    @After
    public void cleanUp() {
        File file = new File(this.getClass().getResource(PATH_TO_FILE).getFile());
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
    }

    @Test
    public void testFindAll() {
        List<Person> persons = personDao.findAll();
        assertEquals(4, persons.size());

        assertEquals(person1, persons.get(0));
        assertEquals(person2, persons.get(1));
        assertEquals(person3, persons.get(2));
        assertEquals(person4, persons.get(3));

        assertTrue(fileContains(PATH_TO_FILE, person1));
        assertTrue(fileContains(PATH_TO_FILE, person2));
        assertTrue(fileContains(PATH_TO_FILE, person3));
        assertTrue(fileContains(PATH_TO_FILE, person4));
    }

    @Test
    public void testFindById() {
        Person person1 = new Person(4L, "Janet", "Gilbert", "Canada, Duson, 4126 Hillside Drive");

        Person personById1 = personDao.findById(4L);
        assertEquals(person1, personById1);

        Person personById2 = personDao.findById(10L);
        assertNull(personById2);
    }

    @Test
    public void testInsert() {
        Person newPerson = new Person(4L, "Jack", "Hooper", "USA, Colorado Springs CO, 935 Berry Street");
        assertFalse(personDao.insert(newPerson)); // false - row with this ID already exists

        newPerson.setId(10L);
        assertTrue(personDao.insert(newPerson)); // OK - new ID

        List<Person> persons = personDao.findAll();
        assertEquals(5, persons.size());

        assertTrue(persons.contains(newPerson));
        assertTrue(fileContains(PATH_TO_FILE, newPerson));
    }

    @Test
    public void testUpdate() {
        Person newPerson = new Person(10L, "Jack", "Hooper", "USA, Colorado Springs CO, 935 Berry Street");
        assertFalse(personDao.update(newPerson)); // false - no such row no update

        newPerson.setId(1L);
        assertTrue(personDao.update(newPerson)); // OK update old entity
        assertTrue(fileContains(PATH_TO_FILE, newPerson));

        Person updated = personDao.findById(1L);
        assertEquals(newPerson, updated);
    }

    @Test
    public void testRemove() {
        Person removed1 = personDao.remove(1L);
        Person removed2 = personDao.remove(2L);
        Person removed3 = personDao.remove(20L);

        assertEquals(2, personDao.findAll().size());
        assertEquals(person1, removed1);
        assertEquals(person2, removed2);
        assertNull(removed3);

        assertFalse(fileContains(PATH_TO_FILE, person1));
        assertFalse(fileContains(PATH_TO_FILE, person2));
    }

    private boolean fileContains(String pathToFile, Person... persons) {
        for (Person person : persons) {
            if (!personInText(person, pathToFile)) {
                return false;
            }
        }
        return true;
    }

    private boolean personInText(Person person, String pathToFile) {
        List<String> lines = getTextFromFile(pathToFile);
        for (String line : lines) {
            if (line.matches(toStringRepresentation(person))) {
                return true;
            }
        }
        return false;
    }

    private List<String> getTextFromFile(String pathToFile) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(pathToFile);
            return IOUtils.readLines(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    private String toStringRepresentation(Person person) {
        StringBuilder builder = new StringBuilder();
        builder.append(ANY_CHARS).append(ANY_WHITE_SPACE)
                .append(person.getId()).append(ANY_WHITE_SPACE).append(COLUMN_SEPARATOR).append(ANY_WHITE_SPACE)
                .append(person.getFirstName()).append(ANY_WHITE_SPACE).append(COLUMN_SEPARATOR).append(ANY_WHITE_SPACE)
                .append(person.getLastName()).append(ANY_WHITE_SPACE).append(COLUMN_SEPARATOR).append(ANY_WHITE_SPACE)
                .append(person.getAddress()).append(ANY_WHITE_SPACE).append(COLUMN_SEPARATOR)
                .append(ANY_WHITE_SPACE).append(ANY_CHARS);
        return builder.toString();
    }
}
