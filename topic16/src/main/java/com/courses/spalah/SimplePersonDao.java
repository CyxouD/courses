package com.courses.spalah;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ievgen Tararaka
 */
public class SimplePersonDao {
    private Connection connection;

    public SimplePersonDao(Connection connection) {
        this.connection = connection;
    }

    public Person read(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM persons WHERE id=?");
        statement.setLong(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            Person person = new Person();
            person.firstName = rs.getString(1);
            person.age = rs.getInt(2);
            return person;
        }
        return null;
    }
}
