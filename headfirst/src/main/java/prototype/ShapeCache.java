package prototype;

import java.util.Hashtable;

/**
 * Created by spark on 11/6/16.
 */
public class ShapeCache {
    private static Hashtable<String, Shape> shapeMap
            = new Hashtable<String, Shape>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    public synchronized static void setPrototype(String shapeId , Shape shape){
        shapeMap.put(shapeId, shape);
    }

}
