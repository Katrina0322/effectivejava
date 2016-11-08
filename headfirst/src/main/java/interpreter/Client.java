package interpreter;

/**
 * used to
 * Created by tianjin on 11/8/16.
 */
public class Client {
    public static void main(String[] args) {

        // 计算9+2-8的值
        int result = new Minus().interpret((new Context(new Plus()
                .interpret(new Context(9, 2)), 8)));
        System.out.println(result);
    }
}
