package db;

import com.courses.spalah.ServletConstants;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Dima on 07.05.2016.
 */
public class test {

    public static void main(String... args){
        addCar();
    }

    private static void addCar(){
        try {
            CarNew car = new CarNew("trhert44343", "wowow", "Ziziz", 666,"hoho", 999);
            car.setVin("5464561231rgregeregewrqwqw1111");
            Factory.getInstance().getCarDao().add(car);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static  void searchCarWithParam(){
        Collection carAds = null;
        try {
            CarSearchParameter[] carSearchParameters = new CarSearchParameter[ServletConstants.NUMBER_OF_SEARCH_CRITERIA];
            carSearchParameters[0] = new CarSearchParameter("");
            carSearchParameters[1] = new CarSearchParameter("");
            carSearchParameters[2] = new CarSearchParameter("20");
            carSearchParameters[3] = new CarSearchParameter("");
            carSearchParameters[4] = new CarSearchParameter("");
            carSearchParameters[5] = new CarSearchParameter("");
            carAds = Factory.getInstance().getCarAdDao().getSpecificAds(carSearchParameters);
            //System.out.println("CAR OWNERS");
            //carOwners = Factory.getInstance().getUserDao().getAllCarOwners();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
