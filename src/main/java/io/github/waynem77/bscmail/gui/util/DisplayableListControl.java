/*
 * Copyright © 2019-2020 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.gui.util;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * A GUI list control that can switch to a non-editable mode with specialized
 * display.
 *
 * @author Wayne Miller (waynem77@yahoo.com)
 * @param <T> the type of data displayed by the list control
 * @since 4.0
 */
public class DisplayableListControl<T> extends JPanel {

    /**
     * The text for the instructions label.
     */
    private static final String INSTRUCTIONS_LABEL_TEXT = "Control-click to select/deselect.";

    /**
     * The font for the instructions label.
     */
    private static final Font INSTRUCTIONS_LABEL_FONT = new Font(new JLabel().getFont().getFontName(), Font.ITALIC, new JLabel().getFont().getSize());

    /**
     * The editable list control.
     */
    private final JList<T> listControl;

    /**
     * The instructions label.
     */
    private final JLabel instructionsLabel;

    /**
     * The display label.
     */
    private final JLabel displayLabel;

    /**
     * Indicates whether the control is in display mode or editable mode.
     */
    private boolean displayMode;

    /**
     * Constructs a new DisplayableListControl.
     */
    public DisplayableListControl() {
        setLayout(new GridBagLayout());

        listControl = new JList<>();
        instructionsLabel = new JLabel(INSTRUCTIONS_LABEL_TEXT);
        instructionsLabel.setFont(INSTRUCTIONS_LABEL_FONT);
        displayLabel = new JLabel(getElementListAsText());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 1.0;
        constraints.gridy = 0;
        add(listControl, constraints);
        constraints.gridy++;
        add(instructionsLabel, constraints);
        constraints.gridy++;
        add(displayLabel, constraints);

        displayMode = true;
        listControl.setVisible(!displayMode);
        instructionsLabel.setVisible(!displayMode);
        displayLabel.setVisible(displayMode);
        assertInvariant();
    }    // DisplayableListControl()

    /**
     * Sets the list data to the given list.
     * @param data the data; may not be null nor contain null
     * @throws NullPointerException if data is null or contains null
     */
    public void setListData(List<T> data) {
        assertInvariant();
        if (data == null) {
            throw new NullPointerException("data may not be null");
        }    // if
        if (data.contains(null)) {
            throw new NullPointerException("data may not be null");
        }    // if

        listControl.setListData(new Vector<>(data));
        assertInvariant();
    }    // setListData()

    /**
     * Sets the selection mode for the list. This method behaves identically to
     * {@link JList#setSelectionMode(int)}.
     *
     * @param selectionMode the selection mode
     * @throws IllegalArgumentException if selectionMode is invalid
     */
    public void setSelectionMode(int selectionMode) {
        assertInvariant();
        listControl.setSelectionMode(selectionMode);
        assertInvariant();
    }    // setSelectionMode()

    /**
     * Returns an array of all of the selected indices, in increasing order.
     *
     * @return all of the selected indices, in increasing order, or an empty
     * array if nothing is selected
     */
    public int[] getSelectedIndices() {
        assertInvariant();
        return listControl.getSelectedIndices();
    }    // getSelectedIndices()

    /**
     * Changes the selection to be the set of indices specified by the given
     * array. This method behaves identically to
     * {@link JList#setSelectedIndices}.
     *
     * @param indices an array of the indices of the cells to select; may not be
     * null
     * @throws NullPointerException if indices is null
     */
    public void setSelectedIndices(int[] indices) {
        assertInvariant();
        if (indices == null) {
            throw new NullPointerException("indices may not be null");
        }    // if

        listControl.setSelectedIndices(indices);
        displayLabel.setText(getElementListAsText());
        assertInvariant();
    }    // setSelectedIndices()

    private String getElementListAsText() {
        final String SEPARATOR = "; ";
        return String.join(SEPARATOR, listControl
                .getSelectedValuesList()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList()));
    }    // getElementListAsText()

    /**
     * Switches the control between display-only mode and editable mode.
     *
     * @param displayOnly true if the control should be in display-only mode;
     * false if it should be editable
     */
    public void setDisplayOnly(boolean displayOnly) {
        assertInvariant();
        displayMode = displayOnly;
        listControl.setVisible(!displayMode);
        instructionsLabel.setVisible(!displayMode);
        displayLabel.setVisible(displayMode);
        assertInvariant();
    }    // setDisplayOnly()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (listControl != null);
        assert (isAncestorOf(listControl));
        assert (instructionsLabel != null);
        assert (isAncestorOf(instructionsLabel));
        assert (displayLabel != null);
        assert (isAncestorOf(displayLabel));
        assert (displayMode
                ? (!listControl.isVisible() && !instructionsLabel.isVisible() && displayLabel.isVisible())
                : (listControl.isVisible() && instructionsLabel.isVisible() && !displayLabel.isVisible()));
    }    // assertInvariant()

}    // DisplayableListControl
