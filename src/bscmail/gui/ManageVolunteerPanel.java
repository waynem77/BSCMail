/*
 * Copyright © 2014-2017 its authors.  See the file "AUTHORS" for details.
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
import bscmail.RolesObserver;
import bscmail.Volunteer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
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
    private final JList rolesSelectList;

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
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public ManageVolunteerPanel(Application application) {
        final int NOTES_ROWS = 4;
        final int NOTES_COLS = 20;
        final String ROLE_INSTRUCTIONS = "(Control-click to select/deselect roles)";
        final int VERTICAL_SPACE_AFTER_CONTROLS = 10;

        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if
        this.application = application;

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
        activeCheckBox = new JCheckBox();
        layoutHelper.addComponent("Active: ", activeCheckBox);
        rolesSelectList = new JList();
        rolesSelectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        rolesSelectList.setListData(new Vector<>(application.getRoles()));
        layoutHelper.addComponent("Roles: ", rolesSelectList);
        layoutHelper.addComponent("", new JLabel(ROLE_INSTRUCTIONS));
        layoutHelper.addComponent("", Box.createVerticalStrut(VERTICAL_SPACE_AFTER_CONTROLS));
        volunteerIsValid = elementIsValid();
        currentVolunteer = null;
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
        currentVolunteer = volunteer;
        nameTextField.setText((volunteer == null) ? "" : volunteer.getName());
        emailTextField.setText((volunteer == null) ? "" : volunteer.getEmail());
        phoneTextField.setText((volunteer == null) ? "" : volunteer.getPhone());
        notesTextArea.setText((volunteer == null) ? "" : volunteer.getNotes());
        activeCheckBox.setSelected((volunteer == null) ? true : volunteer.isActive());
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
        currentVolunteer = new Volunteer(
                nameTextField.getText(),
                emailTextField.getText(),
                phoneTextField.getText(),
                notesTextArea.getText(),
                activeCheckBox.isSelected()
        );
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
     * This method is called whenever the list of defined volunteer roles
     * changes.
     */
    @Override
    public void rolesChanged() {
        rolesSelectList.setListData(new Vector<>(application.getRoles()));
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
        List<Integer> selectedIndices = new LinkedList<>();
        for (int i = 0; i < allRoles.size(); ++i) {
            if (volunteerRoles.contains(allRoles.get(i))) {
                selectedIndices.add(i);
            }    // if
        }    // for
        int[] selectedIndicesAsArray = new int[selectedIndices.size()];
        for (int i = 0; i < selectedIndices.size(); ++i) {
            selectedIndicesAsArray[i] = selectedIndices.get(i);
        }    // for
        rolesSelectList.setSelectedIndices(selectedIndicesAsArray);
    }

    /**
     * Obtains the list of roles selected for the volunteer.
     * @return a list of Roles
     */
    private List<Role> getSelectedRoles() {
        int[] selectedIndices = rolesSelectList.getSelectedIndices();
        ArrayList<Role> selectedRoles = new ArrayList<>();
        List<Role> allRoles = application.getRoles();
        for (int ind : selectedIndices) {
            selectedRoles.add(allRoles.get(ind));
        }
        return selectedRoles;
    }

}    // ManageVolunteerPanel

