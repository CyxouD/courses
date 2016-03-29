/**
 * Created by Dima on 29.03.2016.
 */
public class UserQueries {

    public static final String INSERT_INTO_CAR_OWNER = "Insert into car_owner (owner_name, owner_surname, owner_phone) values (?, ?, ?);";
    public static final String DELETE_FROM_CAR_OWNER = "DELETE from car_owner where owner_id = ?";
}
