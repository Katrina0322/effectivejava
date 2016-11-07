package proxy;

/**
 * used to
 * Created by tianjin on 11/7/16.
 */
public class ProxyObject implements Sourceable {
    private Source source;
    public ProxyObject(){
        super();
        this.source = new Source();
    }
    @Override
    public void method() {
        before();
        source.method();
        after();
    }
    private void after() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }
}
