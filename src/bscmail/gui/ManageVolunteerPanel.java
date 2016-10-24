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
import main.Application;
import bscmail.Volunteer;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
     * The text field displaying a volunteer's phone number.
     */
    private final JTextField phoneTextField;

    /**
     * The text field displaying the volunteer notes.
     */
    private final JTextArea notesTextArea;

    /**
     * The selection panel for displaying a volunteer's roles
     */
    private final JList rolesSelectList;

    /**
     * Button to import another XML file of volunteers.
     */
    private final JButton importVolunteers;

    /**
     * Dialog window to choose file to import.
     */
    private final JFileChooser fileChooser;

    /**
     * Indicates whether the implicit volunteer is valid.
     */
    private boolean volunteerIsValid;

    /**
     * Tracks the current volunteer selected.
     */
    private Volunteer currentVolunteer;

    /**
     * Ensures that only one edit roles window is open at a time.
     */

    private boolean editRolesWindowIsOpen;


    /**
     * Constructs a new volunteer panel.
     */
    public ManageVolunteerPanel() {
        final int NOTES_ROWS = 4;
        final int NOTES_COLS = 20;

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
        phoneTextField = new JTextField();
        layoutHelper.addComponent("Phone: ", phoneTextField);
        notesTextArea = new JTextArea(NOTES_ROWS, NOTES_COLS);
        notesTextArea.setLineWrap(true);
        notesTextArea.setWrapStyleWord(true);
        layoutHelper.addComponent("Notes: ", new JScrollPane(notesTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
        rolesSelectList = new JList();
        rolesSelectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        rolesSelectList.setListData(Application.getRoleNames());
        layoutHelper.addComponent("Roles: ", rolesSelectList);
        importVolunteers = new JButton("Import Volunteers");
        importVolunteers.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                importVolunteersButtonClicked();
            }    // actionPerformed()
        });    // addActionListener()
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        layoutHelper.addComponent("", importVolunteers);
        volunteerIsValid = elementIsValid();
        currentVolunteer = null;
        editRolesWindowIsOpen = false;
    }    // ManageShiftPanel()

    /**
     * Loads the details of a volunteer into the panel.
     *
     * @param volunteer the volunteer to load; may be null
     */
    @Override
    public void loadElement(Volunteer volunteer) {
        currentVolunteer = volunteer;
        nameTextField.setText((volunteer == null) ? "" : volunteer.getName());
        emailTextField.setText((volunteer == null) ? "" : volunteer.getEmail());
        phoneTextField.setText((volunteer == null) ? "" : volunteer.getPhone());
        notesTextArea.setText((volunteer == null) ? "" : volunteer.getNotes());
        if (volunteer != null) {
            loadSelectedRoles(volunteer);
        }    // if
    }    // loadElement()

    /**
     * Creates and returns a new volunteer from the values of the components in
     * the panel.
     *
     * @return a new volunteer created from the values of the components in the
     * panel
     */
    @Override
    public Volunteer createElement() {
        currentVolunteer = new Volunteer(nameTextField.getText(), emailTextField.getText(),
                                                         phoneTextField.getText(),notesTextArea.getText());
        for (Role role : getSelectedRoles()) {
            currentVolunteer.addRole(role);
        }    // for
        return currentVolunteer;
    }    // createElement()

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
    }    // nameTextFieldChanged()

    /**
     * Selects the current roles of the volunteer.
     * This method assumes that each role on a volunteer
     * is a valid role (exists in the roles list)
     *
     * @param volunteer current volunteer
     */
    private void loadSelectedRoles(Volunteer volunteer) {
        List<Role> allRoles = Application.getRoles();
        List<Role> volunteerRoles = volunteer.getRoles();
        int[] selectedIndices = new int[volunteerRoles.size()];
        int selectIndex = 0;
        for (Role role : allRoles) {
            if (volunteerRoles.contains(role)) {
                selectedIndices[selectIndex] = allRoles.indexOf(role);
                selectIndex++;
            }
        }
        rolesSelectList.setSelectedIndices(selectedIndices);
    }

    /**
     * Obtains the list of roles selected for the volunteer.
     * @return a list of Roles
     */
    private List<Role> getSelectedRoles() {
        int[] selectedIndices = rolesSelectList.getSelectedIndices();
        ArrayList<Role> selectedRoles = new ArrayList<>();
        List<Role> allRoles = Application.getRoles();
        for (int ind : selectedIndices) {
            selectedRoles.add(allRoles.get(ind));
        }
        return selectedRoles;
    }

    /**
     * Event fired when the import volunteers button is clicked.
     */
    private void importVolunteersButtonClicked() {
         int returnVal = fileChooser.showOpenDialog(this);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
             try {
                 Application.importVolunteers(fileChooser.getSelectedFile().getPath());
             } catch (ClassNotFoundException e) {
                 System.out.println(e);
             } catch (IOException e) {
                 System.out.println(e);
             }
         }

    }    // importVolunteersButtonClicked()
    
}    // ManageVolunteerPanel

