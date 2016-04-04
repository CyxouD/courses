package db;

/**
 * Created by Dima on 29.03.2016.
 */
public class CarAdQueries {

    public static final String QUERY_WITHOUT_PARAMETERS = "Select car,price,information,owner_phone,manufacturer,model_name,release_year\n"+
            "From car_ad \n"+
            " INNER JOIN\n"+
            "\tcar\n"+
            "On car.vin = car_ad.car\n"+
            " INNER JOIN\n"+
            "\tcar_owner\n"+
            "    On car_owner = owner_id;";

    public static final String QUERY_WITH_PARAMETERS = "Select car,price,information,owner_phone,manufacturer,model_name,release_year\n" +
            "From car_ad \n" +
            " INNER JOIN\n" +
            "\tcar\n" +
            "On car.vin = car_ad.car\n" +
            " INNER JOIN\n" +
            "\tcar_owner\n" +
            "    On car_owner = owner_id\n" +
            "    where manufacturer Like ? AND model_name Like ? AND release_year BETWEEN ? AND ? AND PRICE BETWEEN ? AND ?;";

    public static final String QUERY_WITHOUT_PARAMETERS_USER_ADS = "Select car,price,information,owner_phone,manufacturer,model_name,release_year\n"+
            "From car_ad \n"+
            " INNER JOIN\n"+
            "\tcar\n"+
            "On car.vin = car_ad.car\n"+
            " INNER JOIN\n"+
            "\tcar_owner\n"+
            "    On car_owner = owner_id\n" +
            "    where owner_id = ?";

    public static final String INSERT_INTO_CAR_AD = "Insert into car_ad values (?, ?);";

    public static final String SAVE_ID_BEFORE_DELETE = "SELECT car_owner from car where vin = ?";
    public static final String DELETE_FROM_CAR_AD = "DELETE from car_ad where car = ?";
}
