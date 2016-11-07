package mediator;

/**
 * Created by spark on 11/7/16.
 */
public class Client {
    public static void main(String[] args) {
        Mediator mediator = new MyMediator();
        mediator.createMediator();
        mediator.workAll();
    }
}
