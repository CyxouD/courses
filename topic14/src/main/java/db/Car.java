package db;

/**
 * Created by Dima on 29.03.2016.
 */
public class Car {
    private String vin;
    private String manufacturer;
    private String model;
    private int releaseYear;
    private String information;
    private double price;

    public Car(String vin, String manufacturer, String model, int releaseYear, String information, double price) {
        this.vin = vin;
        this.manufacturer = manufacturer;
        this.model = model;
        this.releaseYear = releaseYear;
        this.information = information;
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getInformation() {
        return information;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "vin='" + vin + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", releaseYear=" + releaseYear +
                ", information='" + information + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Car thatCar = (Car) obj;
        return this.getVin().equals(thatCar.getVin());
    }
}
