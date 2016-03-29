/**
 * Created by Dima on 29.03.2016.
 */
public class User {
    private String userName;
    private String userSurname;
    private int phone;
    private int password;
    private int idInDB;

    public void setIdInDB(int idInDB) {
        this.idInDB = idInDB;
    }

    public int getIdInDB() {
        return idInDB;
    }

    public User(String userName, String userSurname, int phone, int password) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.phone = phone;
        this.password = password;

    }

    public void changePassword(){

    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public int getPhone() {
        return phone;
    }

    public int getPassword() {
        return password;
    }
}
