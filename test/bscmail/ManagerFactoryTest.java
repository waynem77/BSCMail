/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package bscmail;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Manager.Factory}.
 *
 * @author Wayne Miller
 */
public class ManagerFactoryTest {

    /**
     * Variable used to hold the manager name used in testing.
     */
    private String name;

    /**
     * Variable used to hold the manager email address used in testing.
     */
    private String email;

    /**
     * Variable used to hold the manager phone number used in testing.
     */
    private String phone;

    /**
     * Variable used to hold the read-writable properties used in testing.
     */
    private Map<String, Object> properties;

    /**
     * Variable used to hold the read-writable factory being tested.
     */
    private Manager.Factory factory;

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Manager.ManagerFactory");
        System.out.println("======================");
    }    // setUpClass()

    /**
     * Prints unit test footer.
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println();
    }    // tearDownClass(

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void beforeTest() {
        name = "Foo Bar";
        email = "foo@bar.baz";
        phone = "555-FOO-BARR";
        properties = null;
        factory = null;
    }    // beforeTest()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void afterTest() {
        name = null;
        email = null;
        phone = null;
        properties = null;
        factory = null;
    }    // afterTest()

    /*
     * Unit tests
     */
    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)}} throws a
     * {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructReadWritablePropertiesNull() {
        System.out.println("constructReadWritable - properties is null");

        factory = Manager.getManagerFactory();
        properties = null;
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesNull()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties is an empty map.
     */
    @Test
    public void testConstructReadWritablePropertiesEmptyNoException() {
        System.out.println("constructReadWritable - properties empty, no exception");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesEmptyNoException()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties only has extraneous properties.
     */
    @Test
    public void testConstructReadWritablePropertiesOnlyExtraneousNoException() {
        System.out.println("constructReadWritable - properties only has extraneous properties, no exception");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("foo", "bar");
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesOnlyExtraneousNoException()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has only some of the properties the
     * factory needs.
     */
    @Test
    public void testConstructReadWritablePropertiesPartialNoException() {
        System.out.println("constructReadWritable - properties partially filled, no exception");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesPartialNoException()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties contains a null value.
     */
    @Test
    public void testConstructReadWritablePropertiesHasNullNoException() {
        System.out.println("constructReadWritable - properties contains null, no exception");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", null);
        properties.put("phone", phone);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesHasNullNoException()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has all the properties the factory
     * needs.
     */
    @Test
    public void testConstructReadWritableNoException() {
        System.out.println("constructReadWritable - no exception");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableNoException()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} does not
     * throw an exception when the property values are not strings.
     */
    @Test
    public void testConstructReadWritablePropertiesWrongObjectsNoException() {
        System.out.println("constructReadWritable - properties values are not strings, no exception");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("name", new Manager(name, email, phone));
        properties.put("email", 1);
        properties.put("phone", true);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesWrongObjectsNoException()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} returns the
     * correct value when properties is an empty map.
     */
    @Test
    public void testConstructReadWritablePropertiesEmpty() {
        System.out.println("constructReadWritable - properties empty");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();

        Manager expected = new Manager("", "", "");
        Manager received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesEmpty()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} returns the
     * correct value when properties only has extraneous properties.
     */
    @Test
    public void testConstructReadWritablePropertiesOnlyExtraneous() {
        System.out.println("constructReadWritable - properties only has extraneous properties");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("foo", "bar");

        Manager expected = new Manager("", "", "");
        Manager received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesOnlyExtraneous()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} returns the
     * correct value when properties has only some of the properties the factory
     * needs.
     */
    @Test
    public void testConstructReadWritablePropertiesPartial() {
        System.out.println("constructReadWritable - properties partially filled");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("name", name);

        Manager expected = new Manager(name, "", "");
        Manager received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesPartial()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} returns the
     * correct value when properties contains a null value.
     */
    @Test
    public void testConstructReadWritablePropertiesHasNull() {
        System.out.println("constructReadWritable - properties contains null");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", null);
        properties.put("phone", phone);

        Manager expected = new Manager(name, "", phone);
        Manager received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesHasNull()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} returns the
     * correct value when properties has all the properties the factory needs.
     */
    @Test
    public void testConstructReadWritable() {
        System.out.println("constructReadWritable");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);

        Manager expected = new Manager(name, email, phone);
        Manager received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritable()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} returns the
     * correct value when when the property values are not strings.
     */
    @Test
    public void testConstructReadWritablePropertiesWrongObjects() {
        System.out.println("constructReadWritable - properties values are not strings");

        factory = Manager.getManagerFactory();
        properties = new HashMap<>();
        properties.put("name", new Manager(name, email, phone));
        properties.put("email", 1);
        properties.put("phone", true);

        Manager expected = new Manager(name, "1", "true");
        Manager received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesWrongObjects()

    /**
     * Tests that {@link Manager.Factory#constructReadWritable(Map)} returns the
     * correct value with properties constructed from
     * {@link Manager#getReadWritableProperties()}.
     */
    @Test
    public void testConstructReadWritableReflexive() {
        System.out.println("constructReadWritable - reflexivity");

        factory = Manager.getManagerFactory();
        Manager manager = new Manager(name, email, phone);
        properties = manager.getReadWritableProperties();

        Manager expected = manager;
        Manager received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableReflexive()

}    // ManagerFactoryTest
