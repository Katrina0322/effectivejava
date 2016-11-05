package factorymethod;

/**
 * Created by spark on 11/5/16.
 */
public class FactoryB extends AbstractFactory {
    @Override
    public IProduct createProduct() {
        return new ConcreteProductB();
    }
}
