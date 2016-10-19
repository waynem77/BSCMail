/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
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
