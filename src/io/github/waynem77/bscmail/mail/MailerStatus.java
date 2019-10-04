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

/**
 * Indicates the status of a {@link Mailer}.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 3.4
 */
public enum MailerStatus {

    /**
     * Indicates that the Mailer has not started sending mail.
     */
    NOT_STARTED
    ("Not yet started"),

    /**
     * Indicates that the Mailer has started sending mail.
     */
    STARTED
    ("Started"),

    /**
     * Indicates that the Mailer has created the server properties.
     */
    CREATED_PROPERTIES
    ("Created properties"),

    /**
     * Indicates that the Mailer has created the mail session.
     */
    CREATED_SESSION
    ("Created session"),

    /**
     * Indicated that the Mailer has created the mail message.
     */
    CREATED_MESSAGE
    ("Created message"),

    /**
     * Indicates that the Mailer is sending the message.
     */
    SENDING
    ("Sending"),

    /**
     * Indicates that the Mailer has sent the message and the process is
     * complete.
     */
    MAIL_SENT
    ("Mail sent"),

    /**
     * Indicates that the process exited with an error.
     */
    ERROR
    ("Error");


    /**
     * A string representation of the MailerStatus.
     */
    private final String representation;

    private MailerStatus(String representation) {
        assert (representation != null);
        this.representation = representation;
    }    // MailerStatus

    /**
     * Returns a string representation of the MailerStatus.
     *
     * @return a string representation of the MailerStatus
     */
    @Override
    public String toString() {
        return representation;
    }    // toString()

}    // MailerStatus
