package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Dima on 29.03.2016.
 */
public class UserDao {

    public Collection getAllCarOwners() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        java.util.List carUsers = null;
        try{
            tx = session.beginTransaction();
            carUsers = session.createCriteria(CarOwner.class).list();
            for (Iterator iterator =
                 carUsers.iterator(); iterator.hasNext();){
                CarOwner carOwner = (CarOwner) iterator.next();
                System.out.print("Owner id: " + carOwner.getOwner_id());
                System.out.print("owner name: " + carOwner.getName());
                System.out.print("owner surname: " + carOwner.getSurname());
                System.out.println("owner phone: " + carOwner.getPhone());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return carUsers;
    }

    public static User createRandomUser(){
        int phone = - new Random().nextInt()*10000;
        User user = new User("lol", "vasya", phone, new char[] {113});
        return user;
    }

    public static void addOld(User user) throws SQLException {
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

    public static void add(User user) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        tx = session.beginTransaction();
        int id = (int) session.save(user);
        tx.commit();

        user.setIdInDB(id);
    }

    public void deleteUser(User user) {

    }

}
