package db;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ievgen Tararaka
 */

public class UsedCarsTradeDB {

    private static Connection connection;

    public static void init(){
        if (connection == null) {
            try {
                DriverManager.registerDriver(new Driver()); // или любой другой драйвер
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usedcarstrade", "root", "root"); // открытие соединения к базе
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
            return connection;
    }

    public static void closeConnection(){
        try {
            if (!connection.isClosed()) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
