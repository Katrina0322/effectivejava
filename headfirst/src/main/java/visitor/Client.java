package visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * used to
 * Created by tianjin on 11/8/16.
 */
public class Client {
    public static void main(String[] args) {

        Visitor v1 = new ConcreteVisitorA();
        List<Visitable> list = new ArrayList<>();
        list.add(new ConcreteElementA());
        list.add(new ConcreteElementB());

        for(Visitable able :list){
            able.accept(v1);
        }

    }
}
