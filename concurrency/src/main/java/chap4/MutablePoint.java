package chap4;


import javax.annotation.concurrent.NotThreadSafe;

/**
 * Created by spark on 11/9/16.
 */
@NotThreadSafe
public class MutablePoint {
    public int x,y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
