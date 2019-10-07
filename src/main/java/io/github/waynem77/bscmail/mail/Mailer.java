/*
 * Copyright © 2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.mail;

import io.github.waynem77.bscmail.persistent.Application;
import io.github.waynem77.bscmail.persistent.EmailServerProperties;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Sends an email message.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 3.4
 */
public class Mailer {

    /**
     * The application.
     */
    private final Application application;

    /**
     * The set of observers.
     */
    private final Set<MailerObserver> observers;

    /**
     * The mailer's status.
     */
    private MailerStatus status;

    /**
     * The last mail server response.
     */
    private String lastServerResponse;

    /**
     * The last send error.
     */
    private Exception lastError;

    /**
     * Constructs a new Mailer using the given application.
     *
     * @param application the underlying application; may not be null
     * @throws NullPointerException if application is null
     */
    public Mailer(Application application) {
        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if

        this.application = application;
        observers = new HashSet<>();
        status = MailerStatus.NOT_STARTED;
        lastServerResponse = null;
        lastError = null;
        assertInvariant();
    }    // Mailer()

    /**
     * Registers an observer with this Mailer.
     *
     * @param observer the observer to register; may not be null
     * @throws NullPointerException if observer is null
     */
    public void registerObserver(MailerObserver observer) {
        assertInvariant();
        if (observer == null) {
            throw new NullPointerException("observer may not be null");
        }    // if

        observers.add(observer);
        assertInvariant();
    }    // registerObserver()

    /**
     * Notifies all observers that the mailer's status has changed. Observers
     * should get the status using the {@link #getStatus()} method.
     */
    public void notifyObservers() {
        assertInvariant();
        for (MailerObserver observer : observers) {
            observer.mailerStatusChanged();
        }    // for
    }    // notifyObserver()

    /**
     * Sends an email.
     *
     * @param mailMessage the email message; may not be null
     * @param password the email server password; may be null
     * @throws NullPointerException if message is null
     */
    public void send(MailMessage mailMessage, String password) {
        assertInvariant();
        if (mailMessage == null) {
            throw new NullPointerException("mailMessage may not be null");
        }     // if

        setStatusAndNotify(MailerStatus.STARTED);

        EmailServerProperties serverProperties = application.getEmailServerProperties();

        Properties sessionProperties = createSessionProperties(serverProperties);
        setStatusAndNotify(MailerStatus.CREATED_PROPERTIES);

        Session session = Session.getInstance(sessionProperties, null);
        setStatusAndNotify(MailerStatus.CREATED_SESSION);

        try {
            Message mimeMessage = createMimeMessage(serverProperties, mailMessage, application.getApplicationName(), session);
            setStatusAndNotify(MailerStatus.CREATED_MESSAGE);

            try (SMTPTransport transport = createTransport(serverProperties, session, password)) {
                setStatusAndNotify(MailerStatus.SENDING);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                lastServerResponse = transport.getLastServerResponse();
                setStatusAndNotify(MailerStatus.MAIL_SENT);
            }    // try
        } catch (Exception e) {
            lastError = e;
            setStatusAndNotify(MailerStatus.ERROR);
        }
    }    // send()

    /**
     * Returns the Mailer's status.
     *
     * @return the Mailer's status
     */
    public MailerStatus getStatus() {
        assertInvariant();
        return status;
    }    // getStatus()

    /**
     * Returns the last mail server response. The mailer polls the server for a
     * response whenever mail is successfully sent (that is, when the mailer
     * status transitions to {@link MailerStatus#MAIL_SENT}). If the mailer has
     * not yet polled the server for a response, this method returns null.
     *
     * @return the last mail server response, or null if the mailer has not yet
     * polled the mail server
     */
    public String getLastServerResponse() {
        assertInvariant();
        return lastServerResponse;
    }    // getLastServerResponse()

    /**
     * Returns the last exception encountered while sending mail. If the mailer
     * has not yet encountered an exception, this method returns null. In
     * particular, if the mailer's status has transitioned to
     * {@link MailerStatus#ERROR}, this method will return the specific error
     * encountered.
     *
     * @return the last exception encountered, or null if the mailer has not yet
     * encountered an exception
     */
    public Exception getLastError() {
        assertInvariant();
        return lastError;
    }    // getLastError()

    /**
     * Returns mail session properties based on the given EmailServerProperties
     * object.
     *
     * @param serverProperties the email server properties; may not be null
     * @return mail session properties based on serverProperties
     */
    private Properties createSessionProperties(EmailServerProperties serverProperties) {
        assert (serverProperties != null);

        String host = serverProperties.getHostname();
        String port = serverProperties.getPort();

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);

        return properties;
    }    // createSessionProperties()

    /**
     * Returns a MIME message created from the given arguments.
     * @param serverProperties the email server properties; may not be null
     * @param mailMessage the mail message; may not be null
     * @param mailerName the name of the mailing application; may not be null
     * @param session the mail session; may not be null
     * @return a MIME message
     * @throws MessagingException if the message cannot be created
     */
    private MimeMessage createMimeMessage(EmailServerProperties serverProperties, MailMessage mailMessage, String mailerName, Session session) throws MessagingException {
        assert (serverProperties != null);
        assert (mailMessage != null);
        assert (mailerName != null);
        assert (session != null);

        String toAddresses = mailMessage.getToAddresses();
        String ccAddresses = mailMessage.getCcAddresses();
        String bccAddresses = mailMessage.getBccAddresses();
        String subject = mailMessage.getSubject();
        String body = mailMessage.getBody();

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddresses, false));
        mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddresses, false));
        mimeMessage.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccAddresses, false));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(body);
        mimeMessage.setHeader("X-Mailer", mailerName);
        mimeMessage.setSentDate(new Date());

        return mimeMessage;
    }    // createMimeMessage()

    /**
     * Creates a mail transport from the given arguments. The transport is
     * connected to the email server.
     *
     * @param serverProperties the email server properties; may not be null
     * @param session the mail session; may not be null
     * @param password the email server password; may not be null
     * @return a mail transport
     * @throws MessagingException if the transport is unable to connect to the
     * mail server
     * @throws NoSuchProviderException if a transport cannot be created
     */
    private SMTPTransport createTransport(EmailServerProperties serverProperties, Session session, String password) throws MessagingException, NoSuchProviderException {
        assert (serverProperties != null);
        assert (session != null);

        String host = serverProperties.getHostname();
        String fromAddress = serverProperties.getUsername();

        SMTPTransport transport = (SMTPTransport)session.getTransport("smtp");
        transport.connect(host, fromAddress, password);
        return transport;
    }
    /**
     * Sets the status of the mailer and notifies its observers.
     * @param status the status to set; may not be null
     */
    private void setStatusAndNotify(MailerStatus status) {
        assert (status != null);

        this.status = status;
        notifyObservers();
    }    // setStatusAndNotify()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (application != null);
        assert (observers != null);
        assert (! observers.contains(null));
        assert (status != null);
    }    // assertInvariant()

}    // Mailer
