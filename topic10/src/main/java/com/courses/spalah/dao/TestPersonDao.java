package com.courses.spalah.dao;

import com.courses.spalah.FileReader;
import com.courses.spalah.domain.Person;

/**
 * Created by Dima on 21.03.2016.
 */
public class TestPersonDao {
    public static void main(String[] args){
        FileReader fileReader = new FileReader("topic10\\src\\main\\resources\\persons_example.txt");
        PersonDao personDao = new PersonDao(fileReader);
        //System.out.println(personDao.findAll());
        //System.out.println(personDao.findById(2L));
        //personDao.update(new Person(4L,"Dima", "Zasuha","Dnepr"));
        System.out.println(personDao.insert(new Person(6L,"Dima", "Zasuha","Dnepr")));
    }
}
