package command;

/**
 * Created by spark on 11/7/16.
 */
public class Invoker {
    private Command command;

    public void setCommand(Command cmd){
        this.command = cmd;
    }

    public void execute() {
        this.command.execute();
    }
}
