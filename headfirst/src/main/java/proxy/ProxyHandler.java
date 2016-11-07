package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * used to
 * Created by tianjin on 11/7/16.
 */
public class ProxyHandler implements InvocationHandler {
    private Sourceable sourceable;
    public ProxyHandler(Sourceable subject){
        this.sourceable = subject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(sourceable, args);
        after();
        return null;
    }

    private void after() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }
}
