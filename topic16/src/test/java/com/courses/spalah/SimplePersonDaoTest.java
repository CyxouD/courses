package com.courses.spalah;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Ievgen Tararaka
 */
@RunWith(MockitoJUnitRunner.class)
public class SimplePersonDaoTest {
    @Test
    public void testRead() throws SQLException {
        Connection connection = Mockito.mock(Connection.class); // создаем мок для соединения
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class); // создаем мок для стейтмента
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        SimplePersonDao simplePersonDao = new SimplePersonDao(connection); // создаем дао с мок-обьектом

        when(connection.prepareStatement(eq("SELECT * FROM persons WHERE id=?"))).thenReturn(preparedStatement); // задаем поведение для мока
        when(preparedStatement.executeQuery()).thenReturn(resultSet); // делаем так, что у нас не выпадает null-pointer

        simplePersonDao.read(1l); // вызываем дао

        verify(preparedStatement, times(1)).setLong(eq(1), eq(1L)); // проверяем поведение
    }
}
