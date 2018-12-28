/*
 * Copyright © 2018 its authors.  See the file "AUTHORS" for details.
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

package bscmail.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * A panel that displays all the values of an enum as radio buttons. Only one
 * button at most may be selected at one time.
 *
 * @author Wayne Miller
 * @param <E> the enum type
 * @since 3.3
 */
public class EnumRadioPanel<E extends Enum<E>> extends JPanel {

    /**
     * The logical ButtonGroup containing the radio buttons.
     */
    private final ButtonGroup buttonGroup;

    /**
     * A map used to keep track of the radio buttons.
     */
    private final Map<E, JRadioButton> buttonMap;

    /**
     * ActionListeners observing this panel.
     */
    private final List<ActionListener> listeners;

    /**
     * Creates a new enum radio panel. The first button (corresponding to the
     * first enum) will be selected.
     *
     * @param enumClass the class of the enum; may not be null
     * @throws NullPointerException if {@class enumClass} is null
     */
    public EnumRadioPanel(Class<E> enumClass) {
        if (enumClass == null) {
            throw new NullPointerException("enumClass may not be null");
        }    // if

        final E[] ENUM_CONSTANTS = enumClass.getEnumConstants();
        assert (ENUM_CONSTANTS != null);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        buttonGroup = new ButtonGroup();
        buttonMap = new EnumMap<>(enumClass);
        for (E e : ENUM_CONSTANTS) {
            JRadioButton radioButton = new JRadioButton(e.toString());
            buttonGroup.add(radioButton);
            buttonMap.put(e, radioButton);
            add(radioButton);
        }    // for

        // We set selected before adding the ItemListeners so as not to trigger
        // any events before the constructor is complete.
        if (ENUM_CONSTANTS.length > 0) {
            buttonMap.get(ENUM_CONSTANTS[0]).setSelected(true);
        }    // if

        for (JRadioButton radioButton : buttonMap.values()) {
            radioButton.addItemListener(new ItemListener(){
                @Override public void itemStateChanged(ItemEvent e) { radioButtonClicked(e); }
            });    // radioButton.addItemListener()
        }    // for
        listeners = new LinkedList<>();
        assertInvariant();
    }    // EnumRadioPanel()

    /**
     * Sets the selected button to that corresponding to the the given enum.
     *
     * @param selection the enum corresponding to the button to select; may not
     * be null
     * @throws NullPointerException if {@code selection} is null
     */
    public void setSelection(E selection) {
        assertInvariant();
        if (selection == null) {
            throw new NullPointerException("selection may not be null");
        }    // if
        buttonMap.get(selection).setSelected(true);
        assertInvariant();
    }    // setSelectedEnum()
    /**
     * Returns the enum corresponding to the selected button.
     *
     * @return the enum corresponding to the selected button
     */
    public E getSelection() {
        assertInvariant();
        E selectedEnum = null;
        for (E e : buttonMap.keySet()) {
            if (buttonMap.get(e).isSelected()) {
                selectedEnum = e;
                break;
            }    //
        }    // for
        assert (selectedEnum != null);
        return selectedEnum;
    }    // getSelectedEnum()

    /**
     * Adds an ActionListener to the panel.
     *
     * @param listener the ActionListener to be added; may not be null
     * @throws NullPointerException if listener is null
     */
    public void addActionListener(ActionListener listener) {
        assertInvariant();
        if (listener == null) {
            throw new NullPointerException("listener may not be null");
        }    // if
        listeners.add(listener);
        assertInvariant();
    }    // addActionListener()

    /**
     * Notifies all added ActionListeners that an event has occurred. This
     * method calls the
     * {@link ActionListener#actionPerformed(java.awt.event.ActionEvent)} of
     * each ActionListener, passing an {@link ActionEvent} object with the
     * following characteristics.
     * <ul>
     * <li>The source is the EnumRadioPanel object<li>.
     * <li>The id is equal to {@link ActionEvent#ACTION_PERFORMED}.</li>
     * <li>The command string is null.</li>
     * </ul>
     */
    void notifyActionListeners() {
        assertInvariant();
        for (ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }    // for
    }    // notifyActionListeners()


    /**
     * Event that fires when any radio button is clicked.
     */
    private void radioButtonClicked(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            notifyActionListeners();
        }
    }    // radioButtonClicked()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (buttonGroup != null);
        assert (buttonMap != null);
        assert (getNumberOfSelectedRadioButtons() == 1);
        assert (listeners != null);
    }    // assertInvariant()

    /**
     * Returns the number of radio buttons selected.
     *
     * @return the number of radio buttons selected
     */
    private int getNumberOfSelectedRadioButtons() {
        int numberSelected = 0;
        for (JRadioButton button : buttonMap.values()) {
            if (button.isSelected()) {
                ++numberSelected;
            }    // if
        }    // for
        return numberSelected;
    }    // getNumberOfSelectedRadioButtons()

}    // EnumRadioPanel
