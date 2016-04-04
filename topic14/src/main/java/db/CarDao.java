package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dima on 29.03.2016.
 */
public class CarDao {

    public static void add(Car car, int user_id) throws SQLException {
        PreparedStatement preparedStatementInsertIntoCar = UsedCarsTradeDB.getConnection ().prepareStatement(CarQueries.INSERT_INTO_CAR);
        String car_vin = car.getVin ();
        String car_manufacturer = car.getManufacturer ();
        String car_model_name = car.getModel ();
        int car_release_year = car.getReleaseYear ();
        String information = car.getInformation ();
        preparedStatementInsertIntoCar.setString(1,car_vin);
        preparedStatementInsertIntoCar.setString(2,car_manufacturer);
        preparedStatementInsertIntoCar.setString(3,car_model_name);
        preparedStatementInsertIntoCar.setInt(4,car_release_year);
        preparedStatementInsertIntoCar.setString(5, information);
        preparedStatementInsertIntoCar.setInt (6, user_id);
        preparedStatementInsertIntoCar.execute();
    }

    public static void delete(String car_vin) throws SQLException {
        PreparedStatement preparedStatementDeleteFromCar = UsedCarsTradeDB.getConnection ().prepareStatement(CarQueries.DELETE_FROM_CAR);

        preparedStatementDeleteFromCar.setString(1, car_vin);
        preparedStatementDeleteFromCar.executeUpdate();
    }
}
