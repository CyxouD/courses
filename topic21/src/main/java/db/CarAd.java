package db;

/**
 * Created by Dima on 07.05.2016.
 */
public class CarAd {
    private String carVin;
    private Double price;


    public CarAd(){

    }

    public CarAd(String carVin, Double price) {
        this.carVin = carVin;
        this.price = price;
    }

    public String getCarVin() {
        return carVin;
    }

    public Double getPrice() {
        return price;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
