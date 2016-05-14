package db;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dima on 30.03.2016.
 */

//addOld exception when connection was not opened
public class DatabaseOperations {
    public static void addCarAd(Car car, int user_id) throws SQLException {
        CarDao.addOld(car, user_id);
        CarAdDao.addOld(car);
    }

    public static void deleteCarAd(String car_vin){
        try {
            CarAdDao.delete (car_vin);
            CarDao.delete (car_vin);
        } catch(SQLException e){
            e.printStackTrace ();
        }
    }

    public static Object[][] getAllCarAds(){
        Object[][] data = null;
        try {
            data = listToObjectArray (CarAdDao.getAllAds ());
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return data;
    }
    
    public static Object[][] getUserCarAds(int owner_id){
        Object[][] data = null;
        try {
            data = listToObjectArray (CarAdDao.getAllAdsOfSpecificUser (owner_id));
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return data;
        
    }

    public static Object[][] getSearchedCarAds(CarSearchParameter[] carSearchParameters){
        Object[][] data = null;
        try {
            data = listToObjectArray (CarAdDao.getSpecificOldAds(carSearchParameters));
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return data;
    }

    private static Object[][] listToObjectArray(List list){
        int nColumns = UsedCarsTradeClient.numberOfColumns;
        Object[][] data = new Object[list.size () / nColumns][nColumns];

        int icolumn = 0;
        for (int i = 0; i < list.size (); i ++){
            if (icolumn == nColumns) icolumn = 0;
            data[i / nColumns][icolumn] = list.get (i);
            icolumn++;
        }
        return data;
    }

    public static void signUpUser(User user){
        try {
            UserDao.addOld(user);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
}
