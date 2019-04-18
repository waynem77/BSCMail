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
import bscmail.EmailServerProperties;
import bscmail.gui.util.ComponentFactory;
import bscmail.gui.util.LabeledGrid;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A graphical interface to manage the email server properties defined in the
 * {@link Application}.
 *
 * @since 3.4
 * @author Wayne Miller <waynem77@yahoo.com>
 */
public class ManageEmailServerPropertiesFrame extends JFrame {

    /**
     * The calling application.
     */
    private final Application application;

    /**
     * The hostname text field.
     */
    private final JTextField hostnameTextField;

    /**
     * The port text field.
     */
    private final JTextField portTextField;

    /**
     * The username text field.
     */
    private final JTextField usernameTextField;

    /**
     * Constructs a new manage email server properties frame.
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public ManageEmailServerPropertiesFrame(Application application) {
        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if
        this.application = application;
        setTitle(application.getApplicationName() + " - Manage Email Server");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        LabeledGrid labeledGrid = new LabeledGrid();
        labeledGrid.setBorder(ComponentFactory.getStandardBorder());
        add(labeledGrid);

        hostnameTextField = new JTextField();
        labeledGrid.addLabelAndComponent("Email server name:", hostnameTextField);

        portTextField = new JTextField();
        labeledGrid.addLabelAndComponent("Port:", portTextField);

        usernameTextField = new JTextField();
        labeledGrid.addLabelAndComponent("Username:", usernameTextField);

        EmailServerProperties emailServerProperties = application.getEmailServerProperties();
        hostnameTextField.setText(emailServerProperties.getHostname());
        portTextField.setText(emailServerProperties.getPort());
        usernameTextField.setText(emailServerProperties.getUsername());

        hostnameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                valuesChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                valuesChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                valuesChanged();
            }    // changedUpdate()
        });    // addDocumentListener()
        portTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                valuesChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                valuesChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                valuesChanged();
            }    // changedUpdate()
        });    // addDocumentListener()
        usernameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                valuesChanged();
            }    // insertUpdate()
            @Override public void removeUpdate(DocumentEvent e) {
                valuesChanged();
            }    // removeUpdate()
            @Override public void changedUpdate(DocumentEvent e) {
                valuesChanged();
            }    // changedUpdate()
        });    // addDocumentListener()

        pack();
        assertInvariant();
    }    // ManageEmailServerPropertiesFrame()

    /**
     * Event fired when and of the text values change.  All changes are passed
     * back to the {@link Application}.
     */
    private void valuesChanged() {
        String hostname = hostnameTextField.getText();
        String port = portTextField.getText();
        String username = usernameTextField.getText();
        EmailServerProperties emailServerProperties = new EmailServerProperties(hostname, port, username);

        try {
            application.setEmailServerProperties(emailServerProperties);
        } catch (IOException e) {
        }
    }    // valuesChanged()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (application != null);
        assert (hostnameTextField != null);
        assert (isAncestorOf(hostnameTextField));
        assert (portTextField != null);
        assert (isAncestorOf(portTextField));
        assert (usernameTextField != null);
        assert (isAncestorOf(usernameTextField));
    }    // assertInvariant()

}    // ManageEmailServerPropertiesFrame
