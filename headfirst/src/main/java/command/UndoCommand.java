package command;

/**
 * Created by spark on 11/7/16.
 */
public class UndoCommand implements Command {
    private Document document;

    public UndoCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        this.document.undo();
    }
}
