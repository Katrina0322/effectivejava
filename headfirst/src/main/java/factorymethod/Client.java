package factorymethod;

/**
 * Created by spark on 11/5/16.
 */
public class Client {
    public static void main(String[] args) {
        Factory factory = new Factory();
        ConcreteProductA a = factory.createProduct(ConcreteProductA.class);
        ConcreteProductB b = factory.createProduct(ConcreteProductB.class);
        a.method01();
        b.method01();
    }
}
