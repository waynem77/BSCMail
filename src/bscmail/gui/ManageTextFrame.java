/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.gui;

import javax.swing.*;

/**
 * An abstract class used for displaying and editing large amounts of text.
 * 
 * This frame currently used a box layout manager.
 * 
 * @since 2.0
 * @author Wayne Miller
 */
public abstract class ManageTextFrame extends JFrame {

    /**
     * The text area managing the text.
     */
    private final JTextArea textArea;
    
    /**
     * Constructs a new manage text frame.
     */
    public ManageTextFrame() {
        final int COLUMNS = 24;
        final int ROWS = 80;
        
        setContentPane(Box.createVerticalBox());
        textArea = new JTextArea(COLUMNS, ROWS);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea));
        pack();
        assertInvariant();
    }    // ManageTextFrame()

    /**
     * Appends a line of text to the text area.
     * 
     * @param line the line of text to append; may not be null
     * @throws NullPointerException if {@code line} is null
     */
    protected final void appendText(String line) {
        assertInvariant();
        textArea.append(line + "\n");
        assertInvariant();
    }    // appendText()
    
    /**
     * Scrolls the text area to the top.
     */
    protected final void scrollToTop() {
        assertInvariant();
        textArea.setCaretPosition(0);
        assertInvariant();
    }    // scrollToTop()

    /**
     * Returns the text area used for managing text.
     * 
     * @return the text area used for managing text
     */
    protected final JTextArea getTextArea() {
        assertInvariant();
        return textArea;
    }    // getTextArea()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (textArea != null);
        assert (isAncestorOf(textArea));
    }    // assertInvariant()
    
}    // ManageTextFrame
