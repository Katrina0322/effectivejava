package decorate;

/**
 * Created by ubuntu on 3/10/16.
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "houseblend";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
