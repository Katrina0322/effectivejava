package decorate;

/**
 * Created by ubuntu on 3/10/16.
 */
public abstract class Beverage {
    String description = "unknow description";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
