package adapter;

/**
 * Created by spark on 11/6/16.
 */
public class Client {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.sampleOperation1();
        target.sampleOperation2();
    }
}
