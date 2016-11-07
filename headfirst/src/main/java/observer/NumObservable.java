package observer;

import java.util.Observable;

/**
 * Created by spark on 11/7/16.
 */
public class NumObservable extends Observable {
    private int data = 0;

    public int getData() {
        return data;
    }

    public void setData(int i) {
        data = i;
        setChanged();
        notifyObservers();
    }
}
