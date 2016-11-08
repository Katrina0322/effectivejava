package interpreter;

/**
 * used to
 * Created by tianjin on 11/8/16.
 */
public class Minus implements Expression {
    @Override
    public int interpret(Context context) {
        return context.getNum1()-context.getNum2();
    }
}
