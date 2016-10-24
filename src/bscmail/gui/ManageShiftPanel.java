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

import bscmail.Shift;
import bscmail.Role;
import main.Application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

/**
 * A panel that displays and manages a {@link Shift}.
 *
 * @since 2.0
 * @author Wayne Miller
 */
class ManageShiftPanel extends ManageElementPanel<Shift> {

    /**
     * The text field displaying a shift's description.
     */
    private final JTextField descriptionTextField;

    /**
     * The selection panel for displaying a shift's roles
     */
    private final JList rolesSelectList;

    /**
     * The check box displaying whether the volunteer's email address should be
     * displayed in the email.
     */
    private final JCheckBox displayVolunteerEmailCheckBox;

    /**
     * The check box displaying whether the volunteer's phone number should be
     * displayed in the email.
     */
    private final JCheckBox displayVolunteerPhoneCheckBox;

    /**
     * The check box displaying whether the volunteer notes should be displayed
     * in the email.
     */
    private final JCheckBox displayVolunteerNotesCheckBox;

    /**
     * Indicates whether the implicit shift is valid.
     */
    private boolean shiftIsValid;

    /**
     * Constructs a new shift panel.
     */
    public ManageShiftPanel() {
        final int TEXT_COLS = 15;

        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(this);
        layoutHelper.setLayoutManager();
        descriptionTextField = new JTextField(TEXT_COLS);
        descriptionTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                descriptionTextFieldChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                descriptionTextFieldChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                descriptionTextFieldChanged();
            }
        });
        rolesSelectList = new JList();
        rolesSelectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        rolesSelectList.setListData(Application.getRoleNames());

        layoutHelper.addComponent("Description: ", descriptionTextField);

        layoutHelper.addComponent("Roles Required: ", rolesSelectList);

        displayVolunteerEmailCheckBox = new JCheckBox();
        layoutHelper.addComponent("Display volunteer email: ", displayVolunteerEmailCheckBox);

        displayVolunteerPhoneCheckBox = new JCheckBox();
        layoutHelper.addComponent("Display volunteer phone: ", displayVolunteerPhoneCheckBox);

        displayVolunteerNotesCheckBox = new JCheckBox();
        layoutHelper.addComponent("Display volunteer notes: ", displayVolunteerNotesCheckBox);

        shiftIsValid = elementIsValid();
    }

    /**
     * Loads the details of a shift into the panel.
     *
     * @param shift the shift to load; may be null
     */
    @Override
    public void loadElement(Shift shift) {
        descriptionTextField.setText((shift == null) ? "" : shift.getDescription());
        if (shift != null) {
            loadSelectedRoles(shift);
        }    // if
        displayVolunteerEmailCheckBox.setSelected((shift == null) ? false : shift.getDisplayVolunteerEmail());
        displayVolunteerPhoneCheckBox.setSelected((shift == null) ? false : shift.getDisplayVolunteerPhone());
        displayVolunteerNotesCheckBox.setSelected((shift == null) ? false : shift.getDisplayVolunteerNotes());
    }

    /**
     * Creates and returns a new shift from the values of the components in the
     * panel.
     *
     * @return a new shift created from the values of the components in the
     * panel
     */
    @Override
    public Shift createElement() {
        return new Shift(descriptionTextField.getText(),
                getSelectedRoles(),
                displayVolunteerEmailCheckBox.isSelected(),
                displayVolunteerPhoneCheckBox.isSelected(),
                displayVolunteerNotesCheckBox.isSelected());
    }

    /**
     * Returns true if the panel's shift is valid for the frame containing it,
     * or false otherwise.
     *
     * @return true if the panel's shift is valid for the frame containing it;
     * false otherwise
     * @since 2.0.1
     */
    @Override
    public final boolean elementIsValid() {
        if (descriptionTextField.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Event that fires when the text in {@link #descriptionTextField} changes.
     */
    private void descriptionTextFieldChanged() {
        boolean newValidity = elementIsValid();
        if (newValidity != shiftIsValid) {
            notifyObservers();
        }    // if
        shiftIsValid = newValidity;
    }

    /**
     * Selects the current roles of the shift
     * This method assumes that each role on a shift
     * is a valid role (exists in the roles list)
     *
     * @param shift current shift
     */
    private void loadSelectedRoles(Shift shift) {
        List<Role> allRoles = Application.getRoles();
        List<Role> shiftRoles = shift.getRoles();
        int[] selectedIndices = new int[shiftRoles.size()];
        int selectIndex = 0;
        for (Role role : allRoles) {
            if (shiftRoles.contains(role)) {
                selectedIndices[selectIndex] = allRoles.indexOf(role);
                selectIndex++;
            }
        }
        rolesSelectList.setSelectedIndices(selectedIndices);
    }

    /**
     * Obtains the list of roles selected for the shift
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
}
