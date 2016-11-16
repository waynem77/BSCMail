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
 * Unit tests for {@link Event}.
 * 
 * @author Wayne Miller
 */
public class EventTest {

    /**
     * Variable used to hold the event being tested.
     */
    Event event;
    
    /**
     * Prints unit test header.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Event");
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
        event = new Event();
    }    // setUp()

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        event = null;
    }    // tearDown()
    
    /* constructor */
    
    /**
     * Tests that {@link Event#Event()} does not throw an exception.
     */
    @Test
    public void testConstructor() {
        System.out.println("constructor");

        event = new Event();
    }    // testConstructor()

    /* hasDate / getDate / setDate */
    
    /**
     * Tests that {@link Event#hasDate()} does not throw an exception.
     */
    @Test
    public void testHasDateNoException() {
        System.out.println("hasDate - no exception");
        
        event.hasDate();
    }    // testHasDateNoException()
    
    /**
     * Tests that {@link Event#hasDate()} returns false when called before
     * {@link Event#setDate(Date)}.
     */
    @Test
    public void testHasDateBeforeSetDate() {
        System.out.println("hasDate - before setDate");
        
        boolean expected = false;
        boolean received = event.hasDate();
        assertEquals(expected, received);
    }    // testHasDateBeforeSetDate()
    
    /**
     * Tests that {@link Event#hasDate()} returns true when called after
     * {@link Event#setDate(Date)}.
     */
    @Test
    public void testHasDateAfterSetDate() {
        System.out.println("hasDate - after setDate");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        boolean expected = true;
        boolean received = event.hasDate();
        assertEquals(expected, received);
    }    // testHasDateAfterSetDate()
    
    /**
     * Tests that {@link Event#hasDate()} returns false when called after
     * {@link Event#setDate(Date)}, called with null.
     */
    @Test
    public void testHasDateAfterSetDateNull() {
        System.out.println("hasDate - after setDate, null");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        date = null;
        event.setDate(date);
        boolean expected = false;
        boolean received = event.hasDate();
        assertEquals(expected, received);
    }    // testHasDateAfterSetDateNull()
    
    /**
     * Tests that {@link Event#getDate()} does not throw an exception.
     */
    @Test
    public void testGetDateNoException() {
        System.out.println("getDate - no exception");
        
        event.getDate();
    }    // testGetDateNoException()
    
    /**
     * Tests that {@link Event#getDate()} returns null when called before
     * {@link Event#setDate(Date)}.
     */
    @Test
    public void testGetDateBeforeSetDate() {
        System.out.println("getDate - before setDate");
        
        Date expected = null;
        Date received = event.getDate();
        assertEquals(expected, received);
    }    // testGetDateBeforeSetDate()
    
    /**
     * Tests that {@link Event#getDate()} returns the correct value when
     * called after {@link Event#setDate(Date)}.
     */
    @Test
    public void testGetDateAfterSetDate() {
        System.out.println("getDate - after setDate");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        Date expected = date;
        Date received = event.getDate();
        assertEquals(expected, received);
    }    // testGetDateAfterSetDate()
    
    /**
     * Tests that {@link Event#getDate()} returns null when called after
     * {@link Event#setDate(Date)}, called with null.
     */
    @Test
    public void testGetDateAfterSetDateNull() {
        System.out.println("getDate - after setDate, null");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        date = null;
        event.setDate(date);
        Date expected = null;
        Date received = event.getDate();
        assertEquals(expected, received);
    }    // testGetDateAfterSetDateNull()
    
    /**
     * Tests that {@link Event#setDate(Date)} does not throw an exception.
     */
    @Test
    public void testSetDateNoException() {
        System.out.println("setDate - no exception");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
    }    // testSetDateNoException()
    
    /**
     * Tests that {@link Event#setDate(Date)} does not throw an exception
     * when the parameter is null, for an event with no date assigned.
     */
    @Test
    public void testSetDateNullNoDateNoException() {
        System.out.println("setDate - null, no previous date, no exception");
        
        Date date = null;
        event.setDate(date);
    }    // testSetDateNullNoDateNoException()
    
    /**
     * Tests that {@link Event#setDate(Date)} does not throw an exception
     * when the parameter is null.
     */
    @Test
    public void testSetDateNullNoException() {
        System.out.println("setDate - null, no exception");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 28);
        Date date = calendar.getTime();
        event.setDate(date);
        date = null;
        event.setDate(date);
    }    // testSetDateNullNoException()

    /* addEventProperty / getEventProperties */

    /**
     * Tests that {@link Event#addEventProperty(EventProperty)} throws a
     * {@link NullPointerException} when the parameter is null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddEventPropertyNull() {
        System.out.println("addEventProperty - null");
        
        EventProperty eventProperty = null;
        event.addEventProperty(eventProperty);
    }    // testAddEventPropertyNull()
    
    /**
     * Tests that {@link Event#addEventProperty(EventProperty)} does not throw
     * an exception when the parameter is not null.
     */
    @Test
    public void testAddEventProperty() {
        System.out.println("addEventProperty");
        
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        event.addEventProperty(eventProperty);
    }
    
    /**
     * Tests that {@link Event#getEventProperties()} does not throw an exception
     * when no eventProperties have been added.
     */
    @Test
    public void testGetEventPropertiesEmptyNoException() {
        System.out.println("getEventProperties - empty, no exception");
        
        event.getEventProperties();
    }    // testGetEventPropertiesEmptyNoException()
    
    /**
     * Tests that {@link Event#getEventProperties()} returns an empty list when
     * no eventProperties have been added.
     */
    @Test
    public void testGetEventPropertiesEmpty() {
        System.out.println("getEventProperties - empty");
        
        List<EventProperty> expected = Collections.emptyList();
        List<EventProperty> received = event.getEventProperties();
        assertEquals(expected, received);
    }    // testGetEventPropertiesEmpty()
        
    /**
     * Tests that {@link Event#getEventProperties()} does not throw an exception
     * after eventProperties have been added.
     */
    @Test
    public void testGetEventPropertiesNoException() {
        System.out.println("getEventProperties - no exception");
        
        
        String name = "foo";
        String defaultValue = "bar";
        EventProperty eventProperty = new EventProperty(name, defaultValue);
        event.addEventProperty(eventProperty);
        event.getEventProperties();
    }    // testGetEventPropertiesNoException()
        
    /**
     * Tests that {@link Event#getEventProperties()} returns the correct value
     * after eventProperties have been added.
     */
    @Test
    public void testGetEventProperties() {
        System.out.println("getEventProperties");
        
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("foo", "one"),
                new EventProperty("bar", "two"),
                new EventProperty("baz", "three"));
        for (EventProperty eventProperty : eventProperties) {
            event.addEventProperty(eventProperty);
        }    // for
        List<EventProperty> expected = eventProperties;
        List<EventProperty> received = event.getEventProperties();
        assertEquals(expected, received);
    }    // testGetEventProperties()
        
    /**
     * Tests that the list returned from {@link Event#getEventProperties()} is
     * unmodifiable.
     */
    @Test
    public void testGetEventPropertiesUnmodifiable() {
        System.out.println("getEventProperties - return value is unmodifiable");
        
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("foo", "one"),
                new EventProperty("bar", "two"),
                new EventProperty("baz", "three"));
        for (EventProperty eventProperty : eventProperties) {
            event.addEventProperty(eventProperty);
        }    // for
        List<EventProperty> list = event.getEventProperties();
        
        EventProperty eventProperty = new EventProperty("smurf", "four");
        try {
            list.add(eventProperty);
            fail("able to add eventProperties to return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.remove(0);
            fail("able to remove eventProperties from return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.set(0, eventProperty);
            fail("able to replace eventProperties in return value");
        } catch (RuntimeException e) {    // try
        }    // catch
    }    // testGetEventPropertiesUnmodifiable()
        
    /**
     * Tests that the elements of the list returned from
     * {@link Event#getEventProperties()} are modifiable.
     */
    @Test
    public void testGetEventPropertiesElementsModifiable() {
        System.out.println("getEventProperties - elements of return value are modifiable");
        
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("foo", "one"),
                new EventProperty("bar", "two"),
                new EventProperty("baz", "three"));
        for (EventProperty eventProperty : eventProperties) {
            event.addEventProperty(eventProperty);
        }    // for
        List<EventProperty> list = event.getEventProperties();
        int index = 0;
        String value = "smurf";
        list.get(index).setValue(value);
    }    // testGetEventPropertiesElementsModifiable()
        
    /**
     * Tests that changes to the elements of the list returned from
     * {@link Event#getEventProperties()} are persistent.
     */
    @Test
    public void testGetEventPropertiesPersistence() {
        System.out.println("getEventProperties - persistence");
        
        List<EventProperty> eventProperties = Arrays.asList(new EventProperty("foo", "one"),
                new EventProperty("bar", "two"),
                new EventProperty("baz", "three"));
        for (EventProperty eventProperty : eventProperties) {
            event.addEventProperty(eventProperty);
        }    // for
        List<EventProperty> list = event.getEventProperties();
        int index = 0;
        String value = "smurf";
        list.get(index).setValue(value);
        list = null;
        
        list = event.getEventProperties();
        String expected = value;
        String received = list.get(index).getValue();
        assertEquals(expected, received);
    }    // testGetEventPropertiesPersistence()
    
    /* addShift / getShifts */
    
    /**
     * Tests that {@link Event#addShift(Shift)} throws a
     * {@link NullPointerException} when the parameter is null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddShiftNull() {
        System.out.println("addShift - null");
        
        Shift shift = null;
        event.addShift(shift);
    }    // testAddShiftNull()
    
    /**
     * Tests that {@link Event#addShift(Shift)} does not throw an exception
     * when the parameter is not null.
     */
    @Test
    public void testAddShift() {
        System.out.println("addShift");
        
        String description = "foo";
        List<Role> roles = new LinkedList<>();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        event.addShift(shift);
    }
    
    /**
     * Tests that {@link Event#getShifts()} does not throw an exception when no
     * shifts have been added.
     */
    @Test
    public void testGetShiftsEmptyNoException() {
        System.out.println("getShifts - empty, no exception");
        
        event.getShifts();
    }    // testGetShiftsEmptyNoException()
    
    /**
     * Tests that {@link Event#getShifts()} returns an empty list when no shifts
     * have been added.
     */
    @Test
    public void testGetShiftsEmpty() {
        System.out.println("getShifts - empty");
        
        List<Shift> expected = Collections.emptyList();
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testGetShiftsEmpty()
        
    /**
     * Tests that {@link Event#getShifts()} does not throw an exception after
     * shifts have been added.
     */
    @Test
    public void testGetShiftsNoException() {
        System.out.println("getShifts - no exception");
        
        
        String description = "foo";
        List<Role> roles = new LinkedList<>();
        boolean displayVolunteerEmail = true;
        boolean displayVolunteerPhone = true;
        boolean displayVolunteerNotes = true;
        Shift shift = new Shift(description, roles, displayVolunteerEmail, displayVolunteerPhone, displayVolunteerNotes);
        event.addShift(shift);
        event.getShifts();
    }    // testGetShiftsNoException()
        
    /**
     * Tests that {@link Event#getShifts()} returns the correct value after
     * shifts have been added.
     */
    @Test
    public void testGetShifts() {
        System.out.println("getShifts");
        
        List<Shift> shifts = Arrays.asList(new Shift("foo", new LinkedList<Role>(), true, true, true),
                new Shift("bar", new LinkedList<Role>(), true, true, true),
                new Shift("baz", new LinkedList<Role>(), true, true, true));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for
        List<Shift> expected = shifts;
        List<Shift> received = event.getShifts();
        assertEquals(expected, received);
    }    // testGetShifts()
        
    /**
     * Tests that the list returned from {@link Event#getShifts()} is
     * unmodifiable.
     */
    @Test
    public void testGetShiftsUnmodifiable() {
        System.out.println("getShifts - return value is unmodifiable");
        
        List<Shift> shifts = Arrays.asList(new Shift("foo", new LinkedList<Role>(), true, true, true),
                new Shift("bar", new LinkedList<Role>(), true, true, true),
                new Shift("baz", new LinkedList<Role>(), true, true, true));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for
        List<Shift> list = event.getShifts();
        
        Shift shift = new Shift("smurf", new LinkedList<Role>(), true, true, true);
        try {
            list.add(shift);
            fail("able to add shifts to return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.remove(0);
            fail("able to remove shifts from return value");
        } catch (RuntimeException e) {    // try
        }    // catch
        try {
            list.set(0, shift);
            fail("able to replace shifts in return value");
        } catch (RuntimeException e) {    // try
        }    // catch
    }    // testGetShiftsUnmodifiable()
        
    /**
     * Tests that the elements of the list returned from
     * {@link Event#getShifts()} are modifiable.
     */
    @Test
    public void testGetShiftsElementsModifiable() {
        System.out.println("getShifts - elements of return value are modifiable");
        
        List<Shift> shifts = Arrays.asList(new Shift("foo", new LinkedList<Role>(), true, true, true),
                new Shift("bar", new LinkedList<Role>(), true, true, true),
                new Shift("baz", new LinkedList<Role>(), true, true, true));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for
        List<Shift> list = event.getShifts();
        int index = 0;
        Volunteer volunteer = new Volunteer("smurf", "snork", "gummibear", "thundercat");
        list.get(index).setVolunteer(volunteer);
    }    // testGetShiftsElementsModifiable()
        
    /**
     * Tests that changes to the elements of the list returned from
     * {@link Event#getShifts()} are persistent.
     */
    @Test
    public void testGetShiftsPersistence() {
        System.out.println("getShifts - persistence");
        
        List<Shift> shifts = Arrays.asList(new Shift("foo", new LinkedList<Role>(), true, true, true),
                new Shift("bar", new LinkedList<Role>(), true, true, true),
                new Shift("baz", new LinkedList<Role>(), true, true, true));
        for (Shift shift : shifts) {
            event.addShift(shift);
        }    // for
        List<Shift> list = event.getShifts();
        int index = 0;
        Volunteer volunteer = new Volunteer("smurf", "snork", "gummibear", "thundercat");
        list.get(index).setVolunteer(volunteer);
        list = null;
        
        list = event.getShifts();
        Volunteer expected = volunteer;
        Volunteer received = list.get(index).getVolunteer();
        assertEquals(expected, received);
    }    // testGetShiftsPersistence()

}    // EventTest
