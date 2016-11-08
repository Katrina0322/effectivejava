package visitor;

/**
 * used to
 * Created by tianjin on 11/8/16.
 */
public class ConcreteVisitorA implements Visitor {
    @Override
    public void visit(ConcreteElementB able) {
        able.operate();
    }

    @Override
    public void visit(ConcreteElementA able) {
        able.operate();
    }
}
