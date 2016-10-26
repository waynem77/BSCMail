/*
 * Copyright © 2014-2016 its authors.  See the file "AUTHORS" for details.
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

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Volunteer.Factory}.
 *
 * @author Wayne Miller
 */
public class VolunteerFactoryTest {

    /**
     * Variable used to hold the volunteer name used in testing.
     */
    private String name;

    /**
     * Variable used to hold the volunteer email address used in testing.
     */
    private String email;

    /**
     * Variable used to hold the volunteer phone number used in testing.
     */
    private String phone;

    /**
     * Variable used to hold the volunteer notes used in testing.
     */
    private String notes;

    /**
     * Variable used to hold the volunteer roles used in testing.
     */
    private String roles;

    /**
     * Variable used to hold the read-writable properties used in testing.
     */
    private Map<String, Object> properties;

    /**
     * Variable used to hold the read-writable factory being tested.
     */
    private Volunteer.Factory factory;

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Volunteer.VolunteerFactory");
        System.out.println("==========================");
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
        email = "foo@bar";
        phone = "555-FOO";
        notes = "baz";
        roles = "clark,kent";
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
        notes = null;
        roles = null;
        properties = null;
        factory = null;
    }    // afterTest()

    /*
     * Unit tests
     */
    
    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} throws a
     * {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructReadWritablePropertiesNull() {
        System.out.println("constructReadWritable - properties is null");

        factory = Volunteer.getVolunteerFactory();
        properties = null;
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesNull()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties is an empty map.
     */
    @Test
    public void testConstructReadWritablePropertiesEmptyNoException() {
        System.out.println("constructReadWritable - properties empty, no exception");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesEmptyNoException()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has some but not all of the required
     * properties.
     */
    @Test
    public void testConstructReadWritableNameEmailOnlyNoException() {
        System.out.println("constructReadWritable - properties has name and email only, no exception");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableNameEmailOnlyNoException()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties contains a null value.
     */
    @Test
    public void testConstructReadWritablePropertiesHasNullNoException() {
        System.out.println("constructReadWritable - properties contains null, no exception");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", null);
        properties.put("phone", phone);
        properties.put("notes", notes);
        properties.put("roles", roles);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesHasNullNoException()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has all the properties the factory
     * needs.
     */
    @Test
    public void testConstructReadWritableNoException() {
        System.out.println("constructReadWritable - no exception");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);
        properties.put("notes", notes);
        properties.put("roles", roles);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableNoException()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has all the properties the factory
     * needs plus extraneous properties.
     */
    @Test
    public void testConstructReadWritablePropertiesExtraneousNoException() {
        System.out.println("constructReadWritable - properties has extraneous properties, no exception");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);
        properties.put("notes", notes);
        properties.put("roles", roles);
        properties.put("foo", "bar");
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesExtraneousNoException()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} does not
     * throw an exception when the name and email values are not strings.
     */
    @Test
    public void testConstructReadWritableNameEmailWrongObjectsNoException() {
        System.out.println("constructReadWritable - name and email values are not strings, no exception");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", new Volunteer(name, email, phone, notes));
        properties.put("email", 1);
        properties.put("phone", 2.0);
        properties.put("notes", new ArrayList());
        properties.put("roles", false);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableNameEmailWrongObjectsNoException()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} returns
     * null when properties is an empty map.
     */
    @Test
    public void testConstructReadWritablePropertiesEmpty() {
        System.out.println("constructReadWritable - properties empty");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();

        Volunteer expected = null;
        Volunteer received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesEmpty()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} returns
     * null when properties has some but not all of the required properties.
     * */
    @Test
    public void testConstructReadWritableNameEmailOnly() {
        System.out.println("constructReadWritable - properties has name and email only");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", email);

        Volunteer expected = null;
        Volunteer received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableNameEmailOnly()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} returns
     * the correct value when properties contains a null value.
     */
    @Test
    public void testConstructReadWritablePropertiesHasNull() {
        System.out.println("constructReadWritable - properties contains null");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", null);
        properties.put("phone", phone);
        properties.put("notes", notes);
        properties.put("roles", roles);

        Volunteer expected = new Volunteer(name, "", phone, notes);
        for (String roleName : roles.split(",")) {
            expected.addRole(new Role(roleName));
        }    // for
        Volunteer received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesHasNull()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} returns
     * the correct value when properties has all the properties the factory
     * needs.
     */
    @Test
    public void testConstructReadWritable() {
        System.out.println("constructReadWritable");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);
        properties.put("notes", notes);
        properties.put("roles", roles);

        Volunteer expected = new Volunteer(name, "", phone, notes);
        for (String roleName : roles.split(",")) {
            expected.addRole(new Role(roleName));
        }    // for
        Volunteer received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritable()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} returns
     * the correct value when properties has all the properties the factory
     * needs plus extraneous properties.
     */
    @Test
    public void testConstructReadWritablePropertiesExtraneous() {
        System.out.println("constructReadWritable - properties has extraneous properties");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);
        properties.put("notes", notes);
        properties.put("roles", roles);
        properties.put("foo", "bar");

        Volunteer expected = new Volunteer(name, "", phone, notes);
        for (String roleName : roles.split(",")) {
            expected.addRole(new Role(roleName));
        }    // for
        Volunteer received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesExtraneous()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} returns
     * the correct value when the name and email values are not strings.
     */
    @Test
    public void testConstructReadWritableNameEmailWrongObjects() {
        System.out.println("constructReadWritable - name and email values are not strings");

        factory = Volunteer.getVolunteerFactory();
        properties = new HashMap<>();
        properties.put("name", new Volunteer(name, email, phone, notes));
        properties.put("email", 1);
        properties.put("phone", 2.0);
        properties.put("notes", new ArrayList());
        properties.put("roles", false);

        Volunteer expected = new Volunteer(name, "1", "2.0", "");
        expected.addRole(new Role("false"));
        Volunteer received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableNameEmailWrongObjects()

    /**
     * Tests that {@link Volunteer.Factory#constructReadWritable(Map)} returns
     * the correct value with properties constructed from
     * {@link Volunteer#getReadWritableProperties()}.
     */
    @Test
    public void testConstructReadWritableReflexive() {
        System.out.println("constructReadWritable - reflexivity");

        properties.put("name", name);
        properties.put("email", email);
        properties.put("phone", phone);
        properties.put("notes", notes);
        properties.put("roles", roles);
        properties.put("foo", "bar");

        Volunteer volunteer = new Volunteer(name, "", phone, notes);
        for (String roleName : roles.split(",")) {
            volunteer.addRole(new Role(roleName));
        }    // for
        properties = volunteer.getReadWritableProperties();

        Volunteer expected = volunteer;
        Volunteer received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableReflexive()

}    // VolunteerFactoryTest
