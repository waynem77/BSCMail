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
import javax.swing.JLabel;
import javax.swing.JTextArea;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link LabeledComponent}.
 *
 * @author Wayne Miller
 */
public class LabeledComponentTest {

    /* constructor */

    /**
     * Tests that
     * {@link LabeledComponent#LabeledComponent(java.lang.String, java.awt.Component)}
     * throws an exception when labelText is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenLabelIsNull() {
        String labelText = null;
        JTextArea component = new JTextArea();

        LabeledComponent<JTextArea> labeledComponent = new LabeledComponent<>(labelText, component);
    }    // constructorThrowsExceptionWhenLabelIsNull()

    /**
     * Tests that
     * {@link LabeledComponent#LabeledComponent(java.lang.String, java.awt.Component)}
     * throws an exception when component is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenComponentIsNull() {
        String labelText = "Foo";
        JTextArea component = null;

        LabeledComponent<JTextArea> labeledComponent = new LabeledComponent<>(labelText, component);
    }    // constructorThrowsExceptionWhenComponentIsNull()

    /**
     * Tests that
     * {@link LabeledComponent#LabeledComponent(java.lang.String, java.awt.Component)}
     * does not throw an exception when neither component is null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenNeitherArgumentIsNull() {
        String labelText = "Foo";
        JTextArea component = new JTextArea();

        LabeledComponent<JTextArea> labeledComponent = new LabeledComponent<>(labelText, component);
    }    // constructorDoesNotThrowExceptionWhenNeitherArgumentIsNull()

    /* getLabel */

    /**
     * Tests that {@link LabeledComponent#getLabel()} does not throw an
     * exception.
     */
    @Test
    public void getLabelDoesNotThrowException() {
        String labelText = "Foo";
        JTextArea component = new JTextArea();
        LabeledComponent<JTextArea> labeledComponent = new LabeledComponent<>(labelText, component);

        labeledComponent.getLabel();
    }    // getLabelDoesNotThrowException()

    /**
     * Tests that {@link LabeledComponent#getLabel()} does not return null.
     */
    @Test
    public void getLabelDoesNotReturnNull() {
        String labelText = "Foo";
        JTextArea component = new JTextArea();
        LabeledComponent<JTextArea> labeledComponent = new LabeledComponent<>(labelText, component);

        JLabel received = labeledComponent.getLabel();

        assertNotNull(received);
    }    // getLabelDoesNotReturnNull()

    /**
     * Tests that {@link LabeledComponent#getLabel()} returns a label with the
     * correct text.
     */
    @Test
    public void getLabelReturnsLabelWithCorrectText() {
        String labelText = "Foo";
        JTextArea component = new JTextArea();
        LabeledComponent<JTextArea> labeledComponent = new LabeledComponent<>(labelText, component);

        JLabel label = labeledComponent.getLabel();

        String received = label.getText();
        String expected = labelText;
        assertEquals(expected, received);
    }    // getLabelDoesNotReturnNull()

    /* getComponent */

    /**
     * Tests that {@link LabeledComponent#getComponent()} does not throw an
     * exception.
     */
    @Test
    public void getComponentDoesNotThrowException() {
        String labelText = "Foo";
        JTextArea component = new JTextArea();
        LabeledComponent<JTextArea> labeledComponent = new LabeledComponent<>(labelText, component);

        labeledComponent.getComponent();
    }    // getComponentDoesNotThrowException()

    /**
     * Tests that {@link LabeledComponent#getComponent()} does not return null.
     */
    @Test
    public void getComponentDoesNotReturnNull() {
        String labelText = "Foo";
        JTextArea component = new JTextArea();
        LabeledComponent<JTextArea> labeledComponent = new LabeledComponent<>(labelText, component);

        JTextArea received = labeledComponent.getComponent();

        assertNotNull(received);
    }    // getComponentDoesNotReturnNull()

    /**
     * Tests that {@link LabeledComponent#getComponent()} returns the component
     * passed to the constructor.
     */
    @Test
    public void getComponentReturnsCorrectValue() {
        String labelText = "Foo";
        JTextArea component = new JTextArea();
        LabeledComponent<JTextArea> labeledComponent = new LabeledComponent<>(labelText, component);

        JTextArea received = labeledComponent.getComponent();

        JTextArea expected = component;
        Assert.assertSame(expected, received);
    }    // getComponentReturnsCorrectValue()

}    // LabeledComponentTest
