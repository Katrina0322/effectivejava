package singleton;

/**
 * Created by ubuntu on 3/14/16.
 */
public class Singleton2 {
    private volatile static Singleton2 uniqueSingleton2;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (uniqueSingleton2 == null) {
            synchronized (Singleton2.class) {
                if (uniqueSingleton2 == null) {
                    uniqueSingleton2 = new Singleton2();
                }
            }
        }
        return uniqueSingleton2;
    }
}
