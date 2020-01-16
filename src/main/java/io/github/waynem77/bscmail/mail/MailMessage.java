/*
 * Copyright © 2019-2020 its authors.  See the file "AUTHORS" for details.
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

/**
 * Represents an email message.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 3.4
 */
public class MailMessage {

    /**
     * The "to" recipients.
     */
    private final String toAddresses;

    /**
     * The "cc" recipients.
     */
    private final String ccAddresses;

    /**
     * The "bcc" recipients.
     */
    private final String bccAddresses;

    /**
     * The message subject.
     */
    private final String subject;

    /**
     * The message body.
     */
    private final String body;

    /**
     * Constructs a new mail message from the given arguments.
     *
     * @param toAddresses a comma-separated list of email addresses indicating
     * the "to" recipients; may be null if there are no such recipients
     * @param ccAddresses a comma-separated list of email addresses indicating
     * the "CC" recipients; may be null if there are no such recipients
     * @param bccAddresses a comma-separated list of email addresses indicating
     * the "BCC" recipients; may be null if there are no such recipients
     * @param subject the subject line of the message; may not be null
     * @param body the body of the message; may not be null
     * @throws NullPointerException if {@code subject} or {@code body} is null
     */
    public MailMessage(String toAddresses, String ccAddresses, String bccAddresses, String subject, String body) {
        if (subject == null) {
            throw new NullPointerException("subject may not be null");
        }
        if (body == null) {
            throw new NullPointerException("body may not be null");
        }

        this.toAddresses = toAddresses;
        this.ccAddresses = ccAddresses;
        this.bccAddresses = bccAddresses;
        this.subject = subject;
        this.body = body;
        assertInvariant();
    }    // MailMessage()

    /**
     * Returns a comma-separated list of email addresses indicating the "to"
     * recipients
     *
     * @return a comma-separated list of email addresses indicating the "to"
     * recipients
     */
    public String getToAddresses() {
        assertInvariant();
        return toAddresses;
    }    // getToAddresses()

    /**
     * Returns a comma-separated list of email addresses indicating the "CC"
     * recipients
     *
     * @return a comma-separated list of email addresses indicating the "C"
     * recipients
     */
    public String getCcAddresses() {
        assertInvariant();
        return ccAddresses;
    }    // getCcAddresses()

    /**
     * Returns a comma-separated list of email addresses indicating the "BCC"
     * recipients
     *
     * @return a comma-separated list of email addresses indicating the "BCC"
     * recipients
     */
    public String getBccAddresses() {
        assertInvariant();
        return bccAddresses;
    }    // getBccAddresses()

    /**
     * Returns the subject line of the message.
     *
     * @return the subject line of the message
     */
    public String getSubject() {
        assertInvariant();
        return subject;
    }    // getSubject()

    /**
     * Returns the body of the message.
     *
     * @return the body of the message
     */
    public String getBody() {
        assertInvariant();
        return body;
    }    // getBody()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (subject != null);
        assert (body != null);
    }    // assertInvariant()

}    // MailMessage
