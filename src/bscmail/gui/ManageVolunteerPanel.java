/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
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
     * The check box displaying whether a volunteer may fill angel shifts.
     */
    private final JCheckBox angelCheckBox;
    
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
        nameTextField.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { nameTextFieldChanged(); }
            @Override public void removeUpdate(DocumentEvent e) { nameTextFieldChanged(); }
            @Override public void changedUpdate(DocumentEvent e) { nameTextFieldChanged(); }
        });    // addDocumentListener()
        layoutHelper.addComponent("Name: ", nameTextField);
        emailTextField = new JTextField();
        layoutHelper.addComponent("Email: ", emailTextField);
        angelCheckBox = new JCheckBox();
        layoutHelper.addComponent("Angel? ", angelCheckBox);
        volunteerIsValid = elementIsValid();
    }    // ManageShiftPanel()

    /**
     * Loads the details of a volunteer into the panel.
     *
     * @param volunteer the volunteer to load; may be null
     */
    @Override
    public void loadElement(Volunteer volunteer) {
        nameTextField.setText((volunteer == null) ? "" : volunteer.getName());
        emailTextField.setText((volunteer == null) ? "" : volunteer.getEmail());
        angelCheckBox.setSelected((volunteer == null) ? false : volunteer.canAngel());
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
        return new Volunteer(nameTextField.getText(), emailTextField.getText(), angelCheckBox.isSelected());
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
        return ! nameTextField.getText().isEmpty();
    }    // elementIsValid()

    /**
     * Event that fires when the text in {@link #nameTextField} changes.
     */
    private void nameTextFieldChanged() {
        boolean newValidity = elementIsValid();
        if (newValidity != volunteerIsValid) {
            notifyObservers();
        }    // if
        volunteerIsValid = newValidity;
    }    // nameTextFieldChanged()
    
}    // ManageVolunteerPanel
