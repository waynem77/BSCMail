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

import bscmail.EventProperty;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EventPropertyControl}
 *
 * @author Wayne Miller
 */
public class EventPropertyControlTest {

    /* constructor */

    /**
     * Tests that
     * {@link EventPropertyControl#EventPropertyControl(bscmail.EventProperty)}
     * throws a {@link NullPointerException} when eventProperty is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenEventPropertyIsNull() {
        EventProperty eventProperty = null;

        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);
    }    // constructorThrowsExceptionWhenEventPropertyIsNull()

    /**
     * Tests that
     * {@link EventPropertyControl#EventPropertyControl(bscmail.EventProperty)}
     * does not throw an exception when eventProperty is not null.
     */
    @Test
    public void constructorDoesNotThrowExceptionWhenEventPropertyIsNotNull() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);
    }    // constructorDoesNotThrowExceptionWhenEventPropertyIsNotNull()

    /**
     * Tests that
     * {@link EventPropertyControl#EventPropertyControl(bscmail.EventProperty)}
     * initializes the control text to the default text of the eventProperty.
     */
    @Test
    public void constructorCorrectlyInitializesText() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);

        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);

        String received = eventPropertyControl.getText();
        String expected = eventProperty.getDefaultValue();
        assertEquals(expected, received);
    }    // constructorCorrectlyInitializesText()

    /* getEventProperty */

    /**
     * Tests that {@link EventPropertyControl#getEventProperty()} does not throw
     * an exception.
     */
    @Test
    public void getEventPropertyDoesNotThrowException() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);

        eventPropertyControl.getEventProperty();
    }    // getEventPropertyDoesNotThrowException()

    /**
     * Tests that {@link EventPropertyControl#getEventProperty()} does not
     * return null.
     */
    @Test
    public void getEventPropertyDoesNotReturnNull() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);

        EventProperty received = eventPropertyControl.getEventProperty();

        assertNotNull(received);
    }    // getEventPropertyDoesNotReturnNull()

    /**
     * Tests that {@link EventPropertyControl#getEventProperty()} returns the
     * EventProperty passed to the constructor.
     */
    @Test
    public void getEventPropertyReturnsCorrectValue() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);

        EventProperty received = eventPropertyControl.getEventProperty();

        EventProperty expected = eventProperty;
        assertEquals(expected, received);
    }    // getEventPropertyReturnsCorrectValue()

    /* getValue */

    /**
     * Tests that {@link EventPropertyControl#getValue()} does not throw an
     * exception when there is no text in the control.
     */
    @Test
    public void getValueDoesNotThrowExceptionWhenThereIsNoText() {
        String name = "foo";
        String defaultValue = "";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);

        eventPropertyControl.getValue();
    }    // getValueDoesNotThrowExceptionWhenThereIsNoText()

    /**
     * Tests that {@link EventPropertyControl#getValue()} does not throw an
     * exception when there is text in the control.
     */
    @Test
    public void getValueDoesNotThrowExceptionWhenThereIsText() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);
        String inputText = "baz";
        eventPropertyControl.setText(inputText);

        eventPropertyControl.getValue();
    }    // getValueDoesNotThrowExceptionWhenThereIsText()

    /**
     * Tests that {@link EventPropertyControl#getValue()} does not return null
     * when there is no text in the control.
     */
    @Test
    public void getValueDoesNotReturnNullWhenThereIsNoText() {
        String name = "foo";
        String defaultValue = "";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);

        String received = eventPropertyControl.getValue();

        assertNotNull(received);
    }    // getValueDoesNotReturnNullWhenThereIsNoText()

    /**
     * Tests that {@link EventPropertyControl#getValue()} does not return null
     * when there is text in the control.
     */
    @Test
    public void getValueDoesNotReturnNullWhenThereIsText() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);
        String inputText = "baz";
        eventPropertyControl.setText(inputText);

        String received = eventPropertyControl.getValue();

        assertNotNull(received);
    }    // getValueDoesNotReturnNullWhenThereIsText()

    /**
     * Tests that {@link EventPropertyControl#getValue()} returns an empty
     * string when there is no text in the control.
     */
    @Test
    public void getValueReturnsCorrectValueWhenThereIsNoText() {
        String name = "foo";
        String defaultValue = "";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);

        String received = eventPropertyControl.getValue();

        String expected = "";
        assertEquals(expected, received);
    }    // getValueReturnsCorrectValueWhenThereIsNoText()

    /**
     * Tests that {@link EventPropertyControl#getValue()} returns the text
     * entered into the control.
     */
    @Test
    public void getValueReturnsCorrectValueWhenThereIsText() {
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        EventPropertyControl eventPropertyControl = new EventPropertyControl(eventProperty);
        String inputText = "baz";
        eventPropertyControl.setText(inputText);

        String received = eventPropertyControl.getValue();

        String expected = inputText;
        assertEquals(expected, received);
    }    // getValueReturnsCorrectValueWhenThereIsText()

}    // EventPropertyControlTest
