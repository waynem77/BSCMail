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
import bscmail.EventProperty;
import bscmail.Shift;
import bscmail.Volunteer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import bscmail.Application;

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
     * @param application the calling application; may not be null
     * @param event the event; may not be null
     * @throws NullPointerException if either parameter is null
     */
    public DisplayEmailFrame(Application application, Event event) {
        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        
        setTitle(application.getApplicationName() + " - Event Email Text");

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

        populateRecipientLine(event);
        populateSubjectLine(event);
        populateEmailBody(application.getEmailTemplate(), event);

        assertInvariant();
    }    // DisplayEmailFrame()

    /**
     * Populates the recipient line with the appropriate email addresses.
     *
     * @param event the event; may not be null
     */
    private void populateRecipientLine(Event event) {
        Set<String> emails = new HashSet<>();
        for (Shift shift : event.getShifts()) {
            Volunteer volunteer = shift.getVolunteer();
            if (volunteer != null) {
                emails.add(volunteer.getEmail());
            }    // if
        }    // for
        String toLine = "";
        for (String email : emails) {
            if (toLine.isEmpty()) {
                toLine = email;
            } else {    // if
                toLine += ", " + email;
            }    // else
        }    // for

        recipientLine.setText(toLine);
    }    // populateRecipientLine()

    /**
     * Populates the subject line with appropriate text.
     *
     * @param event the event; may not be null
     */
    private void populateSubjectLine(Event event) {
        String subject = "Volunteer schedule";
        String date = this.formattedEventDate(event);
        if (! date.isEmpty()) {
            subject += " for " + date;
        }    // if

        subjectLine.setText(subject);
    }    // populateSubjectLine()

    /**
     * Populates the email body with appropriate text.  This method places text
     * in the following order.
     * <ul>
     *   <li>the pre-schedule text defined in the email template</li>
     *   <li>a list of the event properties defined in the event</li>
     *   <li>the volunteer schedule defined in the shifts of the event</li>
     *   <li>the post-schedule text defined in the email template</li>
     * </ul>
     *
     * @param emailTemplate the email template; may not be null
     * @param event the event; may not be null
     */
    private void populateEmailBody(EmailTemplate emailTemplate, Event event) {
        // Pre-schedule text
        appendText(emailTemplate.getPreScheduleText());
        appendText("");

        // Event properties
        appendText("Date: " + formattedEventDate(event));
        for (EventProperty eventProperty : event.getEventProperties()) {
            appendText(eventProperty.getPropertyName() + ": " + eventProperty.getValue());
        }    // for
        appendText("");

        // Schedule
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

        // Post-schedule text
        appendText(emailTemplate.getPostScheduleText());

        scrollToTop();
    }    // populateEmailBody()

    /**
     * Populates the subject line with appropriate text.
     *
     * @param event the event; may not be null
     */
    private String formattedEventDate(Event event) {
        if (event == null) {
            throw new NullPointerException("event may not be null");
        }    // if
        Date date = event.getDate();
        if (date == null) {
            return "";
        }    // if

        DateFormat format = new SimpleDateFormat("EEEEE MMMMM d");
        return format.format(date);
    }    // populateSubjectLine()

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
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (textArea != null);
        assert (isAncestorOf(textArea));
    }    // assertInvariant()


    
}    // DisplayEmailFrame
