/*
 * Copyright © 2017 its authors.  See the file "AUTHORS" for details.
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
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
     * Vertical padding around components.
     */
    private static final int COMPONENT_VERTICAL_PADDING = 1;

    /**
     * Horizontal padding around components.
     */
    private static final int COMPONENT_HORIZONTAL_PADDING = 5;

    /**
     * Additional vertical padding between groups.
     */
    private static final int GROUP_VERTICAL_PADDING = 10;

    /**
     * The total number of groups.
     */
    private final int groups;

    /**
     * The components in the grouped grid.
     */
    private final List<List<LabeledComponent>> componentGroups;

//    private final GridBagConstraints constraints;

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
        componentGroups = new ArrayList<>();
        for (int i = 0; i < groups; ++i) {
            componentGroups.add(new LinkedList<LabeledComponent>());
        }    // for

        setLayout(new GridBagLayout());

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

        for (int i = group; i < this.groups; ++i) {
            List<LabeledComponent> componentGroup = componentGroups.get(i);
            for (LabeledComponent labeledComponent : componentGroup) {
                remove(labeledComponent.getLabel());
                remove(labeledComponent.getComponent());
            }    // for
        }    // for

        List<LabeledComponent> componentGroup = componentGroups.get(group);
        componentGroup.clear();
        componentGroup.addAll(components);
        componentGroups.set(group, componentGroup);

        int yindex = 0;
        for (int i = 0; i < group; ++i) {
            yindex += componentGroups.get(i).size();
        }    // for
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = yindex;

        for (int i = 0; i < components.size(); ++i) {
            LabeledComponent labeledComponent = components.get(i);
            Insets insets = new Insets(COMPONENT_VERTICAL_PADDING, COMPONENT_HORIZONTAL_PADDING, COMPONENT_VERTICAL_PADDING, COMPONENT_HORIZONTAL_PADDING);
            if ((i == 0) && (group != 0)) {
                insets.top += GROUP_VERTICAL_PADDING;
            }    // if
            constraints.insets = insets;
            add(labeledComponent.getLabel(), constraints);
            ++constraints.gridx;
            add(labeledComponent.getComponent(), constraints);
            ++constraints.gridy;
            constraints.gridx = 0;
        }    // for

        if (group + 1 < componentGroups.size()) {
            setComponents(new LinkedList<>(componentGroups.get(group + 1)), group + 1);
        }    // if

        assertInvariant();
    }    // setComponents()

    private void addComponents(List<LabeledComponent> components, int group) {
        assert (components != null);
        assert (! components.contains(null));
        assert ((group >= 0) && (group < groups));

    }    // addComponents()

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

        List<LabeledComponent> componentGroup = componentGroups.get(group);
        List<Component> components = new LinkedList<>();
        for (LabeledComponent labeledComponent : componentGroup) {
            components.add(labeledComponent.getComponent());
        }    // for

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
     * Asserts the correctness of the object's internal state.
     */
    private void assertInvariant() {
        assert (groups >= 0);
        assert (componentGroups != null);
        assert (componentGroups.size() == groups);
        assert (! componentGroups.contains(null));
        assert (componentGroupsElementsDoNotContainNull());
    }    // assertInvariant()

    /**
     * Returns true if none of the elements of {@link #componentGroups} contains
     * null.
     *
     * This function is only used by {@link #assertInvariant()}
     *
     * @return true if none of the elements of {@code componentGroups} contains
     * null; false otherwise
     */
    private boolean componentGroupsElementsDoNotContainNull() {
        for (List<LabeledComponent> componentGroup : componentGroups) {
            if (componentGroup.contains(null)) {
                return false;
            }    // if
        }    // for

        return true;
    }    // componentGroupsElementsDoNotContainNull()

}    // GroupedGrid
