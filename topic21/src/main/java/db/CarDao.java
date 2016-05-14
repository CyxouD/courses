package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Dima on 29.03.2016.
 */
public class CarDao {

    public Collection getAllCars() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        java.util.List cars = null;
        try{
            tx = session.beginTransaction();
            cars = session.createCriteria(CarNew.class).list();
            for (Iterator iterator =
                 cars.iterator(); iterator.hasNext();){
                CarNew carNew = (CarNew) iterator.next();
                System.out.print("Car vin: " + carNew.getVin());
                System.out.print("car model: " + carNew.getModel());
                System.out.println("car manufacturer: " + carNew.getManufacturer());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return cars;
    }

    public static void addOld(Car car, int user_id) throws SQLException {
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

    public static void add(CarNew car) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        tx = session.beginTransaction();
        session.save(car);
        tx.commit();
    }

    public static void delete(String car_vin) throws SQLException {
        PreparedStatement preparedStatementDeleteFromCar = UsedCarsTradeDB.getConnection ().prepareStatement(CarQueries.DELETE_FROM_CAR);

        preparedStatementDeleteFromCar.setString(1, car_vin);
        preparedStatementDeleteFromCar.executeUpdate();
    }
}
