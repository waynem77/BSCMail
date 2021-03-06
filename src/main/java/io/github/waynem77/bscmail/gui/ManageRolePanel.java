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

import io.github.waynem77.bscmail.gui.util.LabeledGrid;
import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.persistent.Role;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Created by nathan.cordner on 10/19/16.
 */
public class ManageRolePanel extends ManageElementPanel<Role> {

    /**
     * The calling application.
     */
    final Application application;

    /**
     * The text field displaying the role's name.
     */
    private final JTextField nameTextField;

    /**
     * Indicates whether the implicit role is valid.
     */
    private boolean roleIsValid;

    /**
     * Constructs a new role panel.
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public ManageRolePanel(Application application) {
        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if
        this.application = application;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        LabeledGrid labeledGrid = new LabeledGrid();
        add(labeledGrid);

        nameTextField = new JTextField();
        nameTextField.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { nameTextFieldChanged(); }
            @Override public void removeUpdate(DocumentEvent e) { nameTextFieldChanged(); }
            @Override public void changedUpdate(DocumentEvent e) { nameTextFieldChanged(); }
        });    // addDocumentListener()
        labeledGrid.addLabelAndComponent("Name: ", nameTextField);

        roleIsValid = elementIsValid();
    }    // ManageShiftPanel()

    /**
     * Loads the details of a role into the panel.
     *
     * @param role the role to load; may be null
     */
    @Override
    public void loadElement(Role role) {
        if (role == null) {
            role = new Role("");
        }
        nameTextField.setText(role.getName());
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
        return new Role(nameTextField.getText());
    }    // createElement()

    /**
     * Checks validity of a potential new role
     *
     * @return true if the panel's role is valid for the frame containing
     * it; false otherwise
     * @since 3.0
     */
    @Override
    public final boolean elementIsValid() {
        return !nameTextField.getText().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setEditable(boolean enabled) {
        nameTextField.setEditable(enabled);
    }    // setEditable()

    /**
     * {@inheritDoc}
     */
    @Override
    public ManageRolePanel createCopy() {
        return new ManageRolePanel(application);
    }    // createCopy()

    /**
     * Event that fires when the text in {@link #nameTextField} changes.
     */
    private void nameTextFieldChanged() {
        boolean newValidity = elementIsValid();
        if (newValidity != roleIsValid) {
            notifyObservers();
        }    // if
        roleIsValid = newValidity;
    }    // nameTextFieldChanged()

}
