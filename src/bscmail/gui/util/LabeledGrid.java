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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel that places labels and components in a grid.
 *
 * @author Wayne Miller
 * @since 3.3
 */
public class LabeledGrid extends JPanel {

    /**
     * Horizontal padding between labels and components.
     */
    private static final int LABEL_PADDING = 10;

    /**
     * The layout manager used by the panel.
     */
    private GridBagLayout layoutManager;

    /**
     * The set of labels in the label column
     */
    private List<JLabel> labels;

    /**
     * The set of components in the component column.
     */
    private List<Component> rightSideComponents;

    /**
     * True if the add methods are unlocked. This property exists so that the
     * base add functions are locked to users, but may be accessed by
     * {@link #addLabelAndComponent(java.lang.String, java.awt.Component)}.
     */
    private boolean addMethodsAreUnlocked;

    /**
     * The minimum label column width.
     */
    private int minimumLabelWidth;

    /**
     * The label column width.
     */
    private int labelWidth;

    /**
     * Constructs a new labeled grid.
     */
    public LabeledGrid() {
        layoutManager = new GridBagLayout();
        setLayout(layoutManager);
        lockAddMethods();

        minimumLabelWidth = 0;
        labelWidth = 0;

        labels = new LinkedList<>();
        rightSideComponents = new LinkedList<>();

        assertInvariant();
    }    // LabeledGrid()

    /**
     * Do not use this method. Use
     * {@link #addLabelAndComponent(java.lang.String, java.awt.Component)}
     * instead.
     *
     * @param comp not used
     * @return not defined
     * @see #addLabelAndComponent(java.lang.String, java.awt.Component)
     */
    @Override
    @Deprecated
    public Component add(Component comp) {
        assert(this.addMethodsAreUnlocked());
        Component retval = super.add(comp);
        return retval;
    }    // add()

    /**
     * Do not use this method. Use
     * {@link #addLabelAndComponent(java.lang.String, java.awt.Component)}
     * instead.
     *
     * @param comp not used
     * @param constraints not used
     * @see #addLabelAndComponent(java.lang.String, java.awt.Component)
     */
    @Override
    @Deprecated
    public void add(Component comp, Object constraints) {
        assert(this.addMethodsAreUnlocked());
        super.add(comp, constraints);
    }    // add()

    /**
     * Do not use this method. Use
     * {@link #addLabelAndComponent(java.lang.String, java.awt.Component)}
     * instead.
     *
     * @param comp not used
     * @param index not used
     * @return not defined
     * @see #addLabelAndComponent(java.lang.String, java.awt.Component)
     */
    @Override
    @Deprecated
    public Component add(Component comp, int index) {
        assert(this.addMethodsAreUnlocked());
        Component retval = super.add(comp, index);
        return retval;
    }    // add()

    /**
     * Do not use this method. Use
     * {@link #addLabelAndComponent(java.lang.String, java.awt.Component)}
     * instead.
     *
     * @param name not used
     * @param comp not used
     * @return not defined
     * @see #addLabelAndComponent(java.lang.String, java.awt.Component)
     */
    @Override
    @Deprecated
    public Component add(String name, Component comp) {
        assert(this.addMethodsAreUnlocked());
        Component retval = super.add(name, comp);
        return retval;
    }    // add()

    /**
     * Do not use this method. Use
     * {@link #addLabelAndComponent(java.lang.String, java.awt.Component)}
     * instead.
     *
     * @param comp not used
     * @param constraints not used
     * @param index not used
     * @see #addLabelAndComponent(java.lang.String, java.awt.Component)
     */
    @Override
    @Deprecated
    public void add(Component comp, Object constraints, int index) {
        assert(this.addMethodsAreUnlocked());
        super.add(comp, constraints, index);
    }    // add()

    /**
     * Adds a label and component to the grid. Components added using this
     * method are not "sticky"; that is, they will not automatically resize
     * vertically when the frame is resized.
     *
     * @param label the value of the label; may not be null
     * @param component the component; may not be null
     * @throws NullPointerException if either parameter is null
     */
    public void addLabelAndComponent(String label, Component component) {
        addLabelAndComponent(label, component, false);
    }    // addLabelAndComponent

    /**
     * Adds a label and component to the grid.
     *
     * @param label the value of the label; may not be null
     * @param component the component; may not be null
     * @param sticky true if the component should automatically resize
     * vertically when the frame is resized; false otherwise
     * @throws NullPointerException if either {@code label} or {@code component}
     * is null
     */
    public void addLabelAndComponent(String label, Component component, boolean sticky) {
        assertInvariant();
        if (label == null) {
            throw new NullPointerException("label may not be null");
        }    // if
        if (component == null) {
            throw new NullPointerException("component may not be null");
        }    // if

        unlockAddMethods();

        JLabel jLabel = new JLabel(label);
        int newLabelWidth = jLabel.getPreferredSize().width;
        int gridx = 0;
        int gridy = GridBagConstraints.RELATIVE;
        GridBagConstraints constraints = makeConstraints(gridx, gridy);
        constraints.weightx = 0.0;
        constraints.weighty = sticky ? 1.0 : 0.0;
        constraints.fill = GridBagConstraints.NONE;
        Insets insets = new Insets(0, 0, 0, LABEL_PADDING);
        constraints.insets = insets;
        add(jLabel, constraints);
        labels.add(jLabel);

        gridx = 1;
        constraints = makeConstraints(gridx, gridy);

        constraints.weightx = 1.0;
        constraints.weighty = sticky ? 1.0 : 0.0;
        constraints.fill = GridBagConstraints.BOTH;
        add(component, constraints);
        rightSideComponents.add(component);

        lockAddMethods();

        if (newLabelWidth > minimumLabelWidth) {
            minimumLabelWidth = newLabelWidth;
        }  // if
        if (newLabelWidth > labelWidth) {
            labelWidth = newLabelWidth;
        }  // if
        setWidthOfAllLabelComponents(labelWidth)        ;
        assertInvariant();
    }    // addLabelAndComponent()

    /**
     * Do not use this method. Use {@link #removeAll()} instead.
     *
     * @param comp not used
     * @see #removeAll()
     */
    @Override
    @Deprecated
    public void remove(Component comp) {
        assert(false);
    }    // remove()

    /**
     * Do not use this method. Use {@link #removeAll()} instead.
     *
     * @param index not used
     * @see #removeAll()
     */
    @Override
    @Deprecated
    public void remove(int index) {
        assert(false);
    }    // remove()

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAll() {
        assertInvariant();
        minimumLabelWidth = 0;
        labelWidth = 0;
        super.removeAll();
        labels.clear();
        rightSideComponents.clear();
        assertInvariant();
    }    // removeAll()

    /**
     * Returns the minimum width for the label column in the grid. This value is
     * the minimum width necessary to display all existing labels properly, and
     * thus may change as controls are added and removed. The width is specified
     * in {@link java.awt.Dimension} units.
     *
     * If the grid contains no controls, this method returns 0.
     *
     * @return the minimum width for the label column
     * @see #getLabelWidth()
     * @see #setLabelWidth(int)
     */
    public int getMinimumLabelWidth() {
        assertInvariant();
        return minimumLabelWidth;
    }    // getMinimumPreferredLabelWidth()

    /**
     * Returns the current width of the label column in the grid. The width is
     * specified in {@link java.awt.Dimension} units.
     *
     * The width of the label column is given as follows.
     * <ul>
     * <li>Upon creation of the grid, the label column width is 0.</li>
     * <li>When a label-component pair is added to the grid via
     * {@link #addLabelAndComponent(java.lang.String, java.awt.Component)}, if
     * the new label's width is larger than the current label column width, the
     * label column width is set to the width of the new value.</li>
     * <li>When {@link #setLabelWidth(int)} is called, the label column width is
     * set to the argument of that method.</li>
     * <li>When {@link #removeAll()} is called, the label column width is set to
     * 0.
     * </ul>
     *
     * If the grid contains no controls, this method returns 0.
     *
     * @return the current width of the label column
     * @see #getMinimumLabelWidth()
     * @see #setLabelWidth(int)
     */
    public int getLabelWidth() {
        assertInvariant();
        return labelWidth;
    }    // getPreferredLabelWidth()

    /**
     * Sets the width of the label column in the grid to the given value. The
     * width is specified in {@link java.awt.Dimension} units. The width must be
     * at least as large as the value returned by
     * {@link #getMinimumLabelWidth()}.
     *
     * @param width the new width of the label column; must be at least as great
     * as the minimum allowed width
     * @throws IllegalArgumentException if {@code wudth} is less than the
     * minimum allowed width
     * @see #getMinimumLabelWidth()
     * @see #getLabelWidth()
     */
    public void setLabelWidth(int width) {
        assertInvariant();
        if (width < minimumLabelWidth) {
            throw new IllegalArgumentException("width must be at least as great as the minimum label width");
        }    // if

        setWidthOfAllLabelComponents(width);
        labelWidth = width;
        assertInvariant();
    }    // setPreferredLabelWidth()

    /**
     * Returns a list of all the components on the grid in order.
     *
     * @return a list of all the components on the grid in order.
     */
    public List<Component> getRightSideComponents() {
        return rightSideComponents;
    }    // getRightSideComponents()

    /**
     * Sets the preferred width of all label components to the given width.
     * 
     * @param width the component width
     */
    private void setWidthOfAllLabelComponents(int width) {
        for (JLabel label : labels) {
            Dimension preferredSize = label.getPreferredSize();
            preferredSize.width = width;
            label.setMinimumSize(preferredSize);
            label.setMaximumSize(preferredSize);
            label.setPreferredSize(preferredSize);
            label.setSize(preferredSize);
        }    // for
    }    // setWidthOfAllLabelComponents()

    /**
     * Creates and returns a {@link GridBagConstraints} object. The constraints
     * object has the following properties.
     * <ul>
     * <li>{@code anchor} is set to {@link GridBagConstraints#NORTHWEST}.</li>
     * <li>{@code fill} is set to {@link GridBagConstraints#HORIZONTAL}.</li>
     * <li>{@code gridx} and {@code gridy} are set to the given arguments.</li>
     * </ul>
     *
     * @param gridx the {@code gridx} property for the constraints
     * @param gridy the {@code gridy} property for the constraints
     * @return a "default" {@code GirdBackConstraints} object
     */
    private GridBagConstraints makeConstraints(int gridx, int gridy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = gridx;
        constraints.gridy = gridy;

        return constraints;
    }    // makeConstraints()

    private void lockAddMethods() {
        addMethodsAreUnlocked = false;
    }    // lockAddMethods()

    private void unlockAddMethods() {
        addMethodsAreUnlocked = true;
    }    // unlockAddMethods()

    private boolean addMethodsAreUnlocked() {
        return addMethodsAreUnlocked;
    }    // addMethodsAreUnlocked()

    /**
     * Confirms the internal state of the object is correct.
     */
    private void assertInvariant() {
        assert(layoutManager != null);
        assert(this.getLayout() == layoutManager);
        assert(minimumLabelWidth >= 0);
        assert(labelWidth >= minimumLabelWidth);
        assert(labels != null);
        assert(Arrays.asList(getComponents()).containsAll(labels));
        assert(allLabelsAreAsWideAsLabelWidth());
        assert(rightSideComponents != null);
        assert(Arrays.asList(getComponents()).containsAll(rightSideComponents));
    }    // assertInvariant()

    /**
     * Returns true if the width of every label in the grid is equal to
     * {@link #labelWidth}.
     *
     * @return true if the width of every label in the grid is equal to
     * {@code labelWidth}; false otherwise
     */
    private boolean allLabelsAreAsWideAsLabelWidth() {
        for (JLabel label : labels) {
            if (label.getPreferredSize().width != labelWidth) {
                return false;
            }    // if
        }    // for
        return true;
    }    // allLabelsAreAsWideAsLabelWidth()

}    // LabeledGrid
