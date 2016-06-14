package decorate;

/**
 * Created by ubuntu on 3/10/16.
 */
public class Espresso extends Beverage {
    public Espresso() {
        description = "espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
