package proxy;

import java.lang.reflect.Proxy;

/**
 * used to
 * Created by tianjin on 11/7/16.
 */
public class ProxyTest {
    public static void main(String[] args) {
        Sourceable source = new ProxyObject();
        source.method();

        ProxyHandler handler = new ProxyHandler(source);
        Sourceable sourceable = (Sourceable) Proxy.newProxyInstance(ProxyObject.class.getClassLoader(),source.getClass().getInterfaces(),handler);
        sourceable.method();
    }
}
