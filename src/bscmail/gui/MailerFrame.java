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

package bscmail.gui;

import bscmail.Application;
import bscmail.mail.Mailer;
import bscmail.mail.MailerObserver;
import bscmail.mail.MailerStatus;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Displays the progress of sending an email via the {@link Mailer} class.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @since 3.4
 */
public class MailerFrame extends JFrame implements MailerObserver {

    /**
     * The messages area.
     */
    private final JTextArea messagesTextArea;

    /**
     * The close button.
     */
    private final JButton closeButton;

    /**
     * The mailer.
     */
    private final Mailer mailer;


    /**
     * Constructs a new MailerFrame. The MailerFrame automatically registers
     * itself as an observer of the given Mailer.
     *
     * @param application the underlying application; may not be
     * null
     * @param mailer the mailer; may not be null
     * @throws NullPointerException if either parameter is null
     */
    public MailerFrame(Application application, Mailer mailer) {
        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if
        if (application == null) {
            throw new NullPointerException("mailer may not be null");
        }    // if

        setTitle(application.getApplicationName() + " - Mail Status");
        setContentPane(Box.createVerticalBox());

        final int MIN_TEXT_AREA_COLS = 40;
        final int MIN_TEXT_AREA_ROWS = 8;

        messagesTextArea = new JTextArea(MIN_TEXT_AREA_ROWS, MIN_TEXT_AREA_COLS);
        messagesTextArea.setLineWrap(true);
        messagesTextArea.setEditable(false);
        add(new JScrollPane(messagesTextArea));

        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                closeButtonClicked();
            }    // actionPerformed()
        });    // closeButton.addActionListener()
        closeButton.setEnabled(false);
        add(closeButton);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.mailer = mailer;
        mailer.registerObserver(this);

        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        assertInvariant();
    }    // MailerFrame()

    /**
     * {@inheritDoc}
     */
    @Override
    public void mailerStatusChanged() {
        assertInvariant();
        MailerStatus status = mailer.getStatus();
        if (status.equals(MailerStatus.MAIL_SENT)) {
            messagesTextArea.append(status + ": " + mailer.getLastServerResponse() + "\n");
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            closeButton.setEnabled(true);
        } else if (status.equals(MailerStatus.ERROR)) {
            messagesTextArea.append(status + ": " + mailer.getLastError().toString() + "\n");
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            closeButton.setEnabled(true);
        } else {
            messagesTextArea.append(status + "...\n");
        }
    }    // mailerStatusChanged()

    /**
     * Event fired when the close button is clicked.
     */
    private void closeButtonClicked() {
        assertInvariant();
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }    // closeButtonClicked()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (messagesTextArea != null);
        assert (isAncestorOf(messagesTextArea));
        assert (closeButton != null);
        assert (isAncestorOf(closeButton));
        assert (mailer != null);
    }    // assertInvariant()

}    // MailerFrame
