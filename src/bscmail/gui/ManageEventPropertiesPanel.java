/*
 * Copyright © 2014-2018 its authors.  See the file "AUTHORS" for details.
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

import bscmail.EventProperty;
import bscmail.gui.util.LabeledGrid;
import javax.swing.*;
import javax.swing.event.*;

/**
 * A panel that displays and manages an {@link EventProperty}.
 *
 * @since 3.0
 * @author Chaitra Mayya
 */
class ManageEventPropertiesPanel extends ManageElementPanel<EventProperty> {

  /**
   * The text field displaying an event property's name.
   */
  private final JTextField nameTextField;

  /**
   * The text field displaying an event property's default value.
   */
  private final JTextField valueTextField;

  /**
   * Indicates whether the implicit event property is valid.
   */
  private boolean eventPropertyIsValid;

  /**
   * Constructs a new event properties panel.
   */
  public ManageEventPropertiesPanel() {
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
    valueTextField = new JTextField();
    labeledGrid.addLabelAndComponent("Default Value: ", valueTextField);
    eventPropertyIsValid = elementIsValid();
  }    // ManageShiftPanel()

  /**
   * Loads the details of an Event Property into the panel.
   *
   * @param eventProperty Property to load; may be null
   */
  @Override
  public void loadElement(EventProperty eventProperty) {
    nameTextField.setText((eventProperty == null) ? "" : eventProperty.getPropertyName());
    valueTextField.setText((eventProperty == null) ? "" : eventProperty.getDefaultValue());
  }    // loadElement()

  /**
   * Creates and returns a new Event Property from the values of the components in the
   * panel.
   *
   * @return a new Event Property created from the values of the components in the
   * panel
   */
  @Override
  public EventProperty createElement() {
    return new EventProperty(nameTextField.getText(), valueTextField.getText());
  }    // createElement()

  /**
   * Returns true if the panel's Event Property is valid for the frame containing it,
   * or false otherwise.
   *
   * @return true if the panel's Event Property is valid for the frame containing it;
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
    if (newValidity != eventPropertyIsValid) {
      notifyObservers();
    }    // if
    eventPropertyIsValid = newValidity;
  }    // nameTextFieldChanged()

}    // ManageEventPropertiesPanel
