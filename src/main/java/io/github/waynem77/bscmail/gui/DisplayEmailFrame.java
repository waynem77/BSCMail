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

package io.github.waynem77.bscmail.gui;

import io.github.waynem77.bscmail.gui.util.ComponentFactory;
import io.github.waynem77.bscmail.gui.util.LabeledGrid;
import io.github.waynem77.bscmail.mail.MailMessage;
import io.github.waynem77.bscmail.mail.Mailer;
import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.persistent.EmailTemplate;
import io.github.waynem77.bscmail.persistent.Event;
import io.github.waynem77.bscmail.persistent.EventProperty;
import io.github.waynem77.bscmail.persistent.Shift;
import io.github.waynem77.bscmail.persistent.Volunteer;
import io.github.waynem77.bscmail.util.format.EmailFormatter;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Constructs and displays an email.  The email is constructed from an email
 * template, with values filled in from an {@link Event}.
 *
 * @author Wayne Miller
 */
public class DisplayEmailFrame extends JFrame {

    /**
     * The underlying application.
     */
    private final Application application;

    /**
     * The layout grid for the frame.
     */
    private final LabeledGrid mainPanel;

    /**
     * The text area managing the text.
     */
    private final JTextArea textArea;

    /**
     * The text field managing the list of "to" recipients.
     */
    private final JTextField toRecipientLine;

    /**
     * The text field managing the list of "cc" recipients.
     */
    private final JTextField ccRecipientLine;

    /**
     * The text field managing the list of "bcc" recipients.
     */
    private final JTextField bccRecipientLine;

    /**
     * The text field managing the subject.
     */
    private final JTextField subjectLine;

    /**
     * The send email button.
     */
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

        this.application = application;

        setTitle(application.createWindowTitle("Event Email Text"));

        final int MIN_TEXT_AREA_COLS = 10;
        final int MIN_TEXT_AREA_ROWS = 2;
        final int TEXT_AREA_COLS = 80;
        final int TEXT_AREA_ROWS = 24;

        mainPanel = new LabeledGrid();
        mainPanel.setBorder(ComponentFactory.getStandardBorder());

        toRecipientLine = new JTextField(MIN_TEXT_AREA_COLS);
        ccRecipientLine = new JTextField(MIN_TEXT_AREA_COLS);
        bccRecipientLine = new JTextField(MIN_TEXT_AREA_COLS);
        subjectLine = new JTextField(MIN_TEXT_AREA_COLS);
        sendEmail = new JButton("Generate Email");
        sendEmail.addActionListener(this::sendEmailButtonClicked);

        textArea = new JTextArea(MIN_TEXT_AREA_ROWS, MIN_TEXT_AREA_COLS);
        textArea.setLineWrap(true);

        mainPanel.addLabelAndComponent("To: ", toRecipientLine);
        mainPanel.addLabelAndComponent("Cc: ", ccRecipientLine);
        mainPanel.addLabelAndComponent("Bcc: ", bccRecipientLine);
        mainPanel.addLabelAndComponent("Subject: ", subjectLine);
        mainPanel.addLabelAndComponent("Text: ", new JScrollPane(textArea), true);
        mainPanel.addLabelAndComponent("Actions: ", sendEmail);

        add(mainPanel);

        // Setting frame and text area sizes.
        //   1. Create the text area at its desired minimum rows and columns
        //      and set the minimum size of the frame. (Other techniques for
        //      setting the minimum size did not work well.)
        //   2. Alter the rows and columns of the text area to its preferred
        //      starting values and set the size of the frame.
        //   3. Set the text area rows back to its minimum value. This
        //      prevents scroll bars from apearing when the height of the frame
        //      is lessened but there is no text to scroll to.
        pack();
        Dimension minimumSize = getSize();
        setMinimumSize(minimumSize);
        textArea.setRows(TEXT_AREA_ROWS);
        textArea.setColumns(TEXT_AREA_COLS);
        pack();
        textArea.setRows(MIN_TEXT_AREA_ROWS);

        populateRecipientLines(application.getEmailTemplate(), event);
        populateSubjectLine(application.getEmailTemplate(), event);
        populateEmailBody(application.getEmailTemplate(), event);

        assertInvariant();
    }    // DisplayEmailFrame()

    /**
     * Populates the recipient lines with the appropriate email addresses.
     *
     * @param emailTemplate the email template; may not be null
     * @param event the event; may not be null
     */
    private void populateRecipientLines(EmailTemplate emailTemplate, Event event) {
        assert (emailTemplate != null);
        assert (event != null);

        String recipients = event.getShifts().stream()
                .map(Shift::getVolunteer)
                .filter(Objects::nonNull)
                .map(Volunteer::getEmail)
                .distinct()
                .collect(Collectors.joining(", "));

        EmailTemplate.SendType sendType = emailTemplate.getSendType();
        assert (sendType != null);
        if (sendType == EmailTemplate.SendType.CC) {
            ccRecipientLine.setText(recipients);
        } else if (sendType == EmailTemplate.SendType.BCC) {    // if
            bccRecipientLine.setText(recipients);
        } else {    // else if
            toRecipientLine.setText(recipients);
        }    // else
    }    // populateRecipientLines()

    /**
     * Populates the subject line with appropriate text.
     *
     * @param emailTemplate the email template; may not be null
     * @param event the event; may not be null
     */
    private void populateSubjectLine(EmailTemplate emailTemplate, Event event) {
        assert(emailTemplate != null);
        assert(event != null);
        EmailFormatter emailFormatter = new EmailFormatter(emailTemplate.getDateFormatString());
        String subject = emailFormatter.formatString(emailTemplate.getSubjectLineTemplate(), event);
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
        if (event.hasDate()) {
            String datePropertyFormat = "Date: {date}";
            EmailFormatter emailFormatter = new EmailFormatter(emailTemplate.getDateFormatString());
            appendText(emailFormatter.formatString(datePropertyFormat, event));
        }    // if
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
     * Event fired when the send email button is clicked.
     */
    private void sendEmailButtonClicked(ActionEvent e) {
        JPasswordField passwordField = new JPasswordField();
        int selection = JOptionPane.showConfirmDialog(this, passwordField, application.createWindowTitle("Enter Password"), JOptionPane.OK_CANCEL_OPTION);

        if (selection == JOptionPane.OK_OPTION) {
            Mailer mailer = new Mailer(application);
            MailerFrame mailerFrame = new MailerFrame(application, mailer);
            mailerFrame.setVisible(true);
            mailerFrame.mailerStatusChanged();

            MailMessage message = new MailMessage(toRecipientLine.getText(), ccRecipientLine.getText(), bccRecipientLine.getText(), subjectLine.getText(), textArea.getText());

            Thread mailerThread = new Thread(){
                public void run() {
                    mailer.send(message, new String(passwordField.getPassword()));
                }
            };
            mailerThread.start();
        }    // if
    }    // sendEmailButtonClicked()

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
        assert (application != null);
        assert (mainPanel != null);
        assert (isAncestorOf(mainPanel));
        assert (textArea != null);
        assert (isAncestorOf(textArea));
        assert (toRecipientLine != null);
        assert (isAncestorOf(toRecipientLine));
        assert (ccRecipientLine != null);
        assert (isAncestorOf(ccRecipientLine));
        assert (bccRecipientLine != null);
        assert (isAncestorOf(bccRecipientLine));
        assert (subjectLine != null);
        assert (isAncestorOf(subjectLine));
        assert (sendEmail != null);
        assert (isAncestorOf(sendEmail));
    }    // assertInvariant()

}    // DisplayEmailFrame
