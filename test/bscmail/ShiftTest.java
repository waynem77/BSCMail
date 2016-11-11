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
import main.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Shift}
 * @author Wayne Miller
 */
public class ShiftTest extends ReadWritableTest {

    /**
     * Variable used to hold the description used in testing.
     */
    private String description;

    /**
     * Variable used to hold the volunteer used in testing.
     */
    private Volunteer volunteer;

    /**
     * Variable used to hold the shift being tested.
     */
    private Shift shift;
    
    /**
     * Returns a valid volunteer
     */
    private Volunteer getVolunteer() {
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        return new Volunteer(name, email, phone, notes);
    }    // getVolunteer()

    /**
     * Returns the shift to be tested.
     * 
     * @return the shift to be tested
     */
    @Override
    protected Shift getReadWritable() {
        String description = "foo";
        List<Role> roles = new LinkedList<>();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        shift.setVolunteer(getVolunteer());
        return shift;
    }    // getReadWritable()
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Shift");
        System.out.println("=====");
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
    public void setUp() {
        description = "Door 10:15–11:15";
        volunteer = null;
        shift = null;
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        description = null;
        volunteer = null;
        shift = null;
    }    // tearDown()

    /* constructor */

    /**
     * Tests that
     * {@link Shift#Shift(java.lang.String, java.util.List, boolean, boolean, boolean)}
     * throws a {@link NullPointerException} when description is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenDescriptionIsNull() {
        description = null;
        List<Role> roles = new LinkedList<>();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;

        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
    }    // constructorThrowsExceptionWhenDescriptionIsNull()

    /**
     * Tests that
     * {@link Shift#Shift(java.lang.String, java.util.List, boolean, boolean, boolean)}
     * throws a {@link NullPointerException} when roles is null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenRolesIsNull() {
        description = "foo";
        List<Role> roles = null;
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;

        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
    }    // constructorThrowsExceptionWhenRolesIsNull()

    /**
     * Tests that
     * {@link Shift#Shift(java.lang.String, java.util.List, boolean, boolean, boolean)}
     * does not throw an exception when no parameter is null nor contains null.
     */
    @Test
    public void constructorDoesNotThrowsExceptionNormally() {
        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;

        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
    }    // constructorDoesNotThrowsExceptionNormally()

    /**
     * Tests that
     * {@link Shift#Shift(java.lang.String, java.util.List, boolean, boolean, boolean)}
     * throws a {@link NullPointerException} when roles contains null.
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsExceptionWhenRolesContainsNull() {
        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), null, new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;

        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
    }    // constructorThrowsExceptionWhenRolesContainsNull()

    /* getDescription */
    
    /**
     * Tests that {@link Shift#getDescription()} does not throw an exception.
     */
    @Test
    public void testGetDescriptionNoException() {
        System.out.println("getDescription - no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.getDescription();
    }    // testGetDescriptionNoException()
    
    /**
     * Tests that {@link Shift#getDescription()} returns the correct value.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        String expected = description;
        String received = shift.getDescription();
        assertEquals(expected, received);
    }    // testGetDescription()

    /* isOpen */
    
    /**
     * Tests that {@link Shift#isOpen()} returns true when called before
     * {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testIsOpenBeforeSetVolunteer() {
        System.out.println("isOpen - before setVolunteer");
        
        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean received = shift.isOpen();
        assertTrue(received);
    }    // testIsOpenBeforeSetVolunteer()
    
    /**
     * Tests that {@link Shift#isOpen()} returns false when called after
     * {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testIsOpenAfterSetVolunteer() {
        System.out.println("isOpen - after setVolunteer");
        
        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.setVolunteer(getVolunteer());
        boolean received = shift.isOpen();
        assertFalse(received);
    }    // testIsOpenAfterSetVolunteer()
    
    /**
     * Tests that {@link Shift#ifOpen()} returns true when called after
     * {@link Shift#unsetVolunteer()} is called with null.
     */
    @Test
    public void testIsOpenAfterUnsetVolunteer() {
        System.out.println("isOpen - after unsetVolunteer");
        
        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.setVolunteer(getVolunteer());
        volunteer = null;
        shift.setVolunteer(volunteer);
        boolean received = shift.isOpen();
        assertTrue(received);
    }    // testIsOpenAfterUnsetVolunteer()

    /* getVolunteer / setVolunteer */

    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception when
     * called before {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testGetVolunteerBeforeSetVolunteerNoException() {
        System.out.println("getVolunteer - before setVolunteer, no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.getVolunteer();
    }    // testGetVolunteerBeforeSetVolunteerNoException()

    /**
     * Tests that {@link Shift#getVolunteer()} returns null when called before
     * {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testGetVolunteerBeforeSetVolunteer() {
        System.out.println("getVolunteer - before setVolunteer");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Volunteer expected = null;
        Volunteer received = shift.getVolunteer();
        assertEquals(expected, received);
    }    // testGetVolunteerBeforeSetVolunteer()

    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception
     * when called after {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testGetVolunteerAfterSetVolunteerNoException() {
        System.out.println("getVolunteer - after setVolunteer, no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.setVolunteer(getVolunteer());
        shift.getVolunteer();
    }

    /**
     * Tests that {@link Shift#getVolunteer()} returns the correct value when
     * called after {@link Shift#setVolunteer(Volunteer)}.
     */
    @Test
    public void testGetVolunteerAfterSetVolunteer() {
        System.out.println("getVolunteer - after setVolunteer");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        volunteer = getVolunteer();
        shift.setVolunteer(volunteer);
        Volunteer expected = volunteer;
        Volunteer received = shift.getVolunteer();
        assertEquals(expected, received);
    }    // testGetVolunteerAfterSetVolunteer()

    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception when
     * called after {@link Shift#unsetVolunteer()} called with null.
     */
    @Test
    public void testGetVolunteerAfterSetVolunteerNullNoException() {
        System.out.println("getVolunteer - after setVolunteer(null), no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.setVolunteer(getVolunteer());
        volunteer = null;
        shift.setVolunteer(volunteer);
        shift.getVolunteer();
    }

    /**
     * Tests that {@link Shift#getVolunteer()} does not throw an exception when
     * called after {@link Shift#unsetVolunteer()} called with null.
     */
    @Test
    public void testGetVolunteerAfterSetVolunteerNull() {
        System.out.println("getVolunteer - after setVolunteer(null)");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.setVolunteer(getVolunteer());
        volunteer = null;
        shift.setVolunteer(volunteer);
        Volunteer expected = volunteer;
        Volunteer received = shift.getVolunteer();
        assertEquals(expected, received);
    }    // testGetVolunteerAfterSetVolunteerNull()

    /**
     * Tests that {@link Shift#setVolunteer(Volunteer)} does not throw an
     * exception when volunteer is null.
     */
    @Test
    public void testSetVolunteerVolunteerNull() {
        System.out.println("setVolunteer - volunteer is null");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        volunteer = null;
        shift.setVolunteer(volunteer);
    }    // testSetVolunteerVolunteerNull()

    /* getReadWritableProperties */

    /**
     * Tests that {@link Shift#getReadWritableProperties()} returns the
     * correct value for a shift with a volunteer.
     */
    @Test
    public void testGetReadWritablePropertiesVolunteer() {
        System.out.println("getReadWritableProperties - shift has volunteer");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        volunteer = getVolunteer();
        shift.setVolunteer(volunteer);
        Map<String, Object> expected = new HashMap<>();
        expected.put("description", description);
        expected.put("roles", "foo,bar");
        expected.put("displayVolunteerEmail", displayVolunteerEmail);
        expected.put("displayVolunteerPhone", displayVolunteerPhone);
        expected.put("displayVolunteerNotes", displayVolunteerNotes);
        expected.put("volunteer", volunteer);
        Map<String, Object> received = shift.getReadWritableProperties();
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesVolunteer()

    /**
     * Tests that {@link Shift#getReadWritableProperties()} returns the
     * correct value for a shift with no volunteer.
     */
    @Test
    public void testGetReadWritablePropertiesNoVolunteer() {
        System.out.println("getReadWritableProperties - shift does not have a volunteer");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Map<String, Object> expected = new HashMap<>();
        expected.put("description", description);
        expected.put("roles", "foo,bar");
        expected.put("displayVolunteerEmail", displayVolunteerEmail);
        expected.put("displayVolunteerPhone", displayVolunteerPhone);
        expected.put("displayVolunteerNotes", displayVolunteerNotes);
        Map<String, Object> received = shift.getReadWritableProperties();
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesNoVolunteer()

    /**
     * Tests that the return value of {@link Shift#getReadWritableProperties()}
     * has the correct iteration order for a shift with a volunteer.
     */
    @Test
    public void testGetReadWritablePropertiesIterationOrderVolunteer() {
        System.out.println("getReadWritableProperties - iteration order, shift has volunteer");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.setVolunteer(getVolunteer());
        Map<String, Object> properties = shift.getReadWritableProperties();
        List<String> expected = Arrays.asList("description", "roles", "displayVolunteerEmail", "displayVolunteerPhone", "displayVolunteerNotes", "volunteer");
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesIterationOrderVolunteer()

    /**
     * Tests that the return value of {@link Shift#getReadWritableProperties()}
     * has the correct iteration order for a shift with no volunteer.
     */
    @Test
    public void testGetReadWritablePropertiesIterationOrderNoVolunteer() {
        System.out.println("getReadWritableProperties - iteration order, shift does not have a volunteer");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Map<String, Object> properties = shift.getReadWritableProperties();
        List<String> expected = Arrays.asList("description", "roles", "displayVolunteerEmail", "displayVolunteerPhone", "displayVolunteerNotes");
        List<String> received = new LinkedList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            received.add(entry.getKey());
        }    // for
        assertEquals(expected, received);
    }    // testGetReadWritablePropertiesIterationOrderNoVolunteer()

    /* equals */

    /**
     * Tests that {@link Shift#equals(Object)} does not throw an exception
     * when the argument is null.
     */
    @Test
    public void testEqualsNullNoException() {
        System.out.println("equals - null, no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Object obj = null;
        shift.equals(obj);
    }    // testEqualsNullNoException()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument is null.
     */
    @Test
    public void testEqualsNull() {
        System.out.println("equals - null");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Object obj = null;
        boolean received = shift.equals(obj);
        assertFalse(received);
    }    // testEqualsNull()

    /**
     * Tests that {@link Shift#equals(Object)} does not throw an exception
     * when the argument is not a shift.
     */
    @Test
    public void testEqualsNotAShiftNoException() {
        System.out.println("equals - not a shift, no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Object obj = 1;
        shift.equals(obj);
    }    // testEqualsNotAShiftNoException()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument is not a shift.
     */
    @Test
    public void testEqualsNotAShift() {
        System.out.println("equals - not a shift");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Object obj = 1;
        boolean received = shift.equals(obj);
        assertFalse(received);
    }    // testEqualsNotAShift()

    /**
     * Tests that {@link Shift#equals(Object)} does not throw an exception
     * when the argument is a shift.
     */
    @Test
    public void testEqualsShiftNoException() {
        System.out.println("equals - shift, no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.equals(obj);
    }    // testEqualsShiftNoException()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * argument has a different description than the caller.
     */
    @Test
    public void testEqualsDifferentDescription() {
        System.out.println("equals - shift, different description");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        description = "Bar";
        Object obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean expected = false;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentDescription()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * caller has no volunteer assigned and the argument has a volunteer
     * assigned.
     */
    @Test
    public void testEqualsNoVolunteerArgHasVolunteer() {
        System.out.println("equals - no volunteer, argument has volunteer");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Shift obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        volunteer = getVolunteer();
        obj.setVolunteer(volunteer);

        boolean expected = false;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsNoVolunteerArgHasVolunteer()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * caller has a volunteer assigned and the argument has no volunteer
     * assigned.
     */
    @Test
    public void testEqualsVolunteerArgHasNoVolunteer() {
        System.out.println("equals - volunteer, argument has no volunteer");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        volunteer = getVolunteer();
        shift.setVolunteer(volunteer);
        Shift obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean expected = false;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsVolunteerArgHasNoVolunteer()

    /**
     * Tests that {@link Shift#equals(Object)} returns false when the
     * caller has a different volunteer assigned to it than the argument.
     */
    @Test
    public void testEqualsDifferentVolunteers() {
        System.out.println("equals - different volunteers");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        volunteer = new Volunteer(name, email, phone, notes);
        shift.setVolunteer(volunteer);
        Shift obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        name += "X";
        volunteer = new Volunteer(name, email, phone, notes);
        obj.setVolunteer(volunteer);

        boolean expected = false;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsDifferentVolunteers()

    /**
     * Tests that {@link Shift#equals(Object)} returns true when neither the
     * caller nor the argument have volunteers assigned to them.
     */
    @Test
    public void testEqualsNoVolunteers() {
        System.out.println("equals - no volunteers");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        boolean expected = true;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsNoVolunteers()

    /**
     * Tests that {@link Shift#equals(Object)} returns true when the
     * caller has the same volunteer assigned to it as the argument.
     */
    @Test
    public void testEqualsSameVolunteer() {
        System.out.println("equals - same volunteers");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        volunteer = new Volunteer(name, email, phone, notes);
        shift.setVolunteer(volunteer);
        Shift obj = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        volunteer = new Volunteer(name, email, phone, notes);
        obj.setVolunteer(volunteer);

        boolean expected = true;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsSameVolunteer()

    /**
     * Tests that {@link Shift#equals(Object)} returns true when the
     * argument is the same object as the caller.
     */
    @Test
    public void testEqualsIdentical() {
        System.out.println("equals - shift, identical");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Object obj = shift;

        boolean expected = true;
        boolean received = shift.equals(obj);
        assertEquals(expected, received);
    }    // testEqualsIdentical()

    /**
     * Tests that {@link Shift#hashCode()} does not throw an exception.
     */
    @Test
    public void testHashCodeNoException() {
        System.out.println("hashCode - no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.hashCode();
    }    // testHashCodeNoException()

    /* hashCode */

    /**
     * Tests that {@link Shift#hashCode()} returns equal values for equal
     * shifts without volunteers.
     */
    @Test
    public void testHashCodeEqualNoVolunteers() {
        System.out.println("hashCode - equal shifts without volunteers");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        Shift experimental  = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        int expected = shift.hashCode();
        int received = experimental.hashCode();
        assertEquals(expected, received);
    }    // testHashCodeEqualNoVolunteers()

    /**
     * Tests that {@link Shift#hashCode()} returns equal values for equal
     * shifts with volunteers.
     */
    @Test
    public void testHashCodeEqualWithVolunteers() {
        System.out.println("hashCode - equal shifts with volunteers");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        volunteer = new Volunteer(name, email, phone, notes);
        shift.setVolunteer(volunteer);
        Shift experimental = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        volunteer = new Volunteer(name, email, phone, notes);
        experimental.setVolunteer(volunteer);

        int expected = shift.hashCode();
        int received = experimental.hashCode();
        assertEquals(expected, received);
    }    // testHashCodeEqualWithVolunteers()

    /* clone */

    /**
     * Tests that {@link Shift#clone()} does not throw an exception when the
     * shift does not have a volunteer.
     */
    @Test
    public void testCloneNoVolunteerNoException() {
        System.out.println("clone - no volunteer, no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.clone();
    }    // testCloneNoVolunteerNoException()

    /**
     * Tests that {@link Shift#clone()} does not throw an exception when the
     * shift has a volunteer.
     */
    @Test
    public void testCloneVolunteerNoException() {
        System.out.println("clone - volunteer, no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        volunteer = new Volunteer(name, email, phone, notes);
        shift.setVolunteer(volunteer);

        shift.clone();
    }    // testCloneVolunteerNoException()

    /**
     * Tests that the return value of {@link Shift#clone()} is not null when the
     * shift does not have a volunteer.
     */
    @Test
    public void testCloneNoVolunteerNotNull() {
        System.out.println("clone - no volunteer, not null");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Shift received = shift.clone();

        assertNotNull(received);
    }    // testCloneNoVolunteerNotNull()

    /**
     * Tests that the return value of {@link Shift#clone()} is not null when the
     * shift has a volunteer.
     */
    @Test
    public void testCloneVolunteerNotNull() {
        System.out.println("clone - volunteer, not null");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        volunteer = new Volunteer(name, email, phone, notes);
        shift.setVolunteer(volunteer);

        Shift received = shift.clone();

        assertNotNull(received);
    }    // testCloneVolunteerNotNull()

    /**
     * Tests that the return value of {@link Shift#clone()} is equal to the
     * argument when the shift does not have a volunteer.
     */
    @Test
    public void testCloneNoVolunteerEqual() {
        System.out.println("clone - no volunteer, equal");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Shift received = shift.clone();

        Shift expected = shift;
        assertEquals(expected, received);
    }    // testCloneNoVolunteerNoException()

    /**
     * Tests that the return value of {@link Shift#clone()} is equal to the
     * argument when the shift has a volunteer.
     */
    @Test
    public void testCloneVolunteerEqual() {
        System.out.println("clone - volunteer, equal");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        volunteer = new Volunteer(name, email, phone, notes);
        shift.setVolunteer(volunteer);

        Shift received = shift.clone();

        Shift expected = shift;
        assertEquals(expected, received);
    }    // testCloneVolunteerNoException()

    /**
     * Tests that the return value of {@link Shift#clone()} is not identical to
     * the argument when the shift does not have a volunteer.
     */
    @Test
    public void testCloneNoVolunteerIdentity() {
        System.out.println("clone - no volunteer, not identical");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        Shift received = shift.clone();

        assertFalse(shift == received);
    }    // testCloneNoVolunteerNoException()

    /**
     * Tests that the return value of {@link Shift#clone()} is not identical to
     * the argument when the shift has a volunteer.
     */
    @Test
    public void testCloneVolunteerIdentity() {
        System.out.println("clone - volunteer, not identical");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        String name = "foo";
        String email = "bar";
        String phone = "baz";
        String notes = "smurf";
        volunteer = new Volunteer(name, email, phone, notes);
        shift.setVolunteer(volunteer);

        Shift received = shift.clone();

        assertFalse(shift == received);
    }    // testCloneVolunteerNoException()

    /* toString */

    /**
     * Tests that {@link Shift#toString()} does not throw an exception.
     */
    @Test
    public void testToStringNoException() {
        System.out.println("toString - no exception");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        shift.toString();
    }    // testToStringNoException()

    /**
     * Tests that the return value of {@link Shift#toString()} is not null.
     */
    @Test
    public void testToStringNotNull() {
        System.out.println("toString - not null");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        String received = shift.toString();

        assertNotNull(received);
    }    // testToStringNotNull()

    /**
     * Tests that {@link Shift#toString()} returns the shift's description.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        description = "foo";
        List<Role> roles = Arrays.asList(new Role("foo"), new Role("bar"));
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);

        String received = shift.toString();

        String expected = description;
        assertEquals(expected, received);
    }    // testToString()
    
    /* getShiftFactory */
    
    /**
     * Tests that {@link Shift#getShiftFactory()} does not throw an exception.
     */
    @Test
    public void testGetShiftFactoryNoException() {
        System.out.println("getShiftFactory - no exception");
        
        Shift.getShiftFactory();
    }    // testToStringNoException()
    
    /**
     * Tests that the return value of {@link Shift#getShiftFactory()} is not
     * null.
     */
    @Test
    public void testGetShiftFactoryNotNull() {
        System.out.println("getShiftFactory - not null");
        
        ReadWritableFactory factory = Shift.getShiftFactory();
        assertNotNull(factory);
    }    // testGetShiftFactoryNotNull()
    
}    // ShiftTest
