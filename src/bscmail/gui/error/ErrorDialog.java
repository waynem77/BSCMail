/*
 * Copyright © 2015 Wayne Miller
 */

package bscmail.gui.error;

import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;
import main.Application;

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
     * Constructs a new error dialog with the given owner, message, and throwable. The
     * throwable provides extra information that the user may display at their
     * convenience.
     *
     * @param owner the owner of the dialog, or null if there is no owner
     * @param message the message to display, or null if there is no message
     * @param cause the throwable that is the cause of the error, or null if
     * there is no cause
     */
    public ErrorDialog(Frame owner, String message, Throwable cause) {
        super(owner, Application.getApplicationName() + " - Error", true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        Icon errorIcon = UIManager.getIcon("OptionPane.errorIcon");
        panel.add(new JLabel(errorIcon));
        panel.add(new JLabel(message));
        add(panel);
        
        panel = new JPanel();
        if (cause != null) {
            JButton moreButton = new JButton("More >>");
            moreButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moreButtonClicked(e);
                }    // actionPerformed()
            });    // addActionListener
            panel.add(moreButton);
        }    // if
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                okButtonClicked();
            }    // actionPerformed()
        });    // addActionListener
        panel.add(okButton);
        add(panel);
        
        this.cause = cause;
        
        pack();
    }    // ErrorDialog()

    /**
     * Constructs a new error dialog with the given owner.
     *
     * @param owner the owner of the dialog, or null if there is no owner
     * @param message the message to display, or null if there is no message
     */
    public ErrorDialog(Frame owner, String message) {
        this(owner, message, null);
    }    // ErrorDialog()
    
    /**
     * Event that fires when the ok button is pressed.
     */
    private void okButtonClicked() {
        setVisible(false);
        dispose();
    }    // okButtonClicked()
    
    /**
     * Event that fires when the more button is pressed.
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
