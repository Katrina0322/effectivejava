package decorate;

/**
 * Created by ubuntu on 3/10/16.
 */
public class Soy extends CondimentDecorator {
    private Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "soy";
    }

    @Override
    public double cost() {
        return 0.8 + beverage.cost();
    }
}
