package prototype;

/**
 * Created by spark on 11/6/16.
 */
public class Client {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.setId("1");
        ShapeCache.setPrototype(circle.getId(),circle);

        Square square = new Square();
        square.setId("2");
        ShapeCache.setPrototype(square.getId(),square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        ShapeCache.setPrototype(rectangle.getId(),rectangle);

        System.out.println("Shape : " + ShapeCache.getShape("1").getType());
        System.out.println("Shape : " + ShapeCache.getShape("2").getType());
        System.out.println("Shape : " + ShapeCache.getShape("3").getType());
    }
}
