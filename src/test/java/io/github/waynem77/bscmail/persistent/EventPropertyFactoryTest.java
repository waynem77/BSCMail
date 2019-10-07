/*
 * Copyright © 2016-2019 its authors.  See the file "AUTHORS" for details.
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

package io.github.waynem77.bscmail.persistent;

import io.github.waynem77.bscmail.persistent.EventProperty.Factory;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link EventProperty.Factory}.
 *
 * @author Wayne Miller
 */
public class EventPropertyFactoryTest {

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)}
     * throws a {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructReadWritableThrowsExceptionWhenPropertiesIsNull() {
        Factory factory = EventProperty.getEventPropertyFactory();
        Map<String, Object> properties = null;

        factory.constructReadWritable(properties);
    }    // constructReadWritableThrowsExceptionWhenPropertiesIsNull()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty() {
        Factory factory = EventProperty.getEventPropertyFactory();
        Map<String, Object> properties = new HashMap<>();

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not return null when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty() {
        Factory factory = EventProperty.getEventPropertyFactory();
        Map<String, Object> properties = new HashMap<>();

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "name".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingName() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String defaultValue = "bar";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingName()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "name".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingName() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("value", value);

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingName()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value for "name".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullName() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = null;
        String defaultValue = "bar";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullName()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value for "name".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullhenPropertiesHasNullName() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = null;
        String defaultValue = "bar";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullhenPropertiesHasNullName()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "defaultValue".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingDefaultValue() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("value", value);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingDefaultValue()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "defaultValue".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingDefaultValue() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("value", value);

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingDefaultValue()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value "defaultValue".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullDefaultValue() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = null;
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissinDefaultValue()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value "defaultValue".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasNullDefaultValue() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = null;
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasNullDefaultValue()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is missing "value".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingValue() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("value", value);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissingValue()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not return null when properties is missing "value".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingValue() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = "bar";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsMissingValue()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value for "value".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullValue() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = "bar";
        String value = null;
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsMissinValue()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value for "value".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasNullValue() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = "bar";
        String value = null;
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasNullValue()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties all required values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = "bar";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not return null when properties all required values.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasAllValues() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = "bar";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasAllValues()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)}
     * returns the correct value when properties all required values.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasAllValues() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = "bar";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        EventProperty received = factory.constructReadWritable(properties);

        EventProperty expected = new EventProperty(name, defaultValue);
        expected.setValue(value);
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasAllValues()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has extraneous values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousValues() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = "bar";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);
        properties.put("extra", "baz");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not return null when properties has extraneous values.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousValues() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = "bar";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);
        properties.put("extra", "baz");

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)}
     * returns the correct value when properties has extraneous values.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasExtraneousValues() {
        Factory factory = EventProperty.getEventPropertyFactory();
        String name = "foo";
        String defaultValue = "bar";
        String value = "baz";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);
        properties.put("extra", "baz");

        EventProperty received = factory.constructReadWritable(properties);

        EventProperty expected = new EventProperty(name, defaultValue);
        expected.setValue(value);
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not throw an exception when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects() {
        Factory factory = EventProperty.getEventPropertyFactory();
        Object name = 1;
        Object defaultValue = 2.0;
        Object value = new LinkedList();
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} does
     * not returns null when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects() {
        Factory factory = EventProperty.getEventPropertyFactory();
        Object name = 1;
        Object defaultValue = 2.0;
        Object value = new LinkedList();
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("defaultValue", defaultValue);
        properties.put("value", value);

        EventProperty received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

    /**
     * Tests that {@link EventProperty.Factory#constructReadWritable(Map)} acts
     * as the reverse of {@link EventProperty#getReadWritableProperties()}.
     */
    @Test
    public void constructReadWritableWorksReflexively() {
        String name = "foo";
        String defaultValue = "bar";
        String value = "baz";
        EventProperty originalEventProperty = new EventProperty(name, defaultValue);
        originalEventProperty.setValue(value);

        Map<String, Object> properties = originalEventProperty.getReadWritableProperties();
        Factory factory = EventProperty.getEventPropertyFactory();
        EventProperty received = factory.constructReadWritable(properties);

        EventProperty expected = originalEventProperty;
        assertEquals(expected, received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

}    // EventPropertyFactoryTest
