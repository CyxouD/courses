import java.sql.*;

/**
 * @author Ievgen Tararaka
 */

public class UsedCarsTradeDB {

    private static Connection connection;

    public UsedCarsTradeDB() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // или любой другой драйвер
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usedcarstrade", "root", "root"); // открытие соединения к базе
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
