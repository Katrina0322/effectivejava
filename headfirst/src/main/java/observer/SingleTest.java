package observer;

/**
 * Created by spark on 11/7/16.
 */
public class SingleTest {
    public static void main(String[] args) {
        NumObservable number = new NumObservable();
        number.addObserver(new NumObserver());
        number.setData(1);
        number.setData(2);
        number.setData(3);
    }
}
