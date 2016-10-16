/*
 * Copyright © 2014–2015 Wayne Miller
 */

package bscmail.gui;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import main.Application;

/**
 * A graphical interface to manage the defined email template in
 * {@link Application}.
 * 
 * @since 2.0
 * @author Wayne Miller
 */
public class ManageTemplateFrame extends ManageTextFrame {

    /**
     * Constructs a new manage template frame from the text supplied by the
     * given reader.
     * 
     * @param reader the reader containing the text to manage; may not be null
     * @throws NullPointerException if {@code reader} is null
     * @throws IOException if an I/O error occurs
     */
    public ManageTemplateFrame(Reader reader) throws IOException {
        if (reader == null) {
            throw new NullPointerException("reader may not be null");
        }    // if
        
        setTitle(Application.getApplicationName() + " - Email Template");

        // We aren't interested in catching any exceptions.  However, we do want
        // the resources to close automatically in case of problems.
        try (BufferedReader input = new BufferedReader(reader)) {
            String line;
            while ((line = input.readLine()) != null) {
                appendText(line);
            }    // while
        }    // try
        scrollToTop();
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                saveButtonClicked();
            }    // actionPerformed()
        });    // addActionListener
        JPanel panel = new JPanel();
        panel.add(saveButton);
        add(panel);
        pack();
    }    // ManageTemplateFrame
    
    private void saveButtonClicked() {
        JTextArea textArea= getTextArea();
        String template = textArea.getText();
        try (Reader templateReader = new StringReader(template)) {
            Application.setEmailTemplate(templateReader);
        } catch (Exception e) {    // try
            Application.showErrorDialog(this, "Unable to save template.", e);
        }    // catch
    }    // saveButtonClicked()
    
}    // ManageTemplateFrame
