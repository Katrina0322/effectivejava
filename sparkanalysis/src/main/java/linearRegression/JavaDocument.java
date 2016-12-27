package linearRegression;

import java.io.Serializable;

/**
 * @filename: JavaDocument
 * @Description:
 * @Author: ubuntu
 * @Date: 12/26/16 3:00 PM
 */
public class JavaDocument implements Serializable {
    private long id;
    private String text;

    public JavaDocument(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }
}
