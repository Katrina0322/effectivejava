package singleton;

/**
 * Created by ubuntu on 3/14/16.
 */
public class Singleton3 {
    private static Singleton3 uniqueSingleton3 = new Singleton3();

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return uniqueSingleton3;
    }
}
