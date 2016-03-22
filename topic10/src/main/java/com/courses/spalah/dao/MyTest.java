package com.courses.spalah.dao;

import com.courses.spalah.FileReader;
import com.courses.spalah.domain.Person;

import java.util.List;

/**
 * Created by Dima on 21.03.2016.
 */
public class MyTest {
    public static void main(String[] args){
        //FileReader fileReader = new FileReader("topic10\\src\\main\\resources\\persons_example.txt");
        //PersonDao personDao = new PersonDao(fileReader);
        //System.out.println(personDao.findAll());
        //System.out.println(personDao.findById(2L));
       // personDao.update(new Person(4L,"Dima", "Zasuha","Dnepr"));
       // System.out.println(personDao.insert(new Person(6L,"Dima", "Zasuha","Dnepr")));
        //System.out.println(personDao.remove(4L));
        FileReader fileReader = new FileReader("persons_example.txt");
        PersonDao personDao = new PersonDao(fileReader);

        Person person1 = new Person(1L, "Agnes", "Calhoun", "USA, Cuyahoga Falls OH, 3608 Horner Street");
        Person person2 = new Person(2L, "Dawn", "Rutherford", "USA, Globe AZ, 206 Clarksburg Park Road");
        Person person3 = new Person(4L, "Janet", "Gilbert", "Canada, Duson, 4126 Hillside Drive");
        Person person4 = new Person(5L, "Ada", "Banks", "USA, Manchester NH, 4210 Shearwood Forest Drive");

        System.out.println(personDao.insert(person1));
        //personDao.insert(person2);
        //personDao.insert(person3);
        //personDao.insert(person4);
        List<Person> persons = personDao.findAll();
    }
}
