package db;

/**
 * Created by Dima on 29.03.2016.
 */
public class CarSearchParameter {
    String parameter;

    public CarSearchParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return parameter;
    }

    @Override
    public boolean equals(Object obj) {
        return this.parameter.equals(((CarSearchParameter) obj).getParameter());
    }
}
