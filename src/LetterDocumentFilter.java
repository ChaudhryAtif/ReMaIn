import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/*
 * Filter for text that only allows alphabetic characters and spaces
 */
class LetterDocumentFilter extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
    	/*
    	 * An inserted string may be more than a single character i.e a copy and paste of 'aaa123d', 
       	 * also we iterate from the back as super.XX implementation will put last insterted string first 
         * and so on thus 'aa123d' would be 'daa', but because we iterate from the back its 'aad' like we want
    	 */
        for (int n = string.length(); n > 0; n--) {
            char c = string.charAt(n - 1);           			            // Get a single character of the string
            if (Character.isAlphabetic(c) || c == ' ') 			            // If its an alphabet letter or white space
                super.replace(fb, i, i1, String.valueOf(c), as);			// Allow update to take place for given letter
        } // end for loop
    }

    @Override
    public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
        super.remove(fb, i, i1);
    }

    @Override
    public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
        super.insertString(fb, i, string, as);
    }
}
