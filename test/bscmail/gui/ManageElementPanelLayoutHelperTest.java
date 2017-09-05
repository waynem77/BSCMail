/*
 * Copyright © 2014-2017 its authors.  See the file "AUTHORS" for details.
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

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.Collection;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ManageElementPanelLayoutHelper}.
 *
 * @author Wayne Miller
 */
public class ManageElementPanelLayoutHelperTest {

    /* constructor */

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#ManageElementPanelLayoutHelper(Container)}
     * throws a {@link NullPointerException} when container is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenContainerIsNull() {
        Container container = null;

        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
    }    // constructorThrowsExceptionWhenContainerIsNull()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#ManageElementPanelLayoutHelper(Container)}
     * does not throw an exception when the container is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenContainerIsNotNull() {
        Container container = new JPanel();

        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
    }    // constructorDoesNotThrowExceptionWhenContainerIsNotNull()

    /* setLayoutManager */

    /**
     * Tests that {@link ManageElementPanelLayoutHelper#setLayoutManager()} does
     * not throw an exception.
     */
    @Test
    public void setLayoutManagerDoesNotThrowException() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);

        layoutHelper.setLayoutManager();
    }    // setLayoutManagerDoesNotThrowException()

    /**
     * Tests that {@link ManageElementPanelLayoutHelper#setLayoutManager()} sets
     * the appropriate layout manager.
     */
    @Test
    public void setLayoutManagerSetsLayoutManagerProperly() {
        Container container = new JPanel();
        container.setLayout(new FlowLayout());
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);

        layoutHelper.setLayoutManager();

        Class expected = GridBagLayout.class;
        Class received = container.getLayout().getClass();
        assertEquals(expected, received);
    }    // setLayoutManagerSetsLayoutManagerProperly()

    /* addComponent (2 parameters) */

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * throws a {@link NullPointerException} when the name is null.
     */
    @Test(expected = NullPointerException.class)
    public void addComponent2ParametersThrowsExceptionWhenNameIsNull() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        String name = null;
        Component component = new JTextField();

        layoutHelper.addComponent(name, component);
    }    // addComponent2ParametersThrowsExceptionWhenNameIsNull()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * throws a {@link NullPointerException} when the component is null.
     */
    @Test(expected = NullPointerException.class)
    public void addComponent2ParametersThrowsExceptionWhenComponentIsNull() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        String name = "Foo";
        Component component = null;

        layoutHelper.addComponent(name, component);
    }    // addComponent2ParametersThrowsExceptionWhenComponentIsNull()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * does not throw an exception when neither parameter is null.
     */
    @Test
    public void addComponent2ParametersDoesNotThrowExceptionWhenNoParameterIsNull() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        String name = "Foo";
        Component component = new JTextField();

        layoutHelper.addComponent(name, component);
    }    // addComponent2ParametersDoesNotThrowExceptionWhenNoParameterIsNull()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * adds exactly two components to the container.
     */
    @Test
    public void addComponent2ParametersAddsExactlyTwoComponentsToTheContainer() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();

        layoutHelper.addComponent(name, component);

        Component[] components = container.getComponents();
        int expected = 2;
        int received = components.length;
        assertEquals(expected, received);
    }    // addComponent2ParametersAddsExactlyTwoComponentsToTheContainer()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * adds a label to the container.
     */
    @Test
    public void addComponent2ParametersAddsALabelToTheContainer() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();

        layoutHelper.addComponent(name, component);

        boolean hasLabel = false;
        Component[] components = container.getComponents();
        for (Component c : components) {
            if (c instanceof JLabel) {
                hasLabel = true;
                break;
            }    // if
        }    // for
        assertTrue(hasLabel);
    }    // addComponent2ParametersAddsALabelToTheContainer()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * adds the correct label to the container.
     */
    @Test
    public void addComponent2ParametersAddsTheCorrectLabelToTheContainer() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();

        layoutHelper.addComponent(name, component);

        Component[] components = container.getComponents();
        String expected = name;
        String received = null;
        for (Component c : components) {
            if (c instanceof JLabel) {
                JLabel label = (JLabel)c;
                received = label.getText();
                break;
            }    // if
        }    // for
        assertEquals(expected, received);
    }    // addComponent2ParametersAddsTheCorrectLabelToTheContainer()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component)}
     * adds the component to the container.
     */
    @Test
    public void addComponent2ParametersAddsTheComponentToTheContainer() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();

        layoutHelper.addComponent(name, component);

        Collection<Component> components = Arrays.asList(container.getComponents());
        boolean hasContainer = components.contains(component);
        assertTrue(hasContainer);
    }    // addComponent2ParametersAddsTheComponentToTheContainer()

    /* addComponent (3 parameters) */

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * throws a {@link NullPointerException} when the name is null.
     */
    @Test(expected = NullPointerException.class)
    public void addComponent3ParametersThrowsExceptionWhenNameIsNull() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        String name = null;
        Component component = new JTextField();
        boolean sticky = true;

        layoutHelper.addComponent(name, component, sticky);
    }    // addComponent3ParametersThrowsExceptionWhenNameIsNull()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * throws a {@link NullPointerException} when the component is null.
     */
    @Test(expected = NullPointerException.class)
    public void addComponent3ParametersThrowsExceptionWhenComponentIsNull() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        String name = "Foo";
        Component component = null;
        boolean sticky = true;

        layoutHelper.addComponent(name, component, sticky);
    }    // addComponent3ParametersThrowsExceptionWhenComponentIsNull()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * does not throw an exception when sticky is false.
     */
    @Test
    public void addComponent3ParametersDoesNotThrowExceptionWhenStickyIsFalse() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = false;

        layoutHelper.addComponent(name, component, sticky);
    }    // addComponent3ParametersDoesNotThrowExceptionWhenStickyIsFalse()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * does not throw an exception when sticky is true.
     */
    @Test
    public void addComponent3ParametersDoesNotThrowExceptionWhenStickyIsTrue() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = true;

        layoutHelper.addComponent(name, component, sticky);
    }    // addComponent3ParametersDoesNotThrowExceptionWhenStickyIsTrue()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * adds exactly two components to the container when sticky is true.
     */
    @Test
    public void addComponent3ParametersAddsExactlyTwoComponentsToTheContainerWhenStickyIsTrue() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = true;

        layoutHelper.addComponent(name, component, sticky);

        Component[] components = container.getComponents();
        int expected = 2;
        int received = components.length;
        assertEquals(expected, received);
    }    // addComponent3ParametersAddsExactlyTwoComponentsToTheContainerWhenStickyIsTrue()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * adds exactly two components to the container when sticky is false.
     */
    @Test
    public void addComponent3ParametersAddsExactlyTwoComponentsToTheContainerWhenStickyIsFalse() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = false;

        layoutHelper.addComponent(name, component, sticky);

        Component[] components = container.getComponents();
        int expected = 2;
        int received = components.length;
        assertEquals(expected, received);
    }    // addComponent3ParametersAddsExactlyTwoComponentsToTheContainerWhenStickyIsFalse()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * adds a label to the container when sticky is true.
     */
    @Test
    public void addComponent3ParametersAddsALabelToTheContainerWhenStickyIsTrue() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = true;

        layoutHelper.addComponent(name, component, sticky);

        boolean hasLabel = false;
        Component[] components = container.getComponents();
        for (Component c : components) {
            if (c instanceof JLabel) {
                hasLabel = true;
                break;
            }    // if
        }    // for
        assertTrue(hasLabel);
    }    // addComponent3ParametersAddsALabelToTheContainerWhenStickyIsTrue()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * adds a label to the container when sticky is false.
     */
    @Test
    public void addComponent3ParametersAddsALabelToTheContainerWhenStickyIsFalse() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = false;

        layoutHelper.addComponent(name, component, sticky);

        boolean hasLabel = false;
        Component[] components = container.getComponents();
        for (Component c : components) {
            if (c instanceof JLabel) {
                hasLabel = true;
                break;
            }    // if
        }    // for
        assertTrue(hasLabel);
    }    // addComponent3ParametersAddsALabelToTheContainerWhenStickyIsFalse()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * adds the correct label to the container when sticky is true.
     */
    @Test
    public void addComponent3ParametersAddsTheCorrectLabelToTheContainerWhenStickyIsTrue() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = true;

        layoutHelper.addComponent(name, component, sticky);

        Component[] components = container.getComponents();
        String expected = name;
        String received = null;
        for (Component c : components) {
            if (c instanceof JLabel) {
                JLabel label = (JLabel)c;
                received = label.getText();
                break;
            }    // if
        }    // for
        assertEquals(expected, received);
    }    // addComponent3ParametersAddsTheCorrectLabelToTheContainerWhenStickyIsTrue()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * adds the correct label to the container when sticky is false.
     */
    @Test
    public void addComponent3ParametersAddsTheCorrectLabelToTheContainerWhenStickyIsFalse() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = false;

        layoutHelper.addComponent(name, component, sticky);

        Component[] components = container.getComponents();
        String expected = name;
        String received = null;
        for (Component c : components) {
            if (c instanceof JLabel) {
                JLabel label = (JLabel)c;
                received = label.getText();
                break;
            }    // if
        }    // for
        assertEquals(expected, received);
    }    // addComponent3ParametersAddsTheCorrectLabelToTheContainerWhenStickyIsFalse()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * adds the component to the container when sticky is true.
     */
    @Test
    public void addComponent3ParametersAddsTheComponentToTheContainerWhenStickyIsTrue() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = true;

        layoutHelper.addComponent(name, component, sticky);

        Collection<Component> components = Arrays.asList(container.getComponents());
        boolean hasContainer = components.contains(component);
        assertTrue(hasContainer);
    }    // addComponent3ParametersAddsTheComponentToTheContainerWhenStickyIsTrue()

    /**
     * Tests that
     * {@link ManageElementPanelLayoutHelper#addComponent(String, Component, boolean)}
     * adds the component to the container when sticky is false.
     */
    @Test
    public void addComponent3ParametersAddsTheComponentToTheContainerWhenStickyIsFalse() {
        Container container = new JPanel();
        ManageElementPanelLayoutHelper layoutHelper = new ManageElementPanelLayoutHelper(container);
        layoutHelper.setLayoutManager();
        container.removeAll();
        String name = "Foo";
        Component component = new JTextField();
        boolean sticky = false;

        layoutHelper.addComponent(name, component, sticky);

        Collection<Component> components = Arrays.asList(container.getComponents());
        boolean hasContainer = components.contains(component);
        assertTrue(hasContainer);
    }    // addComponent3ParametersAddsTheComponentToTheContainerWhenStickyIsFalse()

}    // ManageElementPanelLayoutHelperTest
