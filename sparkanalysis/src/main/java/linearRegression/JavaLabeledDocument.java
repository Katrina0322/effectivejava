package linearRegression;

import java.io.Serializable;

/**
 * @filename: JavaLabeledDocument
 * @Description:
 * @Author: ubuntu
 * @Date: 12/26/16 3:01 PM
 */
public class JavaLabeledDocument extends JavaDocument implements Serializable{
    private double label;

    public JavaLabeledDocument(long id, String text, double label) {
        super(id, text);
        this.label = label;
    }

    public double getLabel() {
        return label;
    }
}
