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
import io.github.waynem77.bscmail.gui.util.DisplayableListControl;
import io.github.waynem77.bscmail.persistent.Role;
import io.github.waynem77.bscmail.persistent.RolesObserver;
import io.github.waynem77.bscmail.persistent.Shift;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A panel that displays and manages a {@link Shift}.
 *
 * @since 2.0
 * @author Wayne Miller
 */
class ManageShiftPanel extends ManageElementPanel<Shift> implements RolesObserver {

    /**
     * The calling application.
     */
    final Application application;

    /**
     * The text field displaying a shift's description.
     */
    private final JTextField descriptionTextField;

    /**
     * The selection panel for displaying a shift's roles
     */
    private final DisplayableListControl rolesSelectList;

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
     *
     * @param application the calling application; may not be null
     * @throws NullPointerException if {@code application} is null
     */
    public ManageShiftPanel(Application application) {
        final int TEXT_COLS = 15;

        if (application == null) {
            throw new NullPointerException("application may not be null");
        }    // if
        this.application = application;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        LabeledGrid labeledGrid = new LabeledGrid();
        add(labeledGrid);

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
        rolesSelectList = new DisplayableListControl();
        rolesSelectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        rolesSelectList.setListData(application.getRoles());

        labeledGrid.addLabelAndComponent("Description: ", descriptionTextField);

        labeledGrid.addLabelAndComponent("Roles Required: ", rolesSelectList);

        displayVolunteerEmailCheckBox = new JCheckBox();
        labeledGrid.addLabelAndComponent("Display volunteer email: ", displayVolunteerEmailCheckBox);

        displayVolunteerPhoneCheckBox = new JCheckBox();
        labeledGrid.addLabelAndComponent("Display volunteer phone: ", displayVolunteerPhoneCheckBox);

        displayVolunteerNotesCheckBox = new JCheckBox();
        labeledGrid.addLabelAndComponent("Display volunteer notes: ", displayVolunteerNotesCheckBox);

        shiftIsValid = elementIsValid();

        application.registerObserver(this);
    }

    /**
     * Loads the details of a shift into the panel.
     *
     * @param shift the shift to load; may be null
     */
    @Override
    public void loadElement(Shift shift) {
        if (shift == null) {
            // Create a blank shift.
            shift = new Shift("", Collections.emptyList(), false, false, false);
        }
        descriptionTextField.setText(shift.getDescription());
        loadSelectedRoles(shift);
        displayVolunteerEmailCheckBox.setSelected(shift.getDisplayVolunteerEmail());
        displayVolunteerPhoneCheckBox.setSelected(shift.getDisplayVolunteerPhone());
        displayVolunteerNotesCheckBox.setSelected(shift.getDisplayVolunteerNotes());
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
        return !descriptionTextField.getText().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setEditable(boolean enabled) {
        descriptionTextField.setEditable(enabled);
        rolesSelectList.setDisplayOnly(!enabled);
        displayVolunteerEmailCheckBox.setEnabled(enabled);
        displayVolunteerPhoneCheckBox.setEnabled(enabled);
        displayVolunteerNotesCheckBox.setEnabled(enabled);
    }    // setEditable()

    /**
     * {@inheritDoc}
     */
    @Override
    public ManageShiftPanel createCopy() {
        return new ManageShiftPanel(application);
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
        List<Role> allRoles = application.getRoles();
        List<Role> shiftRoles = shift.getRoles();
        int[] selectedIndices = allRoles.stream()
                .filter(shiftRoles::contains)
                .mapToInt(allRoles::indexOf)
                .toArray();
        rolesSelectList.setSelectedIndices(selectedIndices);
    }

    /**
     * Obtains the list of roles selected for the shift
     * @return a list of Roles
     */
    private List<Role> getSelectedRoles() {
        int[] selectedIndices = rolesSelectList.getSelectedIndices();
        List<Role> allRoles = application.getRoles();
        return Arrays.stream(selectedIndices)
                .mapToObj(allRoles::get)
                .collect(Collectors.toList());
    }
}
