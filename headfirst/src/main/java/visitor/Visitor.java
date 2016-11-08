package visitor;

/**
 * used to
 * Created by tianjin on 11/8/16.
 */
public interface Visitor {
    void visit(ConcreteElementB able);

    void visit(ConcreteElementA able);
}
