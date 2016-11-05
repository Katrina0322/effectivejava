package singleton;

/**
 * Created by spark on 11/5/16.
 */
public class Singleton4 {
    private static Singleton4 uniqueSingleton4 = new Singleton4();

    private Singleton4() {
    }

    private static class SingletonFactory{
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return SingletonFactory.INSTANCE;
    }
}
