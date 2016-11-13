package chap5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by spark on 11/12/16.
 */
public class VectorTest {
    public static <T> T getLast(Vector<T> vector) {
        synchronized (vector) {
            int lastIndex = vector.size() - 1;
            return vector.get(lastIndex);
        }
    }

    public static <T> T deleteLast(Vector<T> vector) {
        synchronized (vector) {
            int lastIndex = vector.size() - 1;
            return vector.remove(lastIndex);
        }
    }

    static class Widget{

    }

    public static void main(String[] args) {
        List<Widget> widgeList = Collections.synchronizedList(new ArrayList<Widget>());
        //可能抛出ConcurrentModificationException
        for(Widget w:widgeList){
            doSomeThing(w);
        }

        CopyOnWriteArrayList<Widget> widgets = new CopyOnWriteArrayList<>();
    }

    public static void doSomeThing(Widget widget){

    }


}
