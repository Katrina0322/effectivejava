package command;

/**
 * Created by spark on 11/7/16.
 */
public class AddCommand implements Command {
    private Document document;

    public AddCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        this.document.add();
    }
}
