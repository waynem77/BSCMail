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
     * Returns the names of the given roles, separated by commas.
     *
     * @param roles the list of roles; may not be null nor contain null
     * @return a string containing the names of the roles separated by commas
     */
    private String concatenateRoles(List<Role> roles) {
        String value = "";
        for (Role role : roles) {
            if (!value.isEmpty()) {
                value += ",";
            }    // if
            value += role.getName();
        }    // for
        return value;
    }    // concatenateRoles()

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
        volunteer = new Volunteer("foo", "bar", "baz", "smurf");
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
     * return null when properties is an empty map.
     */
    @Test
    public void testConstructReadWritablePropertiesEmptyDoesNotReturnNull() {
        System.out.println("constructReadWritable - properties empty, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }    // testConstructReadWritablePropertiesEmptyNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when description is missing.
     */
    @Test
    public void testConstructReadWritableDescriptionIsMissingNoException() {
        System.out.println("constructReadWritable - description is missing, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when description is missing.
     */
    @Test
    public void testConstructReadWritableDescriptionIsMissingDoesNotReturnNull() {
        System.out.println("constructReadWritable - description is missing, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }

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
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when description is null.
     */
    @Test
    public void testConstructReadWritableDescriptionIsNullDoesNotReturn() {
        System.out.println("constructReadWritable - description is null, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", null);
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when volunteer is missing.
     */
    @Test
    public void testConstructReadWritableVolunteerIsMissingNoException() {
        System.out.println("constructReadWritable - volunteer is missing, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when volunteer is missing.
     */
    @Test
    public void testConstructReadWritableVolunteerIsMissingDoesNotReturnNull() {
        System.out.println("constructReadWritable - volunteer is missing, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
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
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when volunteer is null.
     */
    @Test
    public void testConstructReadWritableVolunteerIsNullDoesNotReturnNull() {
        System.out.println("constructReadWritable - volunteer is null, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", null);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when roles is missing.
     */
    @Test
    public void testConstructReadWritableRolesIsMissingNoException() {
        System.out.println("constructReadWritable - roles is missing, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when roles is missing.
     */
    @Test
    public void testConstructReadWritableRolesIsMissingDoesNotReturnNull() {
        System.out.println("constructReadWritable - roles is missing, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when roles is null.
     */
    @Test
    public void testConstructReadWritableRolesIsNullNoException() {
        System.out.println("constructReadWritable - roles is null, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", null);
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when roles is null.
     */
    @Test
    public void testConstructReadWritableRolesIsNullDoesNotReturnNull() {
        System.out.println("constructReadWritable - roles is null, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", null);
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when roles is empty.
     */
    @Test
    public void testConstructReadWritableRolesIsEmptyNoException() {
        System.out.println("constructReadWritable - roles is empty, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", new LinkedList<Role>());
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when roles is empty.
     */
    @Test
    public void testConstructReadWritableRolesIsEmptyDoesNotReturnNull() {
        System.out.println("constructReadWritable - roles is empty, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", new LinkedList<Role>());
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when roles contains null.
     */
    @Test
    public void testConstructReadWritableRolesContainsNullNoException() {
        System.out.println("constructReadWritable - roles contains, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(null, new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when roles contains null.
     */
    @Test
    public void testConstructReadWritableRolesContainsNullDoesNotReturnNull() {
        System.out.println("constructReadWritable - roles contains, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(null, new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when displayVolunteerEmail is missing.
     */
    @Test
    public void testConstructReadWritabledisplayVolunteerEmailIsMissingNoException() {
        System.out.println("constructReadWritable - displayVolunteerEmail is missing, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when displayVolunteerEmail is missing.
     */
    @Test
    public void testConstructReadWritabledisplayVolunteerEmailIsMissingDoesNotReturnNull() {
        System.out.println("constructReadWritable - displayVolunteerEmail is missing, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when displayVolunteerPhone is missing.
     */
    @Test
    public void testConstructReadWritabledisplayVolunteerPhoneIsMissingNoException() {
        System.out.println("constructReadWritable - displayVolunteerPhone is missing, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerNotes", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when displayVolunteerPhone is missing.
     */
    @Test
    public void testConstructReadWritabledisplayVolunteerPhoneIsMissingDoesNotReturnNull() {
        System.out.println("constructReadWritable - displayVolunteerPhone is missing, does not return null");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerNotes", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when displayVolunteerNotes is missing.
     */
    @Test
    public void testConstructReadWritabledisplayVolunteerNotesIsMissingNoException() {
        System.out.println("constructReadWritable - displayVolunteerNotes is missing, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * return null when displayVolunteerNotes is missing.
     */
    @Test
    public void testConstructReadWritabledisplayVolunteerNotesIsMissingDoesNotReturnNull() {
        System.out.println("constructReadWritable - displayVolunteerNotes is missing, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);

        Shift received = factory.constructReadWritable(properties);

        assertNotNull(received);
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
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
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
        properties.put("roles", Arrays.asList(new Role("foo")));
        properties.put("displayVolunteerEmail", true);
        properties.put("displayVolunteerPhone", true);
        properties.put("displayVolunteerNotes", true);
        properties.put("foo", "bar");
        factory.constructReadWritable(properties);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} does not
     * throw an exception when the values are not the
     * correct objects.
     */
    @Test
    public void testConstructReadWritableDescriptionVolunteerWrongObjectsNoException() {
        System.out.println("constructReadWritable - description and volunteer values are not correct objects, no exception");

        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", 1);
        properties.put("volunteer", 2.0);
        properties.put("roles", false);
        properties.put("displayVolunteerEmail", "foo");
        properties.put("displayVolunteerPhone", new Volunteer("foo", "bar", "baz", "smurf"));
        properties.put("displayVolunteerNotes", Arrays.asList(new Role("foo")));
        factory.constructReadWritable(properties);
    }    // testConstructReadWritableDescriptionVolunteerWrongObjectsNoException()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value there is no volunteer.
     */
    @Test
    public void testConstructReadWritableNoVolunteer() {
        System.out.println("constructReadWritable - volunteer is null");

        List<Role> roles = Arrays.asList(new Role("foo"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("roles", concatenateRoles(roles));
        properties.put("displayVolunteerEmail", displayVolunteerEmail);
        properties.put("displayVolunteerPhone", displayVolunteerPhone);
        properties.put("displayVolunteerNotes", displayVolunteerNotes);
        
        Shift received = factory.constructReadWritable(properties);

        Shift expected = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        assertEquals(expected, received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value there are no roles.
     */
    @Test
    public void testConstructReadWritableNoRoles() {
        System.out.println("constructReadWritable - roles is null");

        List<Role> roles = new LinkedList<>();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("displayVolunteerEmail", displayVolunteerEmail);
        properties.put("displayVolunteerPhone", displayVolunteerPhone);
        properties.put("displayVolunteerNotes", displayVolunteerNotes);
        
        Shift received = factory.constructReadWritable(properties);

        Shift expected = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        expected.setVolunteer(volunteer);
        assertEquals(expected, received);
    }

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns the
     * correct value when properties has all the properties the factory needs.
     */
    @Test
    public void testConstructReadWritable() {
        System.out.println("constructReadWritable");

        List<Role> roles = Arrays.asList(new Role("foo"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", concatenateRoles(roles));
        properties.put("displayVolunteerEmail", displayVolunteerEmail);
        properties.put("displayVolunteerPhone", displayVolunteerPhone);
        properties.put("displayVolunteerNotes", displayVolunteerNotes);
        
        Shift received = factory.constructReadWritable(properties);
        
        Shift expected = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        expected.setVolunteer(volunteer);
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
        List<Role> roles = Arrays.asList(new Role("foo"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        factory = Shift.getShiftFactory();
        properties = new HashMap<>();
        properties.put("description", description);
        properties.put("volunteer", volunteer);
        properties.put("roles", concatenateRoles(roles));
        properties.put("displayVolunteerEmail", displayVolunteerEmail);
        properties.put("displayVolunteerPhone", displayVolunteerPhone);
        properties.put("displayVolunteerNotes", displayVolunteerNotes);
        properties.put("foo", "bar");

        Shift received = factory.constructReadWritable(properties);
        
        Shift expected = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        expected.setVolunteer(volunteer);
        assertEquals(expected, received);
    }    // testConstructReadWritablePropertiesExtraneous()

    /**
     * Tests that {@link Shift.Factory#constructReadWritable(Map)} returns
     * the correct value with properties constructed from
     * {@link Shift#getReadWritableProperties()}.
     */
    @Test
    public void testConstructReadWritableReflexive() {
        System.out.println("constructReadWritable - reflexivity");

        factory = Shift.getShiftFactory();
        List<Role> roles = Arrays.asList(new Role("foo"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(volunteer);
        properties = shift.getReadWritableProperties();

        Shift expected = shift;
        Shift received = factory.constructReadWritable(properties);
        assertEquals(expected, received);
    }

}
