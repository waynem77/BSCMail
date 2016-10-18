/*
 * This code is protected by copyright and licensed under the GNU
 * General Public License version 3.  For details, see the file
 * "LICENSE" or visit https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package bscmail.gui;

import bscmail.Manager;
import javax.swing.*;
import javax.swing.event.*;

/**
 * A panel that displays and manages a {@link Manager}.
 *
 * @since 2.0
 * @author Wayne Miller
 */
class ManageManagerPanel extends ManageElementPanel<Manager> {

    /**
     * The text field displaying a manager's name.
     */
    private final JTextField nameTextField;

    /**
     * The text field displaying a manager's email address.
     */
    private final JTextField emailTextField;

    /**
     * The text field displaying a manager's phone number.
     */
    private final JTextField phoneTextField;
    
    /**
     * Indicates whether the implicit manager is valid.
     */
    private boolean managerIsValid;

    /**
     * Constructs a new manager panel.
     */
    public ManageManagerPanel() {
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
        phoneTextField = new JTextField();
        layoutHelper.addComponent("Phone: ", phoneTextField);
        managerIsValid = elementIsValid();
    }    // ManageShiftPanel()

    /**
     * Loads the details of a manager into the panel.
     *
     * @param manager the manager to load; may be null
     */
    @Override
    public void loadElement(Manager manager) {
        nameTextField.setText((manager == null) ? "" : manager.getName());
        emailTextField.setText((manager == null) ? "" : manager.getEmail());
        phoneTextField.setText((manager == null) ? "" : manager.getPhone());
    }    // loadElement()

    /**
     * Creates and returns a new manager from the values of the components in
     * the panel.
     *
     * @return a new manager created from the values of the components in the
     * panel
     */
    @Override
    public Manager createElement() {
        return new Manager(nameTextField.getText(), emailTextField.getText(), phoneTextField.getText());
    }    // createElement()
    
    /**
     * Returns true if the panel's manager is valid for the frame containing it,
     * or false otherwise.
     *
     * @return true if the panel's manager is valid for the frame containing it;
     * false otherwise
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
        if (newValidity != managerIsValid) {
            notifyObservers();
        }    // if
        managerIsValid = newValidity;
    }    // nameTextFieldChanged()
    
}    // ManageManagerPanel
