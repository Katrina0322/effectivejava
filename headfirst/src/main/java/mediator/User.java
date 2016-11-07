package mediator;

/**
 * Created by spark on 11/7/16.
 */
public abstract class User {
    private Mediator mediator;

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator(){
        return mediator;
    }

    public abstract void work();
}
