import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 29.03.2016.
 */
public class CarAdDao {


    private static final String ANY = "%";
    private static final String DEFAULT_FROM_RELEASE = "0";
    private static final String DEFAULT_TO_RELEASE = "2016";
    private static final String DEFAULT_FROM_PRICE = "1";
    private static final String DEFAULT_TO_PRICE = "99999999";

    public static void add(Car car) throws SQLException {
        String car_vin = car.getVin ();
        double car_price = car.getPrice ();

        PreparedStatement preparedStatementInsertIntoCarAd = UsedCarsTradeDB.getConnection ().prepareStatement(CarAdQueries.INSERT_INTO_CAR_AD);
        preparedStatementInsertIntoCarAd.setString(1, car_vin);
        preparedStatementInsertIntoCarAd.setDouble (2, car_price);
        preparedStatementInsertIntoCarAd.execute();
    }

    public static void delete(String car_vin) throws SQLException {
        PreparedStatement preparedStatementSaveIdBeforeDelete = UsedCarsTradeDB.getConnection ().prepareStatement(CarAdQueries.SAVE_ID_BEFORE_DELETE);
        PreparedStatement preparedStatementDeleteFromCarAd = UsedCarsTradeDB.getConnection ().prepareStatement(CarAdQueries.DELETE_FROM_CAR_AD);

        preparedStatementSaveIdBeforeDelete.setString(1,car_vin);
        ResultSet resultSet = preparedStatementSaveIdBeforeDelete.executeQuery();
        resultSet.next();
        int owner_id = resultSet.getInt("car_owner");

        preparedStatementDeleteFromCarAd.setString(1, car_vin);
        preparedStatementDeleteFromCarAd.executeUpdate();
    }

    public static List getAllAds() throws SQLException {
            List data = null;
            ResultSet resultSet = null;
            PreparedStatement preparedStatementQueryNoParameters = UsedCarsTradeDB.getConnection ().prepareStatement(CarAdQueries.QUERY_WITHOUT_PARAMETERS);
            resultSet = preparedStatementQueryNoParameters.executeQuery();
            data = resultSetToList (resultSet, data);
            return data;
    }

    public static List getAllAdsOfSpecificUser(int owner_id) throws SQLException {
        List data = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatementQueryNoParametersUserAds = UsedCarsTradeDB.getConnection ().prepareStatement(CarAdQueries.QUERY_WITHOUT_PARAMETERS_USER_ADS);
        preparedStatementQueryNoParametersUserAds.setInt(1, owner_id);
        resultSet = preparedStatementQueryNoParametersUserAds.executeQuery();
        data = resultSetToList (resultSet, data);
        return data;
    }

    public static List getSpecificAds(CarSearchParameter[] carSearchParameters) throws SQLException {
        List data = null;
        String manufacturer = carSearchParameters[0].getParameter ();
        String modelName = carSearchParameters[1].getParameter ();
        String fromRelease = carSearchParameters[2].getParameter ();
        String toRelease = carSearchParameters[3].getParameter ();
        String fromPrice = carSearchParameters[4].getParameter ();
        String toPrice = carSearchParameters[5].getParameter ();
        if (manufacturer.isEmpty ()) manufacturer = ANY;
        if (modelName.isEmpty ()) modelName = ANY;
        if (fromRelease.isEmpty ()) fromRelease = DEFAULT_FROM_RELEASE;
        if (toRelease.isEmpty ()) toRelease = DEFAULT_TO_RELEASE;
        if (fromPrice.isEmpty ()) fromPrice = DEFAULT_FROM_PRICE;
        if (toPrice.isEmpty ()) toPrice = DEFAULT_TO_PRICE;

        PreparedStatement preparedStatementQueryWithParameters = UsedCarsTradeDB.getConnection ().prepareStatement(CarAdQueries.QUERY_WITH_PARAMETERS);
        preparedStatementQueryWithParameters.setString (1, manufacturer);
        preparedStatementQueryWithParameters.setString (2, modelName);
        preparedStatementQueryWithParameters.setInt (3, Integer.parseInt (fromRelease));
        preparedStatementQueryWithParameters.setInt (4, Integer.parseInt(toRelease));
        preparedStatementQueryWithParameters.setDouble (5, Double.parseDouble (fromPrice));
        preparedStatementQueryWithParameters.setDouble (6, Double.parseDouble (toPrice));
        ResultSet resultSet = preparedStatementQueryWithParameters.executeQuery ();
        data = resultSetToList (resultSet, data);
        return data;
    }


    private static List resultSetToList(ResultSet resultSet, List list) throws SQLException {
        list = new ArrayList<String> ();
        while (resultSet.next()){
            String car = resultSet.getString("car");
            list.add (car);
            double price = resultSet.getDouble("price");
            list.add (price);
            String information = resultSet.getString("information");
            list.add (information);
            String owner_phone = resultSet.getString("owner_phone");
            list.add (owner_phone);
            String car_manufacturer = resultSet.getString ("manufacturer");
            list.add (car_manufacturer);
            String car_model = resultSet.getString ("model_name");
            list.add (car_model);
            int car_release_year = resultSet.getInt ("release_year");
            list.add (car_release_year);
        }
        return list;
    }


}
