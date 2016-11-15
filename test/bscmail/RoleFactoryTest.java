/*
 * Copyright © 2016 its authors.  See the file "AUTHORS" for details.
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

package bscmail;

import bscmail.Role.Factory;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Role.Factory}.
 *
 * @author Wayne Miller
 */
public class RoleFactoryTest {

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)}
     * throws a {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructReadWritableThrowsExceptionWhenPropertiesIsNull() {
        Factory factory = Role.getRoleFactory();
        Map<String, Object> properties = null;

        factory.constructReadWritable(properties);
    }    // constructReadWritableThrowsExceptionWhenPropertiesIsNull()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty() {
        Factory factory = Role.getRoleFactory();
        Map<String, Object> properties = new HashMap<>();

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesIsEmpty()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not return null when properties is an empty map.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty() {
        Factory factory = Role.getRoleFactory();
        Map<String, Object> properties = new HashMap<>();

        Role received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesIsEmpty()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has a null value for "name".
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullName() {
        Factory factory = Role.getRoleFactory();
        String name = null;
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasNullName()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not return null when properties has a null value for "name".
     */
    @Test
    public void constructReadWritableDoesNotReturnNullhenPropertiesHasNullName() {
        Factory factory = Role.getRoleFactory();
        String name = null;
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);

        Role received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullhenPropertiesHasNullName()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties all required values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues() {
        Factory factory = Role.getRoleFactory();
        String name = "foo";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasAllValues()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not return null when properties all required values.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasAllValues() {
        Factory factory = Role.getRoleFactory();
        String name = "foo";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);

        Role received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasAllValues()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)}
     * returns the correct value when properties all required values.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasAllValues() {
        Factory factory = Role.getRoleFactory();
        String name = "foo";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);

        Role received = factory.constructReadWritable(properties);

        Role expected = new Role(name);
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasAllValues()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not throw an exception when properties has extraneous values.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousValues() {
        Factory factory = Role.getRoleFactory();
        String name = "foo";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("extra", "baz");

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not return null when properties has extraneous values.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousValues() {
        Factory factory = Role.getRoleFactory();
        String name = "foo";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("extra", "baz");

        Role received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)}
     * returns the correct value when properties has extraneous values.
     */
    @Test
    public void constructReadWritableReturnsCorrectValueWhenPropertiesHasExtraneousValues() {
        Factory factory = Role.getRoleFactory();
        String name = "foo";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("extra", "baz");

        Role received = factory.constructReadWritable(properties);

        Role expected = new Role(name);
        assertEquals(expected, received);
    }    // constructReadWritableReturnsCorrectValueWhenPropertiesHasExtraneousValues()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not throw an exception when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects() {
        Factory factory = Role.getRoleFactory();
        Object name = 1;
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);

        factory.constructReadWritable(properties);
    }    // constructReadWritableDoesNotThrowExceptionWhenPropertiesHasWrongObjects()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} does
     * not returns null when the values are the wrong objects.
     */
    @Test
    public void constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects() {
        Factory factory = Role.getRoleFactory();
        Object name = 1;
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);

        Role received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

    /**
     * Tests that {@link Role.Factory#constructReadWritable(Map)} acts
     * as the reverse of {@link Role#getReadWritableProperties()}.
     */
    @Test
    public void constructReadWritableWorksReflexively() {
        String name = "foo";
        Role originalRole = new Role(name);

        Map<String, Object> properties = originalRole.getReadWritableProperties();
        Factory factory = Role.getRoleFactory();
        Role received = factory.constructReadWritable(properties);

        Role expected = originalRole;
        assertEquals(expected, received);
    }    // constructReadWritableDoesNotReturnNullWhenPropertiesHasWrongObjects()

}    // RoleFactoryTest
