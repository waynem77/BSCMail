/*
 * Copyright © 2017-2018 its authors.  See the file "AUTHORS" for details.
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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * A panel that places labels and components in a grid. The labels and
 * components are placed in discrete groups. The set of labels and components in
 * any given group is mutable and may be changed at runtime, but higher-index
 * groups are always displayed below lower-indexed groups.
 *
 * @author Wayne Miller
 * @since 3.1
 */
public class GroupedGrid extends JPanel {

    /**
     * Additional vertical padding between groups.
     */
    private static final int GROUP_VERTICAL_PADDING = 10;

    /**
     * The total number of groups.
     */
    private final int groups;

    /**
     * The panels containing the components.
     */
    private final List<LabeledGrid> panels;

    /**
     * Constructs a new grouped grid component with the specified number of
     * groups and columns.
     *
     * @param groups the number of groups; must be 1 or more
     * @throws IllegalArgumentException if {@code groups < 1}
     */
    public GroupedGrid(int groups) {
        if (groups < 1) {
            throw new IllegalArgumentException("groups must be at least 1");
        }    // if
        this.groups = groups;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panels = new ArrayList<>();
        for (int i = 0; i < groups; ++i) {
            if (i != 0) {
                add(Box.createVerticalStrut(GROUP_VERTICAL_PADDING));
            }    // if
            LabeledGrid panel = new LabeledGrid();
            add(panel);
            panels.add(panel);
        }    // for

        assertInvariant();
    }    // GroupedGrid()

    /**
     * Sets all the components in a group to the given list of components.
     *
     * @param components the list of labeled components; may not be null, nor
     * have any null elements
     * @param group the group index; must be a valid group index, that is,
     * greater that or equal to 0 and less than the number of groups
     * @throws NullPointerException if {@code components} is null or contains a
     * null element
     * @throws IndexOutOfBoundsException if {@code group} is invalid
     */
    public void setComponents(List<LabeledComponent> components, int group) {
        assertInvariant();
        if (components == null) {
            throw new NullPointerException("components may not be null");
        }    // if
        if (components.contains(null)) {
            throw new NullPointerException("components may not contain null");
        }    // if
        if ((group < 0) || (group >= groups)) {
            throw new IndexOutOfBoundsException("group (" + group + ") is out of bounds ([0.." + (groups - 1) + "])");
        }    // if

        LabeledGrid panel = panels.get(group);
        panel.removeAll();
        for (LabeledComponent labeledComponent : components) {
            panel.addLabelAndComponent(labeledComponent.getLabel().getText(), labeledComponent.getComponent());
        }    // for

        adjustLabelWidths();

        assertInvariant();
    }    // setComponents()

    /**
     * Adjusts the widths of all the labels to be the maximum value of
     * {@link LabeledGrid#getMinimumLabelWidth()} for all panels.
     */
    private void adjustLabelWidths() {
        int maximumMinimumLabelWidth = 0;    // The greatest value of LabeledGrid.getMinimumLabelWidth for all panels.
        for (LabeledGrid panel : panels) {
            if (panel.getMinimumLabelWidth() > maximumMinimumLabelWidth) {
                maximumMinimumLabelWidth = panel.getMinimumLabelWidth();
            }    // if
        }    // for
        for (LabeledGrid panel : panels) {
            panel.setLabelWidth(maximumMinimumLabelWidth);
        }    // for
    }    // adjustLabelWidths

    /**
     * Returns the list of components in the given group. The return value is
     * guaranteed to be non-null and to contain no null elements.
     *
     * @param group the group index; must be a valid group index, that is,
     * greater that or equal to 0 and less than the number of groups
     * @return the list of components in the given group
     * @throws IndexOutOfBoundsException if {@code group} is invalid
     */
    public List<Component> getComponents(int group) {
        assertInvariant();
        if ((group < 0) || (group >= groups)) {
            throw new IndexOutOfBoundsException("group (" + group + ") is out of bounds ([0.." + (groups - 1) + "])");
        }    // if

        LabeledGrid panel = panels.get(group);
        List<Component> components = panel.getRightSideComponents();
        assert (! components.contains(null));
        return components;
    }    // getValues()

    /**
     * Returns the number of groups in this grouped grid.
     * @return the number of groups in this grouped grid
     */
    public int getNumberOfGroups() {
        assertInvariant();

        return groups;
    }    // getNumberOfGroups()

    /**
     * Creates and returns a "default" {@link GridBagConstraints} object. The
     * constraints object has the following properties.
     * <ul>
     * <li>{@code anchor} is set to {@link GridBagConstraints.NORTHWEST}.</li>
     * <li>{@code fill} is set to {@link GridBagConstraints.HORIZONTAL}.</li>
     * <li>{@code gridx} and {@code gridy} are set to the given arguments.</li>
     * </ul>
     *
     * @param gridx the {@code gridx} property for the constraints
     * @param gridy the {@code gridy} property for the constraints
     * @return
     */
    private GridBagConstraints getDefaultConstraints(int gridx, int gridy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = gridx;
        constraints.gridy = gridy;

        return constraints;
    }    // getDefaultConstraints()

    /**
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (groups >= 0);
        assert (panels != null);
        assert (panels.size() == groups);
        assert (! panels.contains(null));
        assert (gridContainsAllPanels());
    }    // assertInvariant()

    /**
     * Returns true all the elements of {@link #panels} are contained within the
     * grouped grid.
     *
     * This function is only used by {@link #assertInvariant()}
     *
     * @return true all the elements of {@code #panels} are contained within the
     * grouped grid; false otherwise
     */
    private boolean gridContainsAllPanels() {
        for (LabeledGrid panel : panels) {
            if (! isAncestorOf(panel)) {
                return false;
            }    // if
        }    // for

        return true;
    }    // gridContainsAllPanels()

}    // GroupedGrid
