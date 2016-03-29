import java.sql.*;

/**
 * Created by Dima on 27.03.2016.
 */
public class UsedCarsTradeImpl {

    private static final String QUERY_WITHOUT_PATARAMETERS = "Select car,price,information,owner_phone,manufacturer,model_name,release_year\n"+
            "From car_ad \n"+
            " INNER JOIN\n"+
            "\tcar\n"+
            "On car.vin = car_ad.car\n"+
            " INNER JOIN\n"+
            "\tcar_owner\n"+
            "    On car_owner = owner_id;";

    private static final String QUERY_WITH_PARAMETERS = "Select car,price,information,owner_phone,manufacturer,model_name,release_year\n" +
            "From car_ad \n" +
            " INNER JOIN\n" +
            "\tcar\n" +
            "On car.vin = car_ad.car\n" +
            " INNER JOIN\n" +
            "\tcar_owner\n" +
            "    On car_owner = owner_id\n" +
            "    where manufacturer Like ? AND model_name Like ? AND release_year BETWEEN ? AND ? AND PRICE BETWEEN ? AND ?;";


    private static final String INSERT_INTO_CAR_OWNER = "Insert into car_owner (owner_name, owner_surname, owner_phone) values (?, ?, ?);";
    private static final String GET_LAST_ID = "SET @last_id_in_car_owner = LAST_INSERT_ID();";
    private static final String INSERT_INTO_CAR = "Insert into car (VIN, manufacturer, model_name, release_year, information, car_owner) values (?,?,?,?,?,@last_id_in_car_owner);";
    private static final String INSERT_INTO_CAR_AD = "Insert into car_ad values (?, ?);";

    private static final String SAVE_ID_BEFORE_DELETE = "SELECT car_owner from car where vin = ?";
    private static final String DELETE_FROM_CAR = "DELETE from car where VIN = ?";
    private static final String DELETE_FROM_CAR_OWNER = "DELETE from car_owner where owner_id = ?";
    private static final String DELETE_FROM_CAR_AD = "DELETE from car_ad where car = ?";

    private static final String SELECT_SPECIFIC_ID = "SELECT vin from car where vin = ?";

    private static final String ANY = "%";
    private static final String DEFAULT_FROM_RELEASE = "0";
    private static final String DEFAULT_TO_RELEASE = "2016";
    private static final String DEFAULT_FROM_PRICE = "1";
    private static final String DEFAULT_TO_PRICE = "99999999";

    private static PreparedStatement preparedStatementQueryWithParameters;
    private static PreparedStatement preparedStatementQueryNoParameters;

    private static PreparedStatement preparedStatementSaveIdBeforeDelete;
    private static PreparedStatement preparedStatementDeleteFromCar;
    private static PreparedStatement preparedStatementDeleteFromCarOwner;
    private static PreparedStatement preparedStatementDeleteFromCarAd;

    private static PreparedStatement preparedStatementInsertIntoCarOwner;
    private static PreparedStatement preparedStatementGetLastId;
    private static PreparedStatement preparedStatementInsertIntoCar;
    private static PreparedStatement preparedStatementInsertIntoCarAd;
    private static PreparedStatement preparedStatementGetVin;

    private static Connection dbConnection;



    public static void init(){
        initConnection();
        initPreparedStatements();
    }

    public static void closePreparedStatements(){
        try {
            preparedStatementDeleteFromCar.close ();
            preparedStatementDeleteFromCarAd.close ();
            preparedStatementSaveIdBeforeDelete.close ();
            preparedStatementDeleteFromCarOwner.close ();
            preparedStatementSaveIdBeforeDelete.close ();
            preparedStatementQueryWithParameters.close ();
            preparedStatementQueryNoParameters.close ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public static Object[][] preparedStatementQueryWithParameters(String manufacturer, String modelName, String fromRelease,
                                                                  String toRelease, String fromPrice, String toPrice) {
        Object[][] data = null;
        try {
            if (manufacturer.isEmpty ()) manufacturer = ANY;
            if (modelName.isEmpty ()) modelName = ANY;
            if (fromRelease.isEmpty ()) fromRelease = DEFAULT_FROM_RELEASE;
            if (toRelease.isEmpty ()) toRelease = DEFAULT_TO_RELEASE;
            if (fromPrice.isEmpty ()) fromPrice = DEFAULT_FROM_PRICE;
            if (toPrice.isEmpty ()) toPrice = DEFAULT_TO_PRICE;
            preparedStatementQueryWithParameters.setString (1, manufacturer);
            preparedStatementQueryWithParameters.setString (2, modelName);
            preparedStatementQueryWithParameters.setInt (3, Integer.parseInt (fromRelease));
            preparedStatementQueryWithParameters.setInt (4, Integer.parseInt(toRelease));
            preparedStatementQueryWithParameters.setDouble (5, Double.parseDouble (fromPrice));
            preparedStatementQueryWithParameters.setDouble (6, Double.parseDouble (toPrice));
            ResultSet resultSet = preparedStatementQueryWithParameters.executeQuery ();
            data = resultSetToArray (resultSet, data);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return data;
    }

    public static Object[][] preparedStatementQueryNoParameters() {
        Object[][] data = null;
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatementQueryNoParameters.executeQuery();
            data = resultSetToArray (resultSet, data);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return data;
    }

    public static void preparedStatementUpdate(String car_vin){
        try{
            preparedStatementSaveIdBeforeDelete.setString(1,car_vin);
            ResultSet resultSet = preparedStatementSaveIdBeforeDelete.executeQuery();
            resultSet.next();
            int owner_id = resultSet.getInt("car_owner");

            preparedStatementDeleteFromCarAd.setString(1, car_vin);
            preparedStatementDeleteFromCarAd.executeUpdate();

            preparedStatementDeleteFromCar.setString(1, car_vin);
            preparedStatementDeleteFromCar.executeUpdate();

            preparedStatementDeleteFromCarOwner.setInt(1,owner_id);
            preparedStatementDeleteFromCarOwner.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean preparedStatementAdd(String owner_name, String owner_surname, int owner_phone, String car_vin,
                                             String car_manufacturer, String car_model_name, int car_release_year,
                                             String information, double car_price){
        try{
            if (!isVinUnique (car_vin)) return false;
            preparedStatementInsertIntoCarOwner.setString(1,owner_name);
            preparedStatementInsertIntoCarOwner.setString(2,owner_surname);
            preparedStatementInsertIntoCarOwner.setInt(3,owner_phone);
            preparedStatementInsertIntoCarOwner.execute();

            preparedStatementGetLastId.execute();

            preparedStatementInsertIntoCar.setString(1,car_vin);
            preparedStatementInsertIntoCar.setString(2,car_manufacturer);
            preparedStatementInsertIntoCar.setString(3,car_model_name);
            preparedStatementInsertIntoCar.setInt(4,car_release_year);
            preparedStatementInsertIntoCar.setString(5, information);
            preparedStatementInsertIntoCar.execute();

            preparedStatementInsertIntoCarAd.setString(1, car_vin);
            preparedStatementInsertIntoCarAd.setDouble (2, car_price);
            preparedStatementInsertIntoCarAd.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static void initConnection(){
        dbConnection = new UsedCarsTradeDB().getConnection ();
    }


    private static void initPreparedStatements(){
        try {
            preparedStatementQueryWithParameters = dbConnection.prepareStatement(QUERY_WITH_PARAMETERS);
            preparedStatementQueryNoParameters = dbConnection.prepareStatement(QUERY_WITHOUT_PATARAMETERS);
            preparedStatementSaveIdBeforeDelete = dbConnection.prepareStatement(SAVE_ID_BEFORE_DELETE);
            preparedStatementDeleteFromCar = dbConnection.prepareStatement(DELETE_FROM_CAR);
            preparedStatementDeleteFromCarOwner = dbConnection.prepareStatement(DELETE_FROM_CAR_OWNER);
            preparedStatementDeleteFromCarAd = dbConnection.prepareStatement(DELETE_FROM_CAR_AD);
            preparedStatementInsertIntoCarOwner = dbConnection.prepareStatement(INSERT_INTO_CAR_OWNER);
            preparedStatementGetLastId = dbConnection.prepareStatement(GET_LAST_ID);
            preparedStatementInsertIntoCar = dbConnection.prepareStatement(INSERT_INTO_CAR);
            preparedStatementInsertIntoCarAd = dbConnection.prepareStatement(INSERT_INTO_CAR_AD);
            preparedStatementGetVin = dbConnection.prepareStatement (SELECT_SPECIFIC_ID);

        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    private static boolean isVinUnique(String vin) throws  SQLException{
        preparedStatementGetVin.setString (1,vin);
        ResultSet resultSet = preparedStatementGetVin.executeQuery ();
        return !resultSet.next ();
    }


        /*
        *  JTable in UsedCarsTradeClient used this array to display data
        */

    private static Object[][] resultSetToArray(ResultSet resultSet, Object[][] data) throws  SQLException{
        resultSet.last ();
        int rows = resultSet.getRow ();
        resultSet.beforeFirst ();
        data = new Object[rows][UsedCarsTradeClient.numberOfColumns];
        int nRow = 0;
        while (resultSet.next()){
            String car = resultSet.getString("car");
            data[nRow][0] = car;
            double price = resultSet.getDouble("price");
            data[nRow][1] = price;
            String information = resultSet.getString("information");
            data[nRow][2] = information;
            String owner_phone = resultSet.getString("owner_phone");
            data[nRow][3] = owner_phone;
            String car_manufacturer = resultSet.getString ("manufacturer");
            data[nRow][4] = car_manufacturer;
            String car_model = resultSet.getString ("model_name");
            data[nRow][5] = car_model;
            int car_release_year = resultSet.getInt ("release_year");
            data[nRow][6] = car_release_year;
            nRow++;
        }
        return data;
    }

}
