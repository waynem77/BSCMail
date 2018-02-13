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
import java.awt.GridBagConstraints;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for LabeledGrid
 * @author Wayne Miller
 */
public class LabeledGridTest {

    /* constructor */

    /**
     * Tests that {@link LabeledGrid#LabeledGrid()} does not throw an exception.
     */
    @Test
    public void constructorDoesNotThrowException() {
        LabeledGrid labeledGrid = new LabeledGrid();
    }    // constructorDoesNotThrowException()

    /* add */

    /**
     * Tests that {@link LabeledGrid#add(java.awt.Component)} throws an
     * {@link AssertionError} when invoked.
     */
    @Test(expected = AssertionError.class)
    public void addComponentThrowsError() {
        LabeledGrid labeledGrid = new LabeledGrid();
        Component comp = new JButton();

        labeledGrid.add(comp);
    }    // addComponentThrowsError()

    /**
     * Tests that {@link LabeledGrid#add(java.awt.Component, java.lang.Object)}
     * throws an {@link AssertionError} when invoked.
     */
    @Test(expected = AssertionError.class)
    public void addComponentObjectThrowsError() {
        LabeledGrid labeledGrid = new LabeledGrid();
        Component comp = new JButton();
        Object constraints = new GridBagConstraints();
        labeledGrid.add(comp, constraints);
    }    // addComponentObjectThrowsError()

    /**
     * Tests that {@link LabeledGrid#add(java.awt.Component, int)} throws an
     * {@link AssertionError} when invoked.
     */
    @Test(expected = AssertionError.class)
    public void addComponentIntThrowsError() {
        LabeledGrid labeledGrid = new LabeledGrid();
        Component comp = new JButton();
        int index = 0;
        labeledGrid.add(comp, index);
    }    // addComponentIntThrowsError()

    /**
     * Tests that {@link LabeledGrid#add(java.lang.String, java.awt.Component)}
     * throws an {@link AssertionError} when invoked.
     */
    @Test(expected = AssertionError.class)
    public void addStringComponentThrowsError() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String name = "Foo";
        Component comp = new JButton();
        Object constraints = new GridBagConstraints();
        labeledGrid.add(name, comp);
    }    // addStringComponentThrowsError()

    /**
     * Tests that
     * {@link LabeledGrid#add(java.awt.Component, java.lang.Object, int)} throws
     * an {@link AssertionError} when invoked.
     */
    @Test(expected = AssertionError.class)
    public void addComponentObjectIntThrowsError() {
        LabeledGrid labeledGrid = new LabeledGrid();
        Component comp = new JButton();
        Object constraints = new GridBagConstraints();
        int index = 0;
        labeledGrid.add(comp, constraints, index);
    }    // addComponentObjectIntThrowsError()

    /* addLabelAndComponent (2 arguments) */

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * throws a {@link NullPointerException} when label is null.
     */
    @Test(expected = NullPointerException.class)
    public void addLabelAndComponent2ArgsThrowsExceptionWhenLabelIsNull() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = null;
        Component component = new JButton();

        labeledGrid.addLabelAndComponent(label, component);
    }    // addLabelAndComponent2ArgsThrowsExceptionWhenLabelIsNull()

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * throws a {@link NullPointerException} when component is null.
     */
    @Test(expected = NullPointerException.class)
    public void addLabelAndComponent2ArgsThrowsExceptionWhenComponentIsNull() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = null;

        labeledGrid.addLabelAndComponent(label, component);
    }    // addLabelAndComponent2ArgsThrowsExceptionWhenComponentIsNull()

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * does not throw an exception when label is empty.
     */
    @Test
    public void addLabelAndComponent2ArgsDoesNotThrowExceptionWhenLabelIsEmpty() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "";
        Component component = new JButton();

        labeledGrid.addLabelAndComponent(label, component);
    }    // addLabelAndComponent2ArgsDoesNotThrowExceptionWhenLabelIsEmpty()

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * does not throw an exception when neither parameter is null.
     */
    @Test
    public void addLabelAndComponen2ArgstDoesNotThrowExceptionWhenNeitherParameterIsNull() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();

        labeledGrid.addLabelAndComponent(label, component);
    }    // addLabelAndComponent2ArgsDoesNotThrowExceptionWhenNeitherParameterIsNull()

    /**
     * Tests that, after a call to
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)},
     * component has been added to the labeled grid.
     */
    @Test
    public void addLabelAndComponent2ArgsAddsComponent() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();

        labeledGrid.addLabelAndComponent(label, component);

        Component[] components = labeledGrid.getComponents();
        assertTrue(Arrays.asList(components).contains(component));
    }    // addLabelAndComponent2ArgsAddsComponent()

    /**
     * Tests that, after a call to
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)},
     * an appropriate label has been added to the labeled grid.
     */
    @Test
    public void addLabelAndComponent2ArgsAddsLabel() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();

        labeledGrid.addLabelAndComponent(label, component);

        Component[] components = labeledGrid.getComponents();
        assertTrue(Arrays.asList(components).contains(component));

        boolean labeledGridContainsLabel = false;
        for (Component gridComponent : labeledGrid.getComponents()) {
            if (gridComponent instanceof JLabel) {
                JLabel gridLabel = (JLabel)gridComponent;
                if (label.equals(gridLabel.getText())) {
                    labeledGridContainsLabel = true;
                }    // if
            }    // if
        }    // for
        assertTrue(labeledGridContainsLabel);
    }    // addLabelAndComponent2ArgsAddsLabel()

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * does not remove any components that have already been added.
     */
    @Test
    public void addLabelAndComponent2ArgsDoesNotRemoveOldComponents() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label1 = "Foo";
        Component component1 = new JButton();
        labeledGrid.addLabelAndComponent(label1, component1);
        String label2 = "Bar";
        Component component2 = new JButton();

        labeledGrid.addLabelAndComponent(label2, component2);

        Component[] components = labeledGrid.getComponents();
        assertTrue(Arrays.asList(components).contains(component1));
    }    // addLabelAndComponent2ArgsDoesNotRemoveOldComponents()

    /* addLabelAndComponent (3 arguments) */

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component, boolean)}
     * throws a {@link NullPointerException} when label is null.
     */
    @Test(expected = NullPointerException.class)
    public void addLabelAndComponent3ArgsThrowsExceptionWhenLabelIsNull() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = null;
        Component component = new JButton();
        boolean sticky = true;

        labeledGrid.addLabelAndComponent(label, component, sticky);
    }    // addLabelAndComponent3ArgsThrowsExceptionWhenLabelIsNull()

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.zwt.Component, boolean)}
     * throws a {@link NullPointerException} when component is null.
     */
    @Test(expected = NullPointerException.class)
    public void addLabelAndComponent3ArgsThrowsExceptionWhenComponentIsNull() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = null;
        boolean sticky = true;

        labeledGrid.addLabelAndComponent(label, component, sticky);
    }    // addLabelAndComponent3ArgsThrowsExceptionWhenComponentIsNull()

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.zwt.Component, boolean)}
     * does not throw an exception when label is empty.
     */
    @Test
    public void addLabelAndComponent3ArgsDoesNotThrowExceptionWhenLabelIsEmpty() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "";
        Component component = new JButton();
        boolean sticky = true;

        labeledGrid.addLabelAndComponent(label, component, sticky);
    }    // addLabelAndComponent3ArgsDoesNotThrowExceptionWhenLabelIsEmpty()

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.zwt.Component, boolean)}
     * does not throw an exception when neither parameter is null.
     */
    @Test
    public void addLabelAndComponen3ArgstDoesNotThrowExceptionWhenNeitherParameterIsNull() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();
        boolean sticky = true;

        labeledGrid.addLabelAndComponent(label, component, sticky);
    }    // addLabelAndComponent3ArgsDoesNotThrowExceptionWhenNeitherParameterIsNull()

    /**
     * Tests that, after a call to
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.zwt.Component, boolean)},
     * component has been added to the labeled grid.
     */
    @Test
    public void addLabelAndComponent3ArgsAddsComponent() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();
        boolean sticky = true;

        labeledGrid.addLabelAndComponent(label, component, sticky);

        Component[] components = labeledGrid.getComponents();
        assertTrue(Arrays.asList(components).contains(component));
    }    // addLabelAndComponent3ArgsAddsComponent()

    /**
     * Tests that, after a call to
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.zwt.Component, boolean)},
     * an appropriate label has been added to the labeled grid.
     */
    @Test
    public void addLabelAndComponent3ArgsAddsLabel() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();
        boolean sticky = true;

        labeledGrid.addLabelAndComponent(label, component, sticky);

        Component[] components = labeledGrid.getComponents();
        assertTrue(Arrays.asList(components).contains(component));

        boolean labeledGridContainsLabel = false;
        for (Component gridComponent : labeledGrid.getComponents()) {
            if (gridComponent instanceof JLabel) {
                JLabel gridLabel = (JLabel)gridComponent;
                if (label.equals(gridLabel.getText())) {
                    labeledGridContainsLabel = true;
                }    // if
            }    // if
        }    // for
        assertTrue(labeledGridContainsLabel);
    }    // addLabelAndComponent3ArgsAddsLabel()

    /**
     * Tests that
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.zwt.Component, boolean)}
     * does not remove any components that have already been added.
     */
    @Test
    public void addLabelAndComponent3ArgsDoesNotRemoveOldComponents() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label1 = "Foo";
        Component component1 = new JButton();
        labeledGrid.addLabelAndComponent(label1, component1);
        String label2 = "Bar";
        Component component2 = new JButton();
        boolean sticky = true;

        labeledGrid.addLabelAndComponent(label2, component2, sticky);

        Component[] components = labeledGrid.getComponents();
        assertTrue(Arrays.asList(components).contains(component1));
    }    // addLabelAndComponent3ArgsDoesNotRemoveOldComponents()

    /* remove */

    /**
     * Tests that {@link LabeledGrid#remove(java.awt.Component)} throws an
     * {@link AssertionError} when invoked.
     */
    @Test(expected = AssertionError.class)
    public void removeComponentThrowsError() {
        LabeledGrid labeledGrid = new LabeledGrid();
        Component comp = new JButton();

        labeledGrid.remove(comp);
    }    // removeComponentThrowsError()

    /**
     * Tests that {@link LabeledGrid#remove(int)} throws an
     * {@link AssertionError} when invoked.
     */
    @Test(expected = AssertionError.class)
    public void removeIntThrowsError() {
        LabeledGrid labeledGrid = new LabeledGrid();
        int index = 0;

        labeledGrid.remove(index);
    }    // removeIntThrowsError()

    /* removeAll */

    /**
     * Tests that {@link LabeledGrid#removeAll()} does not throw an exception
     * when the grid has no components.
     */
    @Test
    public void removeAllDoesNotThrowExceptionWhenGridHasNoComponents() {
        LabeledGrid labeledGrid = new LabeledGrid();
        assertTrue("Test is incorrect.", labeledGrid.getComponents().length == 0);

        labeledGrid.removeAll();
    }    // removeAllDoesNotThrowExceptionWhenGridHasNoComponents()

    /**
     * Tests that {@link LabeledGrid#removeAll()} does not throw an exception
     * when the grid has components.
     */
    @Test
    public void removeAllDoesNotThrowExceptionWhenGridHasComponents() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();
        labeledGrid.addLabelAndComponent(label, component);
        assertTrue("Test is incorrect.", labeledGrid.getComponents().length > 0);

        labeledGrid.removeAll();
    }    // removeAllDoesNotThrowExceptionWhenGridHasComponents()

    /**
     * Tests that {@link LabeledGrid#removeAll()} removes all the grid
     * components.
     */
    @Test
    public void removeAllRemovesAllComponents() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();
        labeledGrid.addLabelAndComponent(label, component);
        assertTrue("Test is incorrect", labeledGrid.getComponents().length > 0);

        labeledGrid.removeAll();

        Component[] components = labeledGrid.getComponents();
        assertTrue(components.length == 0);
    }    // removeAllRemovesAllComponents()

    /* getMinimumLabelWidth */

    /**
     * Tests that {@link LabeledGrid#getMinimumLabelWidth()} does not throw an
     * exception when the grid is empty.
     */
    @Test
    public void getMinimumLabelWidthDoesNotThrowExceptionWhenGridIsEmpty() {
        LabeledGrid labeledGrid = new LabeledGrid();

        labeledGrid.getMinimumLabelWidth();
    }    // getMinimumLabelWidthDoesNotThrowExceptionWhenGridIsEmpty()

    /**
     * Tests that {@link LabeledGrid#getMinimumLabelWidth()} returns 0 when the
     * grid is empty.
     */
    @Test
    public void getMinimumLabelWidthReturnsCorrectValueWhenGridIsEmpty() {
        LabeledGrid labeledGrid = new LabeledGrid();

        int received = labeledGrid.getMinimumLabelWidth();

        int expected = 0;
        assertEquals(expected, received);
    }    // getMinimumLabelWidthReturnsCorrectValueWhenGridIsEmpty()

    /**
     * Tests that {@link LabeledGrid#getMinimumLabelWidth()} does not throw an
     * exception when the grid has a single component-label pair.
     */
    @Test
    public void getMinimumLabelWidthDoesNotThrowExceptionWhenGridHasOneComponentPair() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();
        labeledGrid.addLabelAndComponent(label, component);

        labeledGrid.getMinimumLabelWidth();
    }    // getMinimumLabelWidthDoesNotThrowExceptionWhenGridHasOneComponentPair()

    /**
     * Tests that {@link LabeledGrid#getMinimumLabelWidth()} returns the correct
     * value when the grid has a single component-label pair.
     */
    @Test
    public void getMinimumLabelWidthReturnsCorrectValueWhenGridHasOneComponentPair() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();
        labeledGrid.addLabelAndComponent(label, component);

        int received = labeledGrid.getMinimumLabelWidth();

        int expected = new JLabel(label).getPreferredSize().width;
        assertEquals(expected, received);
    }    // getMinimumLabelWidthReturnsCorrectValueWhenGridHasOneComponentPair()

    /**
     * Tests that {@link LabeledGrid#getMinimumLabelWidth()} does not throw an
     * exception when the grid has multiple component-label pairs.
     */
    @Test
    public void getMinimumLabelWidthDoesNotThrowExceptionWhenGridHasMultipleComponentPairs() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String[] labels = { "0", "1", "this one is longest", "3"};
        int indexOfLongestLabel = 2;
        for (String label : labels) {
            labeledGrid.addLabelAndComponent(label, new JButton());
        }    // for

        labeledGrid.getMinimumLabelWidth();
    }    // getMinimumLabelWidthDoesNotThrowExceptionWhenGridHasMultipleComponentPairs()

    /**
     * Tests that {@link LabeledGrid#getMinimumLabelWidth()} returns the correct
     * value when the grid has multiple component-label pairs.
     */
    @Test
    public void getMinimumLabelWidthReturnsCorrectValueWhenGridHasMultipleComponentPairs() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String[] labels = { "0", "1", "this one is longest", "3"};
        int indexOfLongestLabel = 2;
        for (String label : labels) {
            labeledGrid.addLabelAndComponent(label, new JButton());
        }    // for

        int received = labeledGrid.getMinimumLabelWidth();

        int expected = new JLabel(labels[indexOfLongestLabel]).getPreferredSize().width;
        assertEquals(expected, received);
    }    // getMinimumLabelWidthReturnsCorrectValueWhenGridHasMultipleComponentPairs()

    /* getLabelWidth / setLabelWidth */

    /**
     * Tests that {@link LabeledGrid#getLabelWidth()} does not throw an
     * exception when the grid is empty.
     */
    @Test
    public void getLabelWidthDoesNotThrowExceptionWhenGridIsEmpty() {
        LabeledGrid labeledGrid = new LabeledGrid();

        labeledGrid.getLabelWidth();
    }    // getLabelWidthDoesNotThrowExceptionWhenGridIsEmpty()

    /**
     * Tests that {@link LabeledGrid#getLabelWidth()} does not throw an
     * exception when the grid has a single component-label pair.
     */
    @Test
    public void getLabelWidthDoesNotThrowExceptionWhenGridHasOneComponentPair() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String label = "Foo";
        Component component = new JButton();
        labeledGrid.addLabelAndComponent(label, component);

        labeledGrid.getLabelWidth();
    }    // getLabelWidthDoesNotThrowExceptionWhenGridHasOneComponentPair()

    /**
     * Tests that {@link LabeledGrid#getLabelWidth()} does not throw an
     * exception when the grid has multiple component-label pairs.
     */
    @Test
    public void getLabelWidthDoesNotThrowExceptionWhenGridHasMultipleComponentPairs() {
        LabeledGrid labeledGrid = new LabeledGrid();
        String[] labels = { "0", "1", "this one is longest", "3"};
        int indexOfLongestLabel = 2;
        for (String label : labels) {
            labeledGrid.addLabelAndComponent(label, new JButton());
        }    // for

        labeledGrid.getLabelWidth();
    }    // getLabelWidthDoesNotThrowExceptionWhenGridHasMultipleComponentPairs()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} throws an exception
     * when width is less than the minimum allowed width and the grid has no
     * component-label pairs.
     *
     * This tests requires that {@link LabeledGrid#getMinimumLabelWidth()} is
     * functional.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setLabelWidthThrowsExceptionWhenWidthIsLessThanTheMinimumAndGridHasNoComponentPairs() {
        LabeledGrid labeledGrid = new LabeledGrid();
        int minimumWidth;
        try {
            minimumWidth = labeledGrid.getMinimumLabelWidth();
        } catch (Exception e) {    // try
            fail("Test depends on getMinimumLabelWidth().");
            throw new RuntimeException("This never gets thrown, but makes the compiler realize that the method exits here.");
        }    // catch
        int width = minimumWidth - 1;

        labeledGrid.setLabelWidth(width);
    }    // setLabelWidthThrowsExceptionWhenWidthIsLessThanTheMinimumAndGridHasNoComponentPairs()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} does not throw an
     * exception when width is equal to than the minimum allowed width and the
     * grid has no component-label pairs.
     *
     * This tests requires that {@link LabeledGrid#getMinimumLabelWidth()} is
     * functional.
     */
    @Test
    public void setLabelWidthDoesNotThrowExceptionWhenWidthIsEqualToTheMinimumAndGridHasNoComponentPairs() {
        LabeledGrid labeledGrid = new LabeledGrid();
        int minimumWidth;
        try {
            minimumWidth = labeledGrid.getMinimumLabelWidth();
        } catch (Exception e) {    // try
            fail("Test depends on getMinimumLabelWidth().");
            throw new RuntimeException("This never gets thrown, but makes the compiler realize that the method exits here.");
        }    // catch
        int width = minimumWidth;

        labeledGrid.setLabelWidth(width);
    }    // setLabelWidthDoesNotThrowExceptionWhenWidthIsEqualToTheMinimumAndGridHasNoComponentPairs()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} does not throw an
     * exception when width is greater than the minimum allowed width and the
     * grid has no component-label pairs.
     *
     * This tests requires that {@link LabeledGrid#getMinimumLabelWidth()} is
     * functional.
     */
    @Test
    public void setLabelWidthDoesNotThrowExceptionWhenWidthIsGreaterThanTheMinimumAndGridHasNoComponentPairs() {
        LabeledGrid labeledGrid = new LabeledGrid();
        int minimumWidth;
        try {
            minimumWidth = labeledGrid.getMinimumLabelWidth();
        } catch (Exception e) {    // try
            fail("Test depends on getMinimumLabelWidth().");
            throw new RuntimeException("This never gets thrown, but makes the compiler realize that the method exits here.");
        }    // catch
        int width = minimumWidth + 1;

        labeledGrid.setLabelWidth(width);
    }    // setLabelWidthDoesNotThrowExceptionWhenWidthIsGreaterThanTheMinimumAndGridHasNoComponentPairs()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} throws an exception
     * when width is less than the minimum allowed width and the grid has a
     * single component-label pair.
     *
     * This tests requires that {@link LabeledGrid#getMinimumLabelWidth()} and
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * are functional.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setLabelWidthThrowsExceptionWhenWidthIsLessThanTheMinimumAndGridHasOneComponentPair() {
        LabeledGrid labeledGrid = new LabeledGrid();
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends of addLabelAndComponent().");
        }    // catch
        int minimumWidth;
        try {
            minimumWidth = labeledGrid.getMinimumLabelWidth();
        } catch (Exception e) {    // try
            fail("Test depends on getMinimumLabelWidth().");
            throw new RuntimeException("This never gets thrown, but makes the compiler realize that the method exits here.");
        }    // catch
        int width = minimumWidth - 1;

        labeledGrid.setLabelWidth(width);
    }    // setLabelWidthThrowsExceptionWhenWidthIsLessThanTheMinimumAndGridHasOneComponentPair()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} does not throw an
     * exception when width is equal to than the minimum allowed width and the
     * grid has a single component-label pair.
     *
     * This tests requires that {@link LabeledGrid#getMinimumLabelWidth()} and
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * are functional.
     */
    @Test
    public void setLabelWidthDoesNotThrowExceptionWhenWidthIsEqualToTheMinimumAndGridHasOneComponentPair() {
        LabeledGrid labeledGrid = new LabeledGrid();
        int minimumWidth;
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends of addLabelAndComponent().");
        }    // catch
        try {
            minimumWidth = labeledGrid.getMinimumLabelWidth();
        } catch (Exception e) {    // try
            fail("Test depends on getMinimumLabelWidth().");
            throw new RuntimeException("This never gets thrown, but makes the compiler realize that the method exits here.");
        }    // catch
        int width = minimumWidth;

        labeledGrid.setLabelWidth(width);
    }    // setLabelWidthDoesNotThrowExceptionWhenWidthIsEqualToTheMinimumAndGridHasOneComponentPair()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} does not throw an
     * exception when width is greater than the minimum allowed width and the
     * grid has a single component-label pair.
     *
     * This tests requires that {@link LabeledGrid#getMinimumLabelWidth()} and
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * are functional.
     */
    @Test
    public void setLabelWidthDoesNotThrowExceptionWhenWidthIsGreaterThanTheMinimumAndGridHasOneComponentPair() {
        LabeledGrid labeledGrid = new LabeledGrid();
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends of addLabelAndComponent().");
        }    // catch
        int minimumWidth;
        try {
            minimumWidth = labeledGrid.getMinimumLabelWidth();
        } catch (Exception e) {    // try
            fail("Test depends on getMinimumLabelWidth().");
            throw new RuntimeException("This never gets thrown, but makes the compiler realize that the method exits here.");
        }    // catch
        int width = minimumWidth + 1;

        labeledGrid.setLabelWidth(width);
    }    // setLabelWidthDoesNotThrowExceptionWhenWidthIsGreaterThanTheMinimumAndGridHasOneComponentPair()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} throws an exception
     * when width is less than the minimum allowed width and the grid has
     * multiple component-label pairs.
     *
     * This tests requires that {@link LabeledGrid#getMinimumLabelWidth()} and
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * are functional.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setLabelWidthThrowsExceptionWhenWidthIsLessThanTheMinimumAndGridHasMultipleComponentPairs() {
        LabeledGrid labeledGrid = new LabeledGrid();
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
            labeledGrid.addLabelAndComponent("Bar", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends of addLabelAndComponent().");
        }    // catch
        int minimumWidth;
        try {
            minimumWidth = labeledGrid.getMinimumLabelWidth();
        } catch (Exception e) {    // try
            fail("Test depends on getMinimumLabelWidth().");
            throw new RuntimeException("This never gets thrown, but makes the compiler realize that the method exits here.");
        }    // catch
        int width = minimumWidth - 1;

        labeledGrid.setLabelWidth(width);
    }    // setLabelWidthThrowsExceptionWhenWidthIsLessThanTheMinimumAndGridHasMultipleComponentPairs()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} does not throw an
     * exception when width is equal to than the minimum allowed width and the
     * grid has multiple component-label pairs.
     *
     * This tests requires that {@link LabeledGrid#getMinimumLabelWidth()} and
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * are functional.
     */
    @Test
    public void setLabelWidthDoesNotThrowExceptionWhenWidthIsEqualToTheMinimumAndGridHasMultipleComponentPairs() {
        LabeledGrid labeledGrid = new LabeledGrid();
        int minimumWidth;
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
            labeledGrid.addLabelAndComponent("Bar", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends of addLabelAndComponent().");
        }    // catch
        try {
            minimumWidth = labeledGrid.getMinimumLabelWidth();
        } catch (Exception e) {    // try
            fail("Test depends on getMinimumLabelWidth().");
            throw new RuntimeException("This never gets thrown, but makes the compiler realize that the method exits here.");
        }    // catch
        int width = minimumWidth;

        labeledGrid.setLabelWidth(width);
    }    // setLabelWidthDoesNotThrowExceptionWhenWidthIsEqualToTheMinimumAndGridHasMultipleComponentPairs()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} does not throw an
     * exception when width is greater than the minimum allowed width and the
     * grid has multiple component-label pairs.
     *
     * This tests requires that {@link LabeledGrid#getMinimumLabelWidth()} and
     * {@link LabeledGrid#addLabelAndComponent(java.lang.String, java.awt.Component)}
     * are functional.
     */
    @Test
    public void setLabelWidthDoesNotThrowExceptionWhenWidthIsGreaterThanTheMinimumAndGridHasMultipleComponentPairs() {
        LabeledGrid labeledGrid = new LabeledGrid();
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
            labeledGrid.addLabelAndComponent("Bar", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends of addLabelAndComponent().");
        }    // catch
        int minimumWidth;
        try {
            minimumWidth = labeledGrid.getMinimumLabelWidth();
        } catch (Exception e) {    // try
            fail("Test depends on getMinimumLabelWidth().");
            throw new RuntimeException("This never gets thrown, but makes the compiler realize that the method exits here.");
        }    // catch
        int width = minimumWidth + 1;

        labeledGrid.setLabelWidth(width);
    }    // setLabelWidthDoesNotThrowExceptionWhenWidthIsGreaterThanTheMinimumAndGridHasMultipleComponentPairs()

    /**
     * Tests that the width of the label column is set to 0 upon grid creation.
     */
    @Test
    public void widthOfLabelColumnIsZeroUponGridCreation() {
        LabeledGrid labeledGrid = new LabeledGrid();

        int received = labeledGrid.getLabelWidth();

        int expected = 0;
        assertEquals(expected, received);
    }    // setLabelWidthDoesNotThrowExceptionWhenWidthIsGreaterThanTheMinimumAndGridHasMultipleComponentPairs()

    /**
     * Tests that, when a new component is added to the grid, the width of whose
     * label is greater than the current label column width (which has not been
     * set by {@link LabeledGrid#setLabelWidth(int)}), the label column width is
     * increased to the width of the new label. (This test does not include a
     * call to {@link LabeledGrid#setLabelWidth(int)}.)
     */
    @Test
    public void widthOfLabelColumnIncreasesWhenLargeLabelIsAddedAndWidthHasNotBeenSetBySetLabelWidth() {
        LabeledGrid labeledGrid = new LabeledGrid();
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
            labeledGrid.addLabelAndComponent("Bar", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends on addLabelAndComponent().");
        }    // catch
        int oldLabelColumnWidth = labeledGrid.getLabelWidth();
        String wideLabel = "This label is wider than the current label column width.";
        int wideLabelWidth = new JLabel(wideLabel).getPreferredSize().width;
        assertTrue("Test is incorrect.", wideLabelWidth > oldLabelColumnWidth);
        labeledGrid.addLabelAndComponent(wideLabel, new JButton());

        int received = labeledGrid.getLabelWidth();

        int expected = wideLabelWidth;
        assertEquals(expected, received);
    }    // widthOfLabelColumnIncreasesWhenLargeLabelIsAddedAndWidthHasNotBeenSetBySetLabelWidth()

    /**
     * Tests that, when a new component is added to the grid, the width of whose
     * label is greater than the current label column width (which has been set
     * by {@link LabeledGrid#setLabelWidth(int)}), the label column width is
     * increased to the width of the new label. (This test does not include a
     * call to {@link LabeledGrid#setLabelWidth(int)}.)
     */
    @Test
    public void widthOfLabelColumnIncreasesWhenLargeLabelIsAddedAndWidthHasBeenSetBySetLabelWidth() {
        LabeledGrid labeledGrid = new LabeledGrid();
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
            labeledGrid.addLabelAndComponent("Bar", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends on addLabelAndComponent().");
        }    // catch
        int oldLabelColumnWidth = labeledGrid.getLabelWidth();
        labeledGrid.setLabelWidth(oldLabelColumnWidth + 1);
        oldLabelColumnWidth = labeledGrid.getLabelWidth();
        String wideLabel = "This label is wider than the current label column width.";
        int wideLabelWidth = new JLabel(wideLabel).getPreferredSize().width;
        assertTrue("Test is incorrect.", wideLabelWidth > oldLabelColumnWidth);
        labeledGrid.addLabelAndComponent(wideLabel, new JButton());

        int received = labeledGrid.getLabelWidth();

        int expected = wideLabelWidth;
        assertEquals(expected, received);
    }    // widthOfLabelColumnIncreasesWhenLargeLabelIsAddedAndWidthHasBeenSetBySetLabelWidth()

    /**
     * Tests that {@link LabeledGrid#setLabelWidth(int)} sets the label column
     * width.
     */
    @Test
    public void setLabelWidthSetsTheLabelColumnWidth() {
        LabeledGrid labeledGrid = new LabeledGrid();
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
            labeledGrid.addLabelAndComponent("Bar", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends on addLabelAndComponent().");
        }    // catch
        int oldLabelColumnWidth = labeledGrid.getLabelWidth();
        int newLabelColumnWidth = oldLabelColumnWidth + 1;

        labeledGrid.setLabelWidth(newLabelColumnWidth);

        int received = labeledGrid.getLabelWidth();
        int expected = newLabelColumnWidth;
        assertEquals(expected, received);
    }    // setLabelWidthSetsTheLabelColumnWidth()

    /**
     * Tests that {@link LabeledGrid#removeAll()} resets the label column width
     * to 0.
     */
    @Test
    public void removeAllResetsLabelColumnWidthToZero() {
        LabeledGrid labeledGrid = new LabeledGrid();
        try {
            labeledGrid.addLabelAndComponent("Foo", new JButton());
            labeledGrid.addLabelAndComponent("Bar", new JButton());
        } catch (Exception e) {    // try
            fail("Test depends on addLabelAndComponent().");
        }    // catch
        int oldLabelColumnWidth = labeledGrid.getLabelWidth();
        int newLabelColumnWidth = oldLabelColumnWidth + 1;
        labeledGrid.setLabelWidth(newLabelColumnWidth);
        assertTrue("Test cannot be completed: label column width incorrectly == 0.", labeledGrid.getLabelWidth() != 0);

        labeledGrid.removeAll();

        int received = labeledGrid.getLabelWidth();
        int expected = 0;
        assertEquals(expected, received);
    }    // removeAllResetsLabelColumnWidthToZero()

}    // LabeledGridTest
