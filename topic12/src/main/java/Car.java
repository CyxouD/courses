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
}
