package db;

/**
 * Created by Dima on 07.05.2016.
 */
public class Factory {

    private static CarDao carDao = null;
    private static CarAdDao carAdDao = null;
    private static UserDao userDao = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public CarDao getCarDao(){
        if (carDao == null){
            carDao = new CarDao();
        }
        return carDao;
    }

    public CarAdDao getCarAdDao(){
        if (carAdDao == null){
            carAdDao = new CarAdDao();
        }
        return carAdDao;
    }

    public UserDao getUserDao(){
        if (userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }
}