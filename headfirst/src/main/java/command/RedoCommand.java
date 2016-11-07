package command;

/**
 * Created by spark on 11/7/16.
 */
public class RedoCommand implements Command {
    private Document document;

    public RedoCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        this.document.redo();
    }
}
