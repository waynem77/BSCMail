/*
 * Copyright © 2014-2018 its authors.  See the file "AUTHORS" for details.
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

import bscmail.Application;
import bscmail.EmailServerProperties;
import bscmail.EmailTemplate;
import bscmail.Event;
import bscmail.EventProperty;
import bscmail.Shift;
import bscmail.Volunteer;
import bscmail.gui.util.ComponentFactory;
import bscmail.gui.util.LabeledGrid;
import bscmail.mail.MailMessage;
import bscmail.mail.Mailer;
import bscmail.util.format.EmailFormatter;
import com.sun.mail.smtp.SMTPTransport;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.swing.*;

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

        setTitle(application.getApplicationName() + " - Event Email Text");

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
        sendEmail.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                sendEmailButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()

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

        List<String> emails = new ArrayList<>();
        for (Shift shift : event.getShifts()) {
            Volunteer volunteer = shift.getVolunteer();
            if (volunteer != null) {
                String email = volunteer.getEmail();
                if (! emails.contains(email)) {
                    emails.add(volunteer.getEmail());
                }    // if
            }    // if
        }    // for
        String recipients = "";
        for (String email : emails) {
            if (recipients.isEmpty()) {
                recipients = email;
            } else {    // if
                recipients += ", " + email;
            }    // else
        }    // for

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
     * Returns appropriate text for the subject line.
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

//        try {
//            mailTo(toRecipientLine.getText(), ccRecipientLine.getText(), bccRecipientLine.getText(), subjectLine.getText(), textArea.getText());
//        } catch (IOException err) {
//            //insert error handling
//
//        } catch (URISyntaxException err) {
//            //insert error handling
//        }

        JPasswordField passwordField = new JPasswordField();
        int selection = JOptionPane.showConfirmDialog(this, passwordField, application.getApplicationName() + " - Enter Password", JOptionPane.OK_CANCEL_OPTION);

        if (selection == JOptionPane.OK_OPTION) {
            Mailer mailer = new Mailer(application);
            MailerFrame mailerFrame = new MailerFrame(application, mailer);
            mailerFrame.setVisible(true);
            mailerFrame.mailerStatusChanged();

            EmailServerProperties serverProperties = application.getEmailServerProperties();
            MailMessage message = new MailMessage(toRecipientLine.getText(), ccRecipientLine.getText(), bccRecipientLine.getText(), subjectLine.getText(), textArea.getText());
//            mailer.send(message, new String(passwordField.getPassword()));

            Thread mailerThread = new Thread(){
                public void run() {
                    mailer.send(message, new String(passwordField.getPassword()));
                }
            };
            mailerThread.start();
        }    // if
    }    // sendEmailButtonClicked()

    /**
     * Sends an email through the operating system. Code originally adapted from
     * http://www.2ality.com/2010/12/simple-way-of-sending-emails-in-java.html
     * by nathan.cordner.
     *
     * @param toRecipients the comma-delimited list of "to" recipient email
     * addresses; may not be null
     * @param ccRecipients the comma-delimited list of "cc" recipient email
     * addresses; may not be null
     * @param bccRecipients the comma-delimited list of "bcc" recipient email
     * addresses; may not be null
     * @param subject the subject line; may not be null
     * @param body the email body; may not be null
     * @throws IOException if an I/O error occurs
     * @throws URISyntaxException if a URI syntax exception occurs
     */
    private void mailToOld1(String toRecipients, String ccRecipients, String bccRecipients, String subject,
                              String body) throws IOException, URISyntaxException {
//        String uriStr = String.format("mailto:%s?cc=%s&bcc=%s&subject=%s&body=%s",
//                toRecipients.replaceAll("\\s",""), // comma separated list, no whitespace
//                ccRecipients.replaceAll("\\s",""), // comma separated list, no whitespace
//                bccRecipients.replaceAll("\\s",""), // comma separated list, no whitespace
//                urlEncode(subject),
//                urlEncode(body));
//        Desktop.getDesktop().browse(new URI(uriStr));

        // https://support.google.com/mail/answer/7126229?hl=en&visit_id=636825822910416256-3468808783&rd=2
        // https://www.tutorialspoint.com/java/java_sending_email.htm

        // https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/

        String to = "waynem77@yahoo.com";
        String from = "volunteer@bostonswingcentral.org";
        String host = "smtp.gmail.com";
//        String port = "465";
        String port = "587";
        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//        properties.setProperty("mail.user", from);
//        properties.setProperty("mail.smtp.user", from);
//        properties.setProperty("mail.from", from);
//        properties.setProperty("mail.password", "lobstahroll3");
//        properties.setProperty("mail.smtp.password", "lobstahroll3");
//        properties.setProperty("mail.smtp.ssl.enable", "true");
//        properties.setProperty("mail.smtp.port", "465");

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.socketFactory.port", port);
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
//                properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", port);
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from,"lobstahroll3");
				}
			});
        try {
             // Create a default MimeMessage object.
             MimeMessage message = new MimeMessage(session);

             // Set From: header field of the header.
             message.setFrom(new InternetAddress(from));

             // Set To: header field of the header.
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
         } catch (Exception mex) {
            mex.printStackTrace();
         }

    }

    /**
     * Sends an email through the operating system. Code originally adapted from
     * http://www.2ality.com/2010/12/simple-way-of-sending-emails-in-java.html
     * by nathan.cordner.
     *
     * @param toRecipients the comma-delimited list of "to" recipient email
     * addresses; may not be null
     * @param ccRecipients the comma-delimited list of "cc" recipient email
     * addresses; may not be null
     * @param bccRecipients the comma-delimited list of "bcc" recipient email
     * addresses; may not be null
     * @param subject the subject line; may not be null
     * @param body the email body; may not be null
     * @throws IOException if an I/O error occurs
     * @throws URISyntaxException if a URI syntax exception occurs
     */
    private void mailTo(String toRecipients, String ccRecipients, String bccRecipients, String subject,
                              String body) throws IOException, URISyntaxException {

        // https://support.google.com/mail/answer/7126229?hl=en&visit_id=636825822910416256-3468808783&rd=2
        // https://www.tutorialspoint.com/java/java_sending_email.htm

        // https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/

        String to = "waynem77@yahoo.com";
        String from = "volunteer@bostonswingcentral.org";
        String host = "smtp.gmail.com";
//        String port = "465";
        String port = "587";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(properties, null);

        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
        message.setSubject(subject);
        message.setText(body);
        message.setHeader("X-Mailer", "BSCMail");
        message.setSentDate(new Date());

        SMTPTransport transport = (SMTPTransport)session.getTransport("smtp");
        transport.connect(host, from, "lobstahroll3");
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("Response: " + transport.getLastServerResponse());
        System.out.println("Sent message successfully....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Encodes a string into a format suitable for a URL. Code originally
     * adapted from
     * http://www.2ality.com/2010/12/simple-way-of-sending-emails-in-java.html
     * by nathan.cordner.
     *
     * @param str the string to encode; may not be null
     * @return the encoded string
     * @throws RuntimeException if an unsupported encoding exception occurs
     */
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
