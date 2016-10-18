/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */
package bscmail.gui;

import bscmail.Shift;
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
     * The check box displaying whether a shift is an angel shift.
     */
    private final JCheckBox angelCheckBox;
    
    /**
     * Indicates whether the implicit shift is valid.
     */
    private boolean shiftIsValid;

    /**
     * Constructs a new shift panel.
     */
    public ManageShiftPanel() {
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(this);
        layoutHelper.setLayoutManager();
        descriptionTextField = new JTextField();
        descriptionTextField.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { descriptionTextFieldChanged(); }
            @Override public void removeUpdate(DocumentEvent e) { descriptionTextFieldChanged(); }
            @Override public void changedUpdate(DocumentEvent e) { descriptionTextFieldChanged(); }
        });    // addDocumentListener()
        layoutHelper.addComponent("Description: ", descriptionTextField);
        angelCheckBox = new JCheckBox();
        layoutHelper.addComponent("Angel? ", angelCheckBox);
        shiftIsValid = elementIsValid();
    }    // ManageShiftPanel()

    /**
     * Loads the details of a shift into the panel.
     *
     * @param shift the shift to load; may be null
     */
    @Override
    public void loadElement(Shift shift) {
        descriptionTextField.setText((shift == null) ? "" : shift.getDescription());
        angelCheckBox.setSelected((shift == null) ? false : shift.isAngelShift());
    }    // loadElement()

    /**
     * Creates and returns a new shift from the values of the components in the
     * panel.
     *
     * @return a new shift created from the values of the components in the
     * panel
     */
    @Override
    public Shift createElement() {
        return new Shift(descriptionTextField.getText(), angelCheckBox.isSelected());
    }    // createElement()
    
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
        return ! descriptionTextField.getText().isEmpty();
    }    // elementIsValid()

    /**
     * Event that fires when the text in {@link #descriptionTextField} changes.
     */
    private void descriptionTextFieldChanged() {
        boolean newValidity = elementIsValid();
        if (newValidity != shiftIsValid) {
            notifyObservers();
        }    // if
        shiftIsValid = newValidity;
    }    // descriptionTextFieldChanged()

}    // ManageShiftPanel
