package com.courses.spalah.list;

/**
 * Вам необходимо понять, что не хватаем этому классу, чтобы он имел возможность
 * учавствовать в коллекциях (правильно)
 *
 * @author Ievgen Tararaka
 */
public class Car {
    private int priceInDollars;
    private String modelName;

    public Car(int priceInDollars, String modelName) {
        this.priceInDollars = priceInDollars;
        this.modelName = modelName;
    }

    public String toString(){
        return priceInDollars + ": " + modelName;
    }

    public boolean equals(Object c){
        if (c == null) return false;
        if (!(c instanceof  Car)) return false;

        Car that = (Car) c;
        if (this.priceInDollars == that.priceInDollars && this.modelName.equals(that.modelName)) return true;
        return false;
    }

    public int hashCode(){
        return Integer.hashCode(priceInDollars)+modelName.hashCode();
    }

    public int getPriceInDollars() {
        return priceInDollars;
    }

    public void setPriceInDollars(int priceInDollars) {
        this.priceInDollars = priceInDollars;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
