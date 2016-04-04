package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dima on 29.03.2016.
 */
public class UserDao {

    public void changeContacts(User user) {

    }

    public void signInUser(User user) {

    }

    public static void add(User user) throws SQLException {
        PreparedStatement preparedStatementInsertIntoCarOwner = UsedCarsTradeDB.getConnection ().
                prepareStatement(UserQueries.INSERT_INTO_CAR_OWNER,
                                Statement.RETURN_GENERATED_KEYS);
        String owner_name = user.getUserName ();
        String owner_surname = user.getUserSurname ();
        int owner_phone = user.getPhone ();
        preparedStatementInsertIntoCarOwner.setString(1,owner_name);
        preparedStatementInsertIntoCarOwner.setString(2,owner_surname);
        preparedStatementInsertIntoCarOwner.setInt(3,owner_phone);
        preparedStatementInsertIntoCarOwner.execute();

        ResultSet generatedKeys = preparedStatementInsertIntoCarOwner.getGeneratedKeys();
        generatedKeys.next ();
        int id = generatedKeys.getInt(1);
        user.setIdInDB(id);
    }

    public void deleteUser(User user) {

    }

}
