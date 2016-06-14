package decorate;


/**
 *
 * Created by ubuntu on 3/10/16.
 */
public class Mocha extends CondimentDecorator {
    private Beverage beverage;
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+"mocha";
    }

    @Override
    public double cost() {
        return 0.20+beverage.cost();
    }
}
