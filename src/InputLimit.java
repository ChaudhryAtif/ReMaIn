import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Class to limit input length of JPasswordFields
 */
public class InputLimit extends PlainDocument {
    private int maxLimit = 10;

    InputLimit(int maxLimit) { this.maxLimit = maxLimit; }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) { return; }

        if ((getLength() + str.length()) <= maxLimit) {    // If combined length is <= maxLimit
            super.insertString(offset, str, attr);
        }
    }
}
