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

import bscmail.Volunteer;
import javax.swing.*;
import javax.swing.event.*;

/**
 * A panel that displays and manages a {@link Volunteer}.
 *
 * @since 2.0
 * @author Wayne Miller
 */
class ManageVolunteerPanel extends ManageElementPanel<Volunteer> {

    /**
     * The text field displaying a volunteer's name.
     */
    private final JTextField nameTextField;

    /**
     * The text field displaying a volunteer's email address.
     */
    private final JTextField emailTextField;

    /**
     * Indicates whether the implicit volunteer is valid.
     */
    private boolean volunteerIsValid;

    /**
     * Constructs a new volunteer panel.
     */
    public ManageVolunteerPanel() {
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(this);
        layoutHelper.setLayoutManager();
        nameTextField = new JTextField();
        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nameTextFieldChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nameTextFieldChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                nameTextFieldChanged();
            }
        });
        layoutHelper.addComponent("Name: ", nameTextField);
        emailTextField = new JTextField();
        layoutHelper.addComponent("Email: ", emailTextField);
        volunteerIsValid = elementIsValid();
    }

    /**
     * Loads the details of a volunteer into the panel.
     *
     * @param volunteer the volunteer to load; may be null
     */
    @Override
    public void loadElement(Volunteer volunteer) {
        nameTextField.setText((volunteer == null) ? "" : volunteer.getName());
        emailTextField.setText((volunteer == null) ? "" : volunteer.getEmail());
    }

    /**
     * Creates and returns a new volunteer from the values of the components in
     * the panel.
     *
     * @return a new volunteer created from the values of the components in the
     * panel
     */
    @Override
    public Volunteer createElement() {
        return new Volunteer(nameTextField.getText(), emailTextField.getText());
    }

    /**
     * Returns true if the panel's volunteer is valid for the frame containing
     * it, or false otherwise.
     *
     * @return true if the panel's volunteer is valid for the frame containing
     * it; false otherwise
     * @since 2.0.1
     */
    @Override
    public final boolean elementIsValid() {
        return !nameTextField.getText().isEmpty();
    }

    /**
     * Event that fires when the text in {@link #nameTextField} changes.
     */
    private void nameTextFieldChanged() {
        boolean newValidity = elementIsValid();
        if (newValidity != volunteerIsValid) {
            notifyObservers();
        }
        volunteerIsValid = newValidity;
    }
}