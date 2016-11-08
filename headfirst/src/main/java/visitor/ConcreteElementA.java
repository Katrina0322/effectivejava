package visitor;

/**
 * used to
 * Created by tianjin on 11/8/16.
 */
public class ConcreteElementA implements Visitable {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void operate() {
        System.out.println("ConcreteElementA ....");
    }
}
