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

import io.github.waynem77.bscmail.Application;
import io.github.waynem77.bscmail.gui.util.DisplayableListControl;
import io.github.waynem77.bscmail.persistent.Role;
import io.github.waynem77.bscmail.persistent.RolesObserver;
import io.github.waynem77.bscmail.persistent.Volunteer;
import io.github.waynem77.bscmail.gui.util.LabeledGrid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A panel that displays and manages a {@link Volunteer}.
 *
 * @since 2.0
 * @author Wayne Miller
 */
class ManageVolunteerPanel extends ManageElementPanel<Volunteer> implements RolesObserver {

    /**
     * The calling application.
     */
    final Application application;

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
     * The check box displaying the volunteer's active state.
     */
    private final JCheckBox activeCheckBox;

    /**
     * The selection panel for displaying a volunteer's roles
     */
    private final DisplayableListControl rolesSelectList;

    /**
     * Indicates whether the implicit volunteer is valid.
     */
    private boolean volunteerIsValid;

    /**
     * Ensures that only one edit roles window is open at a time.
     */

    private boolean editRolesWindowIsOpen;


    /**
     * Constructs a new volunteer panel.
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public ManageVolunteerPanel(Application application) {
        final int NOTES_ROWS = 4;
        final int NOTES_COLS = 20;
        final int VERTICAL_SPACE_AFTER_CONTROLS = 10;

        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if
        this.application = application;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        LabeledGrid labeledGrid = new LabeledGrid();
        add(labeledGrid);

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
        labeledGrid.addLabelAndComponent("Name: ", nameTextField);
        emailTextField = new JTextField();
        labeledGrid.addLabelAndComponent("Email: ", emailTextField);
        phoneTextField = new JTextField();
        labeledGrid.addLabelAndComponent("Phone: ", phoneTextField);
        notesTextArea = new JTextArea(NOTES_ROWS, NOTES_COLS);
        notesTextArea.setLineWrap(true);
        notesTextArea.setWrapStyleWord(true);
        labeledGrid.addLabelAndComponent("Notes: ", new JScrollPane(notesTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
        activeCheckBox = new JCheckBox();
        activeCheckBox.setSelected(true);
        labeledGrid.addLabelAndComponent("Active: ", activeCheckBox);
        rolesSelectList = new DisplayableListControl();
        rolesSelectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        rolesSelectList.setListData(application.getRoles());
        labeledGrid.addLabelAndComponent("Roles: ", rolesSelectList);
        labeledGrid.addLabelAndComponent("", Box.createVerticalStrut(VERTICAL_SPACE_AFTER_CONTROLS));
        volunteerIsValid = elementIsValid();
        editRolesWindowIsOpen = false;

        application.registerObserver(this);
    }    // ManageShiftPanel()

    /**
     * Loads the details of a volunteer into the panel.
     *
     * @param volunteer the volunteer to load; may be null
     */
    @Override
    public void loadElement(Volunteer volunteer) {
        if (volunteer == null) {
            // Create a blank volunteer.
            volunteer = new Volunteer("", "", "", "", false, Collections.emptyList());
        }
        nameTextField.setText(volunteer.getName());
        emailTextField.setText(volunteer.getEmail());
        phoneTextField.setText(volunteer.getPhone());
        notesTextArea.setText(volunteer.getNotes());
        activeCheckBox.setSelected(volunteer.isActive());
        loadSelectedRoles(volunteer);
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
        return new Volunteer(
                nameTextField.getText(),
                emailTextField.getText(),
                phoneTextField.getText(),
                notesTextArea.getText(),
                activeCheckBox.isSelected(),
                getSelectedRoles()
        );
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
     * {@inheritDoc}
     */
    @Override
    public final void setEditable(boolean enabled) {
        nameTextField.setEditable(enabled);
        emailTextField.setEditable(enabled);
        phoneTextField.setEditable(enabled);
        notesTextArea.setEditable(enabled);
        activeCheckBox.setEnabled(enabled);
        rolesSelectList.setDisplayOnly(!enabled);
    }    // setEditable()

    /**
     * {@inheritDoc}
     */
    @Override
    public ManageVolunteerPanel createCopy() {
        return new ManageVolunteerPanel(application);
    }    // createCopy()

    /**
     * This method is called whenever the list of defined volunteer roles
     * changes.
     */
    @Override
    public void rolesChanged() {
        rolesSelectList.setListData(application.getRoles());
        notifyObservers();
    }    // rolesChanged()

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
        List<Role> allRoles = application.getRoles();
        List<Role> volunteerRoles = volunteer.getRoles();
        int[] selectedIndices = allRoles.stream()
                .filter(volunteerRoles::contains)
                .mapToInt(allRoles::indexOf)
                .toArray();
        rolesSelectList.setSelectedIndices(selectedIndices);
    }

    /**
     * Obtains the list of roles selected for the volunteer.
     * @return a list of Roles
     */
    private List<Role> getSelectedRoles() {
        int[] selectedIndices = rolesSelectList.getSelectedIndices();
        List<Role> allRoles = application.getRoles();
        List<Role> selectedRoles = Arrays.stream(selectedIndices)
                .mapToObj(allRoles::get)
                .collect(Collectors.toList());
        return selectedRoles;
    }

}    // ManageVolunteerPanel

