/*
 * Copyright © 2014-2020 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * BSCMail is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BSCMail is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BSCMail.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.waynem77.bscmail.gui.error;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * Dialog that displays an error message.
 * 
 * @author Wayne Miller
 * @since 2.0.2
 */
public class ErrorDialog extends JDialog {

    /**
     * The width of the dialog border.
     */
    private final static int BORDER_WIDTH = 10;
    
    /**
     * The number of rows for the cause text area.
     */
    private final static int TEXT_AREA_ROWS = 4;
    
    /**
     * The error cause.
     */
    private final Throwable cause;
    
    /**
     * Constructs a new error dialog with the given owner, message, and
     * throwable. The throwable provides extra information that the user may
     * display at their convenience.
     *
     * @param owner the owner of the dialog, or null if there is no owner
     * @param title the window title
     * @param message the message to display, or null if there is no message
     * @param cause the underlying error, or null if there is no underlying
     * error object
     */
    public ErrorDialog(Frame owner, String title, String message, Throwable cause) {
        super(owner, title, true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        Icon errorIcon = UIManager.getIcon("OptionPane.errorIcon");
        panel.add(new JLabel(errorIcon));
        panel.add(new JLabel(message));
        add(panel);
        
        panel = new JPanel();
        if (cause != null) {
            JButton moreButton = new JButton("More >>");
            moreButton.addActionListener(this::moreButtonClicked);
            panel.add(moreButton);
        }    // if
        JButton okButton = new JButton("OK");
        okButton.addActionListener(this::okButtonClicked);
        panel.add(okButton);
        add(panel);
        
        this.cause = cause;
        
        pack();
    }    // ErrorDialog()

    /**
     * Constructs a new error dialog with the given owner.
     *
     * @param owner the owner of the dialog, or null if there is no owner
     * @param title the window title
     * @param message the message to display, or null if there is no message
     * @throws NullPointerException if {@code applicationName} is null
     */
    public ErrorDialog(Frame owner, String title, String message) {
        this(owner, title, message, null);
    }    // ErrorDialog()
    
    /**
     * Event that fires when the ok button is pressed.
     * @param e the event
     */
    private void okButtonClicked(ActionEvent e) {
        setVisible(false);
        dispose();
    }    // okButtonClicked()
    
    /**
     * Event that fires when the more button is pressed.
     * @param e the event
     */
    private void moreButtonClicked(ActionEvent e) {
        JButton moreButton = (JButton)e.getSource();
        moreButton.setEnabled(false);
        
        JTextArea textArea = new JTextArea();
        textArea.setRows(TEXT_AREA_ROWS);
        for (Throwable throwable = cause; throwable != null; throwable = throwable.getCause()) {
            if (! textArea.getText().isEmpty()) {
                textArea.append("\n");
            }    // if
            textArea.append(throwable.toString());
        }    // for
        textArea.setEditable(false);
        add(new JScrollPane(textArea));

        pack();
    }    // moreButtonClicked()
    
}    // ErrorDialog
