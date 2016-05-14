package db;

/**
 * Created by Dima on 29.03.2016.
 */
public class CarQueries {
    public static final String INSERT_INTO_CAR = "Insert into car (VIN, manufacturer, model_name, release_year, information, car_owner) values (?,?,?,?,?,?);";

    public static final String DELETE_FROM_CAR = "DELETE from car where VIN = ?";
}
