package observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by spark on 11/7/16.
 */
public class NumObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        NumObservable myObserable=(NumObservable) o;
        System.out.println("Data has changed to " + myObserable.getData());
    }
}
