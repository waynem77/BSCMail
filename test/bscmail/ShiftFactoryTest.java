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
 * Unit tests for {@link Shift.Factory}.
 *
 * @author Wayne Miller
 */
public class ShiftFactoryTest {

    /**
     * Variable used to hold the shift description used in testing.
     */
    private String description;

    /**
     * Variable used to hold the volunteer used in testing.
     */
    private Volunteer volunteer;
    
    /**
     * Variable used to hold the read-writable properties used in testing.
     */
    private Map<String, Object> properties;

    /**
     * Variable used to hold the read-writable factory being tested.
     */
    private Shift.Factory factory;

    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Shift.ShiftFactory");
        System.out.println("==================");
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
        description = "Foo";
        volunteer = new Volunteer("Foo Bar", "foo@bar");
        properties = null;
        factory = null;
    }    // beforeTest()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void afterTest() {
        description = null;
        volunteer = null;
        properties = null;
        factory = null;
    }    // afterTest()

    /*
     * Unit tests
     */
    
    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} throws a
     * {@link NullPointerException} when properties is null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructReadWritablePropertiesNull() {
        System.out.println("constructReadWritable - properties is null");

        factory = Shift.getShiftFactory();
        properties = null;
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesNull()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties is an empty map.
     */
    @Test
    public void testConstructReadWritablePropertiesEmptyNoException() {
        System.out.println("constructReadWritable - properties empty, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        factory.constructReadWritable(properties);
    }    // testConstructReadWritablePropertiesEmptyNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has only the description.
     */
    @Test
    public void testConstructReadWritableDescriptionOnlyNoException() {
        System.out.println("constructReadWritable - properties has description only, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableDescriptionOnlyNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when description is null.
     */
    @Test
    public void testConstructReadWritableDescriptionIsNullNoException() {
        System.out.println("constructReadWritable - description is null, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", null);
        properties.put("volunteer", volunteer);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when volunteer is null.
     */
    @Test
    public void testConstructReadWritableVolunteerIsNullNoException() {
        System.out.println("constructReadWritable - volunteer is null, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", null);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has all the properties the factory
     * needs.
     */
    @Test
    public void testConstructReadWritableNoException() {
        System.out.println("constructReadWritable - no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when properties has all the properties the factory
     * needs plus extraneous properties.
     */
    @Test
    public void testConstructReadWritablePropertiesExtraneousNoException() {
        System.out.println("constructReadWritable - properties has extraneous properties, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("foo", "bar");
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when the description and volunteer values are not the
     * correct objects.
     */
    @Test
    public void testConstructReadWritableDescriptionVolunteerWrongObjectsNoException() {
        System.out.println("constructReadWritable - description and volunteer values are not correct objects, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", new Volunteer("description", ""));
        properties.put("volunteer", "foo");
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableDescriptionVolunteerWrongObjectsNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns null
     * when properties is an empty map.
     */
    @Test
    public void testConstructReadWritablePropertiesEmpty() {
        System.out.println("constructReadWritable - properties empty");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        Shift expected = null;
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns null
     * when properties has only the description.
     */
    @Test
    public void testConstructReadWritableDescriptionOnly() {
        System.out.println("constructReadWritable - properties has description only");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        Shift expected = null;
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when description is null.
     */
    @Test
    public void testConstructReadWritableDescriptionIsNull() {
        System.out.println("constructReadWritable - description is null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", null);
        properties.put("volunteer", volunteer);
        
        Shift expected = new Shift("");
        expected.setVolunteer(volunteer);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritableDescriptionIsNull()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when volunteer is null.
     */
    @Test
    public void testConstructReadWritableVolunteerIsNull() {
        System.out.println("constructReadWritable - volunteer is null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", null);
        
        Shift expected = new Shift(description);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when properties has all the properties the factory needs.
     */
    @Test
    public void testConstructReadWritable() {
        System.out.println("constructReadWritable");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        
        Shift expected = new Shift(description);
        expected.setVolunteer(volunteer);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritable()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when properties has all the properties the factory needs
     * plus extraneous properties.
     */
    @Test
    public void testConstructReadWritablePropertiesExtraneous() {
        System.out.println("constructReadWritable - properties has extraneous properties");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("foo", "bar");
        
        Shift expected = new Shift(description);
        expected.setVolunteer(volunteer);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesExtraneous()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when the description and volunteer values are not the
     * correct objects.
     */
    @Test
    public void testConstructReadWritableDescriptionVolunteerWrongObjects() {
        System.out.println("constructReadWritable - description and volunteer values are not correct objects");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", new Volunteer(description, "", false));
        properties.put("volunteer", "foo");
        
        Shift expected = new Shift(description);
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns
     * the correct value with properties constructed from
     * {@link Shift#getReadWritableProperties()}.
     */
    @Test
    public void testConstructReadWritableReflexive() {
        System.out.println("constructReadWritable - reflexivity");

        factory = Shift.getShiftFactory();
        Shift shift = new Shift(description);
        shift.setVolunteer(volunteer);
        properties = shift.getReadWritableProperties();

        Shift expected = shift;
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }

}
