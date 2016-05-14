package db;

/**
 * Created by Dima on 07.05.2016.
 */
public class CarNew {
    private String vin;
    private String manufacturer;
    private String model;
    private int releaseYear;
    private String information;
    private int carOwner;

    public CarNew() {
    }

    public CarNew(String vin, String manufacturer, String model, int releaseYear, String information) {
        this.vin = vin;
        this.manufacturer = manufacturer;
        this.model = model;
        this.releaseYear = releaseYear;
        this.information = information;
    }

    public CarNew(String vin, String manufacturer, String model, int releaseYear, String information, int carOwner) {
        this.vin = vin;
        this.manufacturer = manufacturer;
        this.model = model;
        this.releaseYear = releaseYear;
        this.information = information;
        this.carOwner = carOwner;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setCarOwner(int carOwner) {
        this.carOwner = carOwner;
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

    public int getCarOwner() {
        return carOwner;
    }
}
