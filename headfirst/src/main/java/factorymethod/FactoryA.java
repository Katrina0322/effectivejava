package factorymethod;

/**
 * Created by spark on 11/5/16.
 */
public class FactoryA extends AbstractFactory{

    @Override
    public IProduct createProduct() {
        return new ConcreteProductA();
    }
}
