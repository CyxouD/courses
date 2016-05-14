package db;

import java.util.Set;

/**
 * Created by Dima on 07.05.2016.
 */
public class CarOwner {
    private int owner_id;
    private String name;
    private String surname;
    private String phone;
    private Set cars;

    public void setCars(Set cars) {
        this.cars = cars;
    }

    public Set getCars() {

        return cars;
    }

    public CarOwner() {
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }
}

