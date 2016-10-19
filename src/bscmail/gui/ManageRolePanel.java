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

import bscmail.Role;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import main.Application;

/**
 * Created by nathan.cordner on 10/19/16.
 */
public class ManageRolePanel extends ManageElementPanel<Role> {

    /**
     * The text field displaying the role's title.
     */
    private final JTextField titleTextField;

    /**
     * Indicates whether the implicit role is valid.
     */
    private boolean roleIsValid;

    /**
     * Constructs a new role panel.
     */
    public ManageRolePanel() {
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(this);
        layoutHelper.setLayoutManager();
        titleTextField = new JTextField();
        titleTextField.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { titleTextFieldChanged(); }
            @Override public void removeUpdate(DocumentEvent e) { titleTextFieldChanged(); }
            @Override public void changedUpdate(DocumentEvent e) { titleTextFieldChanged(); }
        });    // addDocumentListener()
        layoutHelper.addComponent("Title: ", titleTextField);
        roleIsValid = elementIsValid();
    }    // ManageShiftPanel()

    /**
     * Loads the details of a role into the panel.
     *
     * @param role the role to load; may be null
     */
    @Override
    public void loadElement(Role role) {
        titleTextField.setText((role == null) ? "" : role.getTitle());
    }    // loadElement()

    /**
     * Creates and returns a new role from the values of the components in
     * the panel.
     *
     * @return a new role created from the values of the components in the
     * panel
     */
    @Override
    public Role createElement() {

        String roleTitle = titleTextField.getText();

        //check to see if role name is unique


        List<Role> myRoles = Application.getRoles();
        for (Role role : myRoles) {
            if (role.getTitle().equals(roleTitle))
                return null;
        }

        return new Role(titleTextField.getText());
    }    // createElement()

    /**
     * Returns true if the panel's role is valid for the frame containing
     * it, or false otherwise.
     *
     * @return true if the panel's role is valid for the frame containing
     * it; false otherwise
     * @since 3.0
     */
    @Override
    public final boolean elementIsValid() {
        return ! titleTextField.getText().isEmpty();
    }    // elementIsValid()

    /**
     * Event that fires when the text in {@link #titleTextField} changes.
     */
    private void titleTextFieldChanged() {
        boolean newValidity = elementIsValid();
        if (newValidity != roleIsValid) {
            notifyObservers();
        }    // if
        roleIsValid = newValidity;
    }    // nameTextFieldChanged()

}
