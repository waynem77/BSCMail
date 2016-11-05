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

import bscmail.Application;
import bscmail.Role;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
     * Button to import another XML file of roles.
     */
    private final JButton importRoles;

    /**
     * Dialog window to choose file to import.
     */
    private final JFileChooser fileChooser;

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

        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(this);
        layoutHelper.setLayoutManager();
        nameTextField = new JTextField();
        nameTextField.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { nameTextFieldChanged(); }
            @Override public void removeUpdate(DocumentEvent e) { nameTextFieldChanged(); }
            @Override public void changedUpdate(DocumentEvent e) { nameTextFieldChanged(); }
        });    // addDocumentListener()
        layoutHelper.addComponent("Name: ", nameTextField);
        importRoles = new JButton("Import Roles");
        importRoles.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                importRolesButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        layoutHelper.addComponent("", importRoles);

        roleIsValid = elementIsValid();
    }    // ManageShiftPanel()

    /**
     * Loads the details of a role into the panel.
     *
     * @param role the role to load; may be null
     */
    @Override
    public void loadElement(Role role) {
        nameTextField.setText((role == null) ? "" : role.getName());
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
        // check empty
        if (nameTextField.getText().isEmpty()) {
            return false;
        }

        // check for uniqueness
        List<Role> myRoles = application.getRoles();
        for (Role role : myRoles) {
            if (role.getName().equals(nameTextField.getText())) {
                return false;
            }
        }
        return true;
    }

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

    /**
     * Event fired when the import volunteers button is clicked.
     */
    private void importRolesButtonClicked() {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                application.importRoles(fileChooser.getSelectedFile().getPath());
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }    // importRolesButtonClicked()


}
