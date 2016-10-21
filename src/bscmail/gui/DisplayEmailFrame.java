/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui;

import bscmail.EmailTemplate;
import bscmail.Event;
import bscmail.Shift;
import bscmail.Volunteer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import java.net.*;
import main.Application;

import javax.swing.*;

/**
 * Constructs and displays an email.  The email is constructed from an email
 * template, with values filled in from an {@link Event}.
 * 
 * @author Wayne Miller
 */
public class DisplayEmailFrame extends JFrame {

    private final JPanel mainPanel;

    /**
     * The text area managing the text.
     */
    private final JTextArea textArea;

    private final JTextField recipientLine;

    private final JTextField subjectLine;

    private final JButton sendEmail;
    
    /**
     * Constructs a new display email frame displaying an email constructed from
     * the given template and list of shifts.
     * 
     * @param emailTemplate the email template; may not be null
     * @param event the event; may not be null
     * @throws NullPointerException if either parameter is null
     */
    public DisplayEmailFrame(EmailTemplate emailTemplate, Event event) {
        if (emailTemplate == null) {
            throw new NullPointerException("emailTemplate may not be null");
        }    // if
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        
        setTitle(Application.getApplicationName() + " - Event Email Text");

        final int COLUMNS = 24;
        final int ROWS = 80;

        mainPanel = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(mainPanel);
        layoutHelper.setLayoutManager();

        recipientLine = new JTextField();
        subjectLine = new JTextField();
        sendEmail = new JButton("Generate Email");
        sendEmail.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {

                sendEmailButtonClicked();

            }    // actionPerformed()
        });    // addActionListener()

        textArea = new JTextArea(COLUMNS, ROWS);
        textArea.setLineWrap(true);

        layoutHelper.addComponent("To: ", recipientLine);
        layoutHelper.addComponent("Subject: ", subjectLine);
        layoutHelper.addComponent("Text: ", new JScrollPane(textArea));
        layoutHelper.addComponent("Actions: ", sendEmail);

        add(mainPanel);
        //add(new JScrollPane(textArea));
        pack();
        assertInvariant();

        Set<String> emails = new HashSet<>();
        for (Shift shift : event.getShifts()) {
            Volunteer volunteer = shift.getVolunteer();
            if (volunteer != null) {
                emails.add(volunteer.getEmail());
            }    // if
        }    // for
        String toLine = "";
        for (String email : emails) {
            if (! toLine.isEmpty()) {
                toLine = email;
            } else {    // if
                toLine += ", " + email;
            }    // else
        }    // for

        recipientLine.setText(toLine);
        //appendText("To: " + toLine);
        //appendText("");
        appendText(emailTemplate.getPreScheduleText());
        appendText("");
        for (Shift shift : event.getShifts()) {
            String shiftLine = shift.getDescription() + ":";
            Volunteer volunteer = shift.getVolunteer();
            if (volunteer != null) {
                shiftLine += " " + volunteer.getName();
                
                String extraInfo = null;
                if (shift.getDisplayVolunteerEmail()) {
                    extraInfo = (extraInfo == null) ? volunteer.getEmail() : (extraInfo + ", " + volunteer.getEmail());
                }    // if
                if (shift.getDisplayVolunteerPhone()) {
                    extraInfo = (extraInfo == null) ? volunteer.getPhone() : (extraInfo + ", " + volunteer.getPhone());
                }    // if
                if (shift.getDisplayVolunteerNotes()) {
                    extraInfo = (extraInfo == null) ? volunteer.getNotes() : (extraInfo + ", " + volunteer.getNotes());
                }    // if
                
                if (extraInfo != null) {
                    shiftLine += " (" + extraInfo + ")";
                }    // if
            }    // if
            appendText(shiftLine);
        }    // for
        appendText("");
        appendText(emailTemplate.getPostScheduleText());
        scrollToTop();





    }    // DisplayEmailFrame()

    /**
     * Event fired when the send email button is clicked.
     */
    private void sendEmailButtonClicked() {

        try {
            mailTo(recipientLine.getText(), subjectLine.getText(), textArea.getText());
        } catch (IOException err) {
            //insert error handling

        } catch (URISyntaxException err) {
            //insert error handling
        }


    }    // sendEmailButtonClicked()

    //The following three methods initially found at
    // http://www.2ality.com/2010/12/simple-way-of-sending-emails-in-java.html
    //Modified by nathan.cordner
    private void mailTo(String recipients, String subject,
                              String body) throws IOException, URISyntaxException {
        String uriStr = String.format("mailto:%s?subject=%s&body=%s",
                recipients.replaceAll("\\s",""), // comma separated list, no whitespace
                urlEncode(subject),
                urlEncode(body));
        Desktop.getDesktop().browse(new URI(uriStr));
    }

    private final String urlEncode(String str) {
        try {
            //WARNING! Something gets messed up here if the last character
            //before the end of the line happens to be a space.
            //But other than that, this works great. :)
            //--Nathan
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    //End of Borrowed Code




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


    
}    // DisplayEmailFrame
