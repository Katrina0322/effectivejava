package proxy;

/**
 * used to
 * Created by tianjin on 11/7/16.
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
