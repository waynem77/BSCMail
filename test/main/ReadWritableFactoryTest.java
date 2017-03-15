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

package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Abstract base class for unit tests of implementations of
 * {@link ReadWritableFactory}.
 *
 * @author Wayne Miller
 * @param <T> The implementation of ReadWritable whose factory is being tested
 * @since 3.1
 */
abstract public class ReadWritableFactoryTest<T extends ReadWritable> {

    /**
     * Returns the ReadWritableFactory implementation being tested.
     *
     * @return the ReadWritableFactory implementation being tested
     */
    abstract protected ReadWritableFactory<T> getTestFactory();
    
    /**
     * Returns a properties map appropriate for testing. It is important that
     * the map returned by this method contain all the properties that the
     * implementation of
     * {@link ReadWritableFactory#constructReadWritable(java.util.Map)} will
     * expect; unit tests will use this method to determine which properties to
     * test.
     *
     * @return a properties map appropriate for testing
     */
    abstract protected Map<String, Object> getTestProperties();
    
    /**
     * Returns the ReadWriteable that ought to be created from the given
     * properties.  This method must never return null.
     *
     * @param properties the properties; should be the (possibly modified)
     * return value of {@link #getTestProperties()}
     * @return the ReadWriteable that ought to be created from the given
     * properties
     */
    abstract protected T getReadWritableFromProperties(Map<String, Object> properties);
    
    /*
     * Unit tests
     */

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} throws
     * a {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructReadWritableThrowsExceptionWhenPropertiesIsNull() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = null;

        factory.constructReadWritable(properties);
    }    // constructReadWritableMapThrowsExceptionWhenPropertiesIsNull()
    
    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not throw an exception when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = new HashMap<>();

        factory.constructReadWritable(properties);
    }    // constructReadWritableMapDoesNotThrowExceptionWhenPropertiesIsEmpty()
    
    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not return null when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = new HashMap<>();

        T received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableMapDoesNotReturnNullWhenPropertiesIsEmpty()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} returns
     * the correct value when properties is an empty map.
     */
    @Test
    public void constructReadWritableMapReturnsCorrectValueWhenPropertiesIsEmpty() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = new HashMap<>();
        
        T received = factory.constructReadWritable(properties);

        T expected = getReadWritableFromProperties(properties);
        assertEquals(expected, received);
    }    // constructReadWritableMapReturnsCorrectValueWhenPropertiesIsEmpty()
    
    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not throw an exception when any property is missing.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenAnyPropertyIsMissing() {
        ReadWritableFactory<T> factory = getTestFactory();
        Set<String> propertyKeys = getTestProperties().keySet();

        for (String propertyKey : propertyKeys) {
            Map<String, Object> properties = getTestProperties();
            properties.remove(propertyKey);
            try {
                factory.constructReadWritable(properties);
            } catch (Exception e) {    // try
                fail("Factory threw " + e + " when key: " + propertyKey + " was missing.");
            }    // catch
        }    // for
    }    // constructReadWritableDoesNotThrowExceptionWhenAnyPropertyIsMissing()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not return null when any property is missing.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenAnyPropertyIsMissing() {
        ReadWritableFactory<T> factory = getTestFactory();
        Set<String> propertyKeys = getTestProperties().keySet();

        for (String propertyKey : propertyKeys) {
            Map<String, Object> properties = getTestProperties();
            properties.remove(propertyKey);

            T received = factory.constructReadWritable(properties);
            
            if (received == null) {
                fail("Return value was null when key: " + propertyKey + " was missing.");
            }    // if
        }    // for
    }    // constructReadWritableDoesNotReturnNullWhenAnyPropertyIsMissing()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} returns
     * the correct value when any property is missing.
     */
    @Test
    public void constructReadWritableMapReturnsCorrectValueWhenAnyPropertyIsMissing() {
        ReadWritableFactory<T> factory = getTestFactory();
        Set<String> propertyKeys = getTestProperties().keySet();

        for (String propertyKey : propertyKeys) {
            Map<String, Object> properties = getTestProperties();
            properties.remove(propertyKey);

            T received = factory.constructReadWritable(properties);

            T expected = getReadWritableFromProperties(properties);
            if (! expected.equals(received)) {
                fail("Expected " + expected + ", received " + received + " when key: " + propertyKey + " was missing.");
            }    // if
        }    // for
    }    // constructReadWritableMapReturnsCorrectValueWhenAnyPropertyIsMissing()
    
    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not throw an exception when any property is null.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenAnyPropertyIsNull() {
        ReadWritableFactory<T> factory = getTestFactory();
        Set<String> propertyKeys = getTestProperties().keySet();

        for (String propertyKey : propertyKeys) {
            Map<String, Object> properties = getTestProperties();
            properties.put(propertyKey, null);
            try {
                factory.constructReadWritable(properties);
            } catch (Exception e) {    // try
                fail("Factory threw " + e + " when key: " + propertyKey + " was missing.");
            }    // catch
        }    // for
    }    // constructReadWritableDoesNotThrowExceptionWhenAnyPropertyIsNull()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not return null when any property is null.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenAnyPropertyIsNull() {
        ReadWritableFactory<T> factory = getTestFactory();
        Set<String> propertyKeys = getTestProperties().keySet();

        for (String propertyKey : propertyKeys) {
            Map<String, Object> properties = getTestProperties();
            properties.put(propertyKey, null);

            T received = factory.constructReadWritable(properties);
            
            if (received == null) {
                fail("Return value was null when key: " + propertyKey + " was missing.");
            }    // if
        }    // for
    }    // constructReadWritableDoesNotReturnNullWhenAnyPropertyIsNull()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} returns
     * the correct value when any property is an unexpected object.
     */
    @Test
    public void constructReadWritableMapReturnsCorrectValueWhenAnyPropertyIsNull() {
        ReadWritableFactory<T> factory = getTestFactory();
        Set<String> propertyKeys = getTestProperties().keySet();

        for (String propertyKey : propertyKeys) {
            Map<String, Object> properties = getTestProperties();
            properties.put(propertyKey, null);
            
            T received = factory.constructReadWritable(properties);

            T expected = getReadWritableFromProperties(properties);
            if (! expected.equals(received)) {
                fail("Expected " + expected + ", received " + received + " when key: " + propertyKey + " was missing.");
            }    // if
        }    // for
    }    // constructReadWritableMapReturnsCorrectValueWhenAnyPropertyIsNull()
    
    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not throw an exception when any property is of an unexpected type.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenAnyPropertyIsOfUnexpectedType() {
        ReadWritableFactory<T> factory = getTestFactory();
        Set<String> propertyKeys = getTestProperties().keySet();

        for (String propertyKey : propertyKeys) {
            Map<String, Object> properties = getTestProperties();
            Object oldValue = properties.get(propertyKey);
            Object newValue = (oldValue instanceof String) ? -1.0 : "unexpected";
            properties.put(propertyKey, newValue);
            try {
                factory.constructReadWritable(properties);
            } catch (Exception e) {    // try
                fail("Factory threw " + e + " when key: " + propertyKey + " was missing.");
            }    // catch
        }    // for
    }    // constructReadWritableDoesNotThrowExceptionWhenAnyPropertyIsOfUnexpectedType()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not return null when any property is of an unexpected type.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenAnyPropertyIsOfUnexpectedType() {
        ReadWritableFactory<T> factory = getTestFactory();
        Set<String> propertyKeys = getTestProperties().keySet();

        for (String propertyKey : propertyKeys) {
            Map<String, Object> properties = getTestProperties();
            Object oldValue = properties.get(propertyKey);
            Object newValue = (oldValue instanceof String) ? -1.0 : "unexpected";
            properties.put(propertyKey, newValue);

            T received = factory.constructReadWritable(properties);
            
            if (received == null) {
                fail("Return value was null when key: " + propertyKey + " was missing.");
            }    // if
        }    // for
    }    // constructReadWritableDoesNotReturnNullWhenAnyPropertyIsOfUnexpectedType()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} returns
     * the correct value when any property is of an unexpected type.
     */
    @Test
    public void constructReadWritableMapReturnsCorrectValueWhenAnyPropertyIsOfUnexpectedType() {
        ReadWritableFactory<T> factory = getTestFactory();
        Set<String> propertyKeys = getTestProperties().keySet();

        for (String propertyKey : propertyKeys) {
            Map<String, Object> properties = getTestProperties();
            Object oldValue = properties.get(propertyKey);
            Object newValue = (oldValue instanceof String) ? -1.0 : "unexpected";
            properties.put(propertyKey, newValue);

            T received = factory.constructReadWritable(properties);

            T expected = getReadWritableFromProperties(properties);
            if (! expected.equals(received)) {
                fail("Expected " + expected + ", received " + received + " when key: " + propertyKey + " was missing.");
            }    // if
        }    // for
    }    // constructReadWritableMapReturnsCorrectValueWhenAnyPropertyIsOfUnexpectedType()
    
    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not throw an exception when properties has all the necessary properties.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsCorrect() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = getTestProperties();

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsCorrect()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not return null when properties has all the necessary properties.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsCorrect() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = getTestProperties();

        T received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsCorrect()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} returns
     * the correct value when properties has all the necessary properties.
     */
    @Test
    public void constructReadWritableMapReturnsCorrectValueWhenPropertiesIsCorrect() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = getTestProperties();

        T received = factory.constructReadWritable(properties);

        T expected = getReadWritableFromProperties(properties);
        assertEquals(expected, received);
    }    // constructReadWritableMapReturnsCorrectValueWhenPropertiesIsCorrect()
    
    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not throw an exception when properties has extraneous properties.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousProperties() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = getTestProperties();
        Set<String> propertyKeys = properties.keySet();
        String newKey = "x";
        while (propertyKeys.contains(newKey)) {
            newKey += "x";
        }    // while
        properties.put(newKey, -1.0);
        
        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousProperties()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not return null when properties has extraneous properties.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousProperties() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = getTestProperties();
        Set<String> propertyKeys = properties.keySet();
        String newKey = "x";
        while (propertyKeys.contains(newKey)) {
            newKey += "x";
        }    // while
        properties.put(newKey, -1.0);

        T received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousProperties()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} returns
     * the correct value when properties has extraneous properties.
     */
    @Test
    public void constructReadWritableMapReturnsCorrectValueWhenPropertiesHasExtraneousProperties() {
        ReadWritableFactory<T> factory = getTestFactory();
        Map<String, Object> properties = getTestProperties();
        Set<String> propertyKeys = properties.keySet();
        String newKey = "x";
        while (propertyKeys.contains(newKey)) {
            newKey += "x";
        }    // while
        properties.put(newKey, -1.0);

        T received = factory.constructReadWritable(properties);

        T expected = getReadWritableFromProperties(properties);
        assertEquals(expected, received);
    }    // constructReadWritableMapReturnsCorrectValueWhenPropertiesHasExtraneousProperties()

    // Still needed: has all properties, has extraneous properties, unexpected objects, inverseness
    
    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not throw an exception when properties is created by the appropriate
     * read-writable.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesComesFromRW() {
        ReadWritableFactory<T> factory = getTestFactory();
        T original = getReadWritableFromProperties(getTestProperties());
        Map<String, Object> properties = original.getReadWritableProperties();

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesComesFromRW()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} does
     * not return null when properties is created by the appropriate
     * read-writable.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesComesFromRW() {
        ReadWritableFactory<T> factory = getTestFactory();
        T original = getReadWritableFromProperties(getTestProperties());
        Map<String, Object> properties = original.getReadWritableProperties();

        T received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesComesFromRW()

    /**
     * Tests that {@link ReadWritableFactory#constructReadWritable(Map)} returns
     * the correct value when properties is created by the appropriate
     * read-writable.
     */
    @Test
    public void constructReadWritableMapReturnsCorrectValueWhenPropertiesComesFromRW() {
        ReadWritableFactory<T> factory = getTestFactory();
        T original = getReadWritableFromProperties(getTestProperties());
        Map<String, Object> properties = original.getReadWritableProperties();

        T received = factory.constructReadWritable(properties);

        T expected = original;
        assertEquals(expected, received);
    }    // constructReadWritableMapReturnsCorrectValueWhenPropertiesComesFromRW()
    
}    // ReadWritableFactoryTest
